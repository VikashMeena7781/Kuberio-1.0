package com.vikash.kuberio10.Dashboard_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vikash.kuberio10.Dashboard_2.Chat_Fragment.ChatFragment;
import com.vikash.kuberio10.Dashboard_2.Chat_Fragment.UserFragment;
import com.vikash.kuberio10.Dashboard_2.Chat_Model.Chat;
import com.vikash.kuberio10.R;
import com.vikash.kuberio10.User_Info;

import java.util.ArrayList;
import java.util.List;


public class ChatFrag extends Fragment {
    FirebaseUser user;
    FirebaseAuth auth;
    DatabaseReference reference;
    //TextView profile_image;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ArrayList<String> mUsersList = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFrag() {
        // Required empty public constructor
    }

    public static HomeFrag newInstance(String param1, String param2) {
        HomeFrag fragment = new HomeFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wealthy, container, false);

        TextView username = view.findViewById(R.id.username);
        //profile_image=view.findViewById(R.id.profile_image);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

//        tabLayout_1=tabLayout;

        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users_Data").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User_Info user_info = snapshot.getValue(User_Info.class);
                assert user_info!=null;
                username.setText(user_info.getUsername());
//                if(user_info.getImageUrl().equals("default")){
//                profile_image.setImageResource(R.mipmap.ic_launcher);
 //               profile_image.setText(String.valueOf(user_info.getUsername().charAt(0)));
//                }else{
//                    Glide.with(getContext()).load(user_info.getImageUrl()).into(profile_image);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
                if(isAdded()){
                    viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
                }
//                viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
                int unread=0;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    assert chat != null;
                    if(chat.getReceiver().equals(user.getUid()) && !chat.isIsseen()){
                        unread++;
                    }
                }
                if(unread==0){
                    viewPagerAdapter.addFragment(new ChatFragment(),"Chats");
                }else{
                    viewPagerAdapter.addFragment(new ChatFragment(),"("+unread+") Chats");

                }
                viewPagerAdapter.addFragment(new UserFragment(),"Users");
//                viewPagerAdapter.addFragment(new ProfileFragment(),"Profile");

                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);

                if(savedInstanceState !=null){
                    mUsersList = savedInstanceState.getStringArrayList("users_list");
                    viewPagerAdapter.setUsersList(mUsersList);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("users_list", mUsersList);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Restore saved state
        if (savedInstanceState != null) {
            mUsersList = savedInstanceState.getStringArrayList("users_list");
            viewPagerAdapter.setUsersList(mUsersList);
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        FragmentPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
//        tabLayout_1.setupWithViewPager(viewPager);
    }


//    public class ViewPagerAdapter extends FragmentPagerAdapter {
//        private ArrayList<Fragment> fragments;
//        private ArrayList<String> titles;
//
//        ViewPagerAdapter(FragmentManager fm){
//            super(fm);
//            this.fragments=new ArrayList<>();
//            this.titles=new ArrayList<>();
//        }
//
//
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragments.size();
//        }
//        public void addFragment(Fragment fragment , String title){
//            fragments.add(fragment);
//            titles.add(title);
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return titles.get(position);
//        }
//    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mFragmentTitleList = new ArrayList<>();
        private ArrayList<String> mUsersList = new ArrayList<String>();

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void setUsersList(ArrayList<String> usersList) {
            mUsersList = usersList;
            notifyDataSetChanged();
        }

        public List<String> getUsersList() {
            return mUsersList;
        }
//        public boolean isAdded() {
//            return getActivity() != null && isAdded();
//        }

    }


    private  void  status(String status){
        reference = FirebaseDatabase.getInstance().getReference("Users_Data").child(user.getUid());

        reference.child("status").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
//        if(isAdded()) {
//            viewPagerAdapter.setUsersList(mUsersList);
//        }
        status("online");
    }

    @Override
    public void onPause() {
        super.onPause();
        status("offline");
    }

}