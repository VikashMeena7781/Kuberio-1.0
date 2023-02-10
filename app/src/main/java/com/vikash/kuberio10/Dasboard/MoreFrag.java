package com.vikash.kuberio10.Dasboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.vikash.kuberio10.MainActivity;
import com.vikash.kuberio10.R;
import com.vikash.kuberio10.profile;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoreFrag() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreFrag newInstance(String param1, String param2) {
        MoreFrag fragment = new MoreFrag();
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
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        CardView logout = view.findViewById(R.id.log_out);
        CardView profile,proposal,transaction,sips,privacy_policy,terms,refund_policy;

        profile=view.findViewById(R.id.profile);
        proposal=view.findViewById(R.id.proposal);
        transaction=view.findViewById(R.id.transaction);
        sips=view.findViewById(R.id.sips);
        privacy_policy=view.findViewById(R.id.privacy_policy);
        terms=view.findViewById(R.id.terms);
        refund_policy=view.findViewById(R.id.refund_policy);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile_number = getActivity().getIntent().getStringExtra("mobile_number");
                Intent intent = new Intent(getActivity().getApplicationContext(), com.vikash.kuberio10.profile.class);
                intent.putExtra("mobile_number",mobile_number);
                startActivity(intent);
            }
        });

        proposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        sips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        refund_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

                SharedPreferences pref = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("flag",false);
                editor.apply();
            }
        });

        return view;
    }
}