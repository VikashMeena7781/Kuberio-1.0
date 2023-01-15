package com.vikash.kuberio10.Dasboard;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.vikash.kuberio10.Database.MyDbHandler;
import com.vikash.kuberio10.R;
import com.vikash.kuberio10.SlideShow.SliderAdapter;
import com.vikash.kuberio10.SlideShow.SliderModal;
import com.vikash.kuberio10.profile;

import java.util.ArrayList;

import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFrag extends Fragment {



    private AutoScrollViewPager viewPager;
    private LinearLayout dotsLL;
    SliderAdapter adapter;
    private ArrayList<SliderModal> sliderModalArrayList;
    private TextView[] dots;
    int size;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFrag.
     */
    // TODO: Rename and change types and number of parameters
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView user_name;

        user_name=view.findViewById(R.id.user_name);
        /** to set username fetch data from database with mobile_number used*/
        MyDbHandler db = new MyDbHandler(getContext());
        String number = getActivity().getIntent().getStringExtra("mobile_number");
        if(number!=null){
            ArrayList<String> user_info = db.User_info(number);

            String firstname = user_info.get(0);
            String lastname = user_info.get(1);

            user_name.setText(firstname+" "+lastname);
        }


        ImageView notification = view.findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You have no Notification yet!", Toast.LENGTH_SHORT).show();
            }
        });



        /**Click Listener on Card View */
        CardView card_1 = view.findViewById(R.id.card_1);
        CardView card_2 = view.findViewById(R.id.card_2);
        CardView card_3 = view.findViewById(R.id.card_3);
        CardView card_4 = view.findViewById(R.id.track);

        card_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TrackerFrag();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.Conatiner,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        card_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        card_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        card_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });


        /**Bug in Custom Slider Code*/

//        viewPager = view.findViewById(R.id.idViewPager);
//        dotsLL = view.findViewById(R.id.idLLDots);
//
//        // in below line we are creating a new array list.
//        sliderModalArrayList = new ArrayList<SliderModal>();
//
//        // on below 3 lines we are adding data to our array list.
//        sliderModalArrayList.add(new SliderModal("Unify your Mutual Funds", "Track Mutual Fund investments bought across different apps"));
//        sliderModalArrayList.add(new SliderModal("See your family's Mutual Funds", "Consolidate and get a health report of your entire family's Mutual Fund portfolio."));
//        sliderModalArrayList.add(new SliderModal("Keep your portfolio updated", "Auto-sync your portfolio using your registered email(for Gmail users only)"));
//
//        // below line is use to add our array list to adapter class.
//        adapter = new SliderAdapter(getContext(), sliderModalArrayList);
//
//        // below line is use to set our
//        // adapter to our view pager.
//        //Setting up Auto_Scrolling
//        viewPager.startAutoScroll();
//        viewPager.setInterval(5000);
//        viewPager.setCycle(true);
//        viewPager.setStopScrollWhenTouch(true);
//        viewPager.setAdapter(adapter);
//
//        // we are storing the size of our
//        // array list in a variable.
//        size = sliderModalArrayList.size();
//
//        // calling method to add dots indicator
//        addDots(size, 0);
//
//        // below line is use to call on
//        // page change listener method.
//        viewPager.addOnPageChangeListener(viewListener);

        return view;
    }
    private void addDots(int size, int pos) {
        // inside this method we are
        // creating a new text view.
        dots = new TextView[size];

        // below line is use to remove all
        // the views from the linear layout.
        dotsLL.removeAllViews();

        // running a for loop to add
        // number of dots to our slider.
        for (int i = 0; i < size; i++) {
            // below line is use to add the
            // dots and modify its color.
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("•"));
            dots[i].setTextSize(35);

            // below line is called when the dots are not selected.
            dots[i].setTextColor(getResources().getColor(R.color.black));
            dotsLL.addView(dots[i]);
        }
        if (dots.length > 0) {
            // this line is called when the dots
            // inside linear layout are selected
            dots[pos].setTextColor(getResources().getColor(R.color.purple_200));
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            // we are calling our dots method to
            // change the position of selected dots.
            addDots(size, position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}