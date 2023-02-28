package com.vikash.kuberio10.Dashboard_2;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.vikash.kuberio10.R;
import com.vikash.kuberio10.SlideShow.ExpandableListViewAdapter;
import com.vikash.kuberio10.SlideShow.SliderAdapter;
import com.vikash.kuberio10.SlideShow.SliderModal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrackerFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackerFrag extends Fragment {

    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> answer_list;
    HashMap<String, List<String>> question_list;

    private AutoScrollViewPager viewPager;
    private LinearLayout dotsLL;
    SliderAdapter adapter;
    private ArrayList<SliderModal> sliderModalArrayList;
    private TextView[] dots;
    int size;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TrackerFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrackerFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static TrackerFrag newInstance(String param1, String param2) {
        TrackerFrag fragment = new TrackerFrag();
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
        View view = inflater.inflate(R.layout.fragment_tracker, container, false);

        expandableListView = view.findViewById(R.id.elistView);

        showList();

        listViewAdapter = new ExpandableListViewAdapter(getContext(), answer_list, question_list);
        expandableListView.setAdapter(listViewAdapter);

        // initializing all our views.
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

    private void showList() {
        answer_list = new ArrayList<String>();
        question_list = new HashMap<String, List<String>>();

        answer_list.add("How does Mutual Fund tracking work?");
        answer_list.add("How often should I sync my portfolio?");
        answer_list.add("How can I track my family's portfolio?");
        answer_list.add("What is manual sync?");
        answer_list.add("Why is my data incorrect?");

        List<String> topic1 = new ArrayList<>();

        topic1.add("We generated an eCAS (Consolidated Account Statement) for your registered email,scan it, and show you a consolidated view of the portfolio.");
//        topic1.add("Topic 2");
//        topic1.add("Topic 3");

        List<String> topic2 = new ArrayList<>();

        topic2.add("It is recommended to sync once in every month, also you can sync whenever you do a new transaction so that your portfolio is up to date.");
//        topic2.add("Topic 2");
//        topic2.add("Topic 3");

        List<String> topic3 = new ArrayList<>();

        topic3.add("Add your family member's email address and initiate sync. You can do this for as many family members as you want.");
//        topic3.add("Topic 2");
//        topic3.add("Topic 3");

        List<String> topic4 = new ArrayList<>();

        topic4.add("To track your portfolio, please do a manual sync by forwarding the eCAS to review@kuberio.in");
//        topic4.add("Topic 2");
//        topic4.add("Topic 3");

        List<String> topic5 = new ArrayList<>();

        topic5.add("It could be because of Funds are in a demat account, Consolidated funds and Funds mapped to some other email." +
                "  For any difficulty, please reach out to support@kuberio.in");
//        topic5.add("Topic 2");
//        topic5.add("Topic 3");

        question_list.put(answer_list.get(0), topic1);
        question_list.put(answer_list.get(1),topic2);
        question_list.put(answer_list.get(2),topic3);
        question_list.put(answer_list.get(3),topic4);
        question_list.put(answer_list.get(4),topic5);

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

    // creating a method for view pager for on page change listener.
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