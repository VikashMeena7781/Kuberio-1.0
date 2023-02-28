package com.vikash.kuberio10.Dashboard_2.Chat_Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vikash.kuberio10.Dashboard_2.Chat_Adapter.UserAdapter;
import com.vikash.kuberio10.Dashboard_2.Chat_Model.ChatList;
import com.vikash.kuberio10.R;
import com.vikash.kuberio10.User_Info;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User_Info> users;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    private List<ChatList> user_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_chat, container, false);

            recyclerView= view.findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            user_list=new ArrayList<>();

            reference= FirebaseDatabase.getInstance().getReference("ChatList").child(firebaseUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user_list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ChatList chatList = dataSnapshot.getValue(ChatList.class);
                        user_list.add(chatList);
                    }
                    for (int i = 0; i < user_list.size(); i++) {
                        for (int j = i+1; j < user_list.size(); j++) {
                            ChatList temp;
                            Timestamp ts2;
                            Timestamp ts1 ;
                            if(user_list.get(j).last_time_msg!=null && user_list.get(i).last_time_msg!=null){
                                ts2 = Timestamp.valueOf(user_list.get(j).last_time_msg);
                                ts1 = Timestamp.valueOf(user_list.get(i).last_time_msg);
                                int x = ts1.compareTo(ts2);
                                if(x<=0){
                                    temp=user_list.get(i);
                                    user_list.set(i, user_list.get(j));
                                    user_list.set(j,temp);
                                }
                            }
                        }

                    }
                    chatList();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            return view;
        }
    private void chatList() {
        users = new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference("Users_Data");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (ChatList chatList : user_list){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        User_Info user_info = dataSnapshot.getValue(User_Info.class);
                        assert user_info!=null;
                        if(chatList.getId().equals(user_info.getId())){
                            users.add(user_info);
                        }
                    }
                }
                userAdapter = new UserAdapter(getContext(),users,true);
                recyclerView.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }