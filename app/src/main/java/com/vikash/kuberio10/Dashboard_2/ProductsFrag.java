package com.vikash.kuberio10.Dashboard_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.vikash.kuberio10.Pdf;
import com.vikash.kuberio10.R;

public class ProductsFrag extends Fragment {

    public ProductsFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
        btn1=view.findViewById(R.id.btn_1);
        btn2=view.findViewById(R.id.btn_2);
        btn3=view.findViewById(R.id.btn_3);
        btn4=view.findViewById(R.id.btn_4);
        btn5=view.findViewById(R.id.btn_5);
        btn6=view.findViewById(R.id.btn_6);
        btn7=view.findViewById(R.id.btn_7);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Pdf.class);
                intent.putExtra("link","");
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),Pdf.class);
                intent.putExtra("link","");
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),Pdf.class);
                intent.putExtra("link","");
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),Pdf.class);
                intent.putExtra("link","");
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),Pdf.class);
                intent.putExtra("link","");
                startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),Pdf.class);
                intent.putExtra("link","");
                startActivity(intent);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),Pdf.class);
                intent.putExtra("link","");
                startActivity(intent);
            }
        });

        return view;
    }
}