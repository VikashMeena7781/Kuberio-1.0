package com.vikash.kuberio10.Dashboard_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vikash.kuberio10.Dashboard_2.Chat_Adapter.MessageAdapter;
import com.vikash.kuberio10.Dashboard_2.Chat_Model.Chat;
import com.vikash.kuberio10.R;
import com.vikash.kuberio10.User_Info;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    TextView profile_image;
    TextView username;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    ImageButton btn;
    EditText text_send;

    MessageAdapter messageAdapter;
    List<Chat> chats;

    RecyclerView recyclerView;

    String user_id ;

    Intent intent;

    ValueEventListener seenListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        profile_image=findViewById(R.id.profile_image);
        username=findViewById(R.id.username);
        btn=findViewById(R.id.btn_send);
        text_send=findViewById(R.id.text_send);
        recyclerView=findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        intent=getIntent();
        final String id = intent.getStringExtra("userid");
        user_id=id;
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


        btn.setOnClickListener(view -> {
            String msg = text_send.getText().toString();
            if(!msg.equals("")){
                sendMessage(firebaseUser.getUid(),id,msg);
            }else{
                Toast.makeText(MessageActivity.this, "You can't send Empty Message", Toast.LENGTH_SHORT).show();
            }
            text_send.setText("");
        });
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users_Data").child(id);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User_Info user_info = snapshot.getValue(User_Info.class);
                assert user_info!=null;
                username.setText(user_info.getUsername());
//                if(user_info.getImageUrl().equals("default")){
//                profile_image.setImageResource(R.mipmap.ic_launcher)
//                ;
                profile_image.setText(String.valueOf(user_info.getUsername().charAt(0)));
//                }else{
//                    Glide.with(getApplicationContext()).load(user_info.getImageUrl());
//                }
                readMessage(firebaseUser.getUid(),id);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        seenMessage(id);

    }
    private void seenMessage (String userid){
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        seenListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    assert chat != null;
                    if(chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid)
                    ){
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("isseen",true);
                        dataSnapshot.getRef().updateChildren(hashMap);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void sendMessage(String sender , String receiver , String Message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",Message);
        hashMap.put("isseen",false);

        reference.child("Chats").push().setValue(hashMap);

        // add user to chat fragment.. final tha phle yha ;
        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("ChatList")
                .child(firebaseUser.getUid())
                .child(user_id);
        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String timestamp = dtf.format(now);
                    chatRef.child("id").setValue(user_id);
                    chatRef.child("last_time_msg").setValue(timestamp);
                }else{
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String timestamp = dtf.format(now);
                    chatRef.child("last_time_msg").setValue(timestamp);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final DatabaseReference chatRef_1 = FirebaseDatabase.getInstance().getReference("ChatList")
                .child(user_id)
                .child(firebaseUser.getUid());
        chatRef_1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String timestamp = dtf.format(now);
                    chatRef_1.child("id").setValue(firebaseUser.getUid());
                    chatRef_1.child("last_time_msg").setValue(timestamp);
                }else{
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String timestamp = dtf.format(now);
                    chatRef_1.child("last_time_msg").setValue(timestamp);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void readMessage(String myid ,String userid){
        chats = new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chats.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    assert chat != null;
                    if(chat.getReceiver().equals(myid) && chat.getSender().equals(userid) || chat.getReceiver().equals(userid) && chat.getSender().equals(myid)){
                        chats.add(chat);
                    }
                    messageAdapter = new MessageAdapter(getApplicationContext(),chats);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private  void  status(String status){
        reference = FirebaseDatabase.getInstance().getReference("Users_Data").child(firebaseUser.getUid());

        reference.child("status").setValue(status).addOnCompleteListener(task -> {

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        reference.removeEventListener(seenListener);
        status("offline");
    }
}