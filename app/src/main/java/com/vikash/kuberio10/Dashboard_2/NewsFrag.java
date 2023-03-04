package com.vikash.kuberio10.Dashboard_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vikash.kuberio10.Dashboard_2.NewsAdapter.Normal_News_Adapter;
import com.vikash.kuberio10.Dashboard_2.NewsAdapter.Stock_News_Adapter;
import com.vikash.kuberio10.Dashboard_2.NewsModel.NormalNewsModel;
import com.vikash.kuberio10.Dashboard_2.NewsModel.StockNewsModel;
import com.vikash.kuberio10.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NewsFrag extends Fragment {
    RecyclerView recyclerView;
    Normal_News_Adapter normalNewsAdapter;
    Stock_News_Adapter stockNewsAdapter;

    private int newsCount = 0;
    private final int limit = 9;
    private final int timeLimit = 60; // in seconds
    private Instant lastNewsCall = Instant.now();
    Button getnormalnews;
    Button getstockNews;


//    ExpandableListViewAdapter listViewAdapter;
//    ExpandableListView expandableListView;
//    List<String> answer_list;
//    HashMap<String, List<String>> question_list;
//
//    private LinearLayout dotsLL;
//    SliderAdapter adapter;
//    int size;



    public NewsFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.news, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getnormalnews=view.findViewById(R.id.news);
        getstockNews=view.findViewById(R.id.mainNews);

        getnormalnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Instant now = Instant.now();
                long elapsed = now.getEpochSecond() - lastNewsCall.getEpochSecond();
                if (elapsed > timeLimit) {
                    newsCount = 0;
                    lastNewsCall = now;
                }
                // Check if the call limit has been reached
                if (newsCount >= limit) {
                    long remainingTime = timeLimit - elapsed;
                    try {
                        Thread.sleep(remainingTime * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    newsCount = 0;
                    lastNewsCall = Instant.now();
                }
                newsCount++;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://mboum-finance.p.rapidapi.com/ne/news/?symbol=AAPL%2CMSFT")
                        .get()
                        .addHeader("x-rapidapi-host", "mboum-finance.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", "18c935d513msh8cff4fe15c38f82p17f7aejsnef2a8a9efb44")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, IOException e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                        String responseString = response.body().string();
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject resultJson = new JSONObject(responseString);
                                    List<NormalNewsModel> newsList = new ArrayList<>();
                                    JSONArray jsonArray = resultJson.getJSONArray("item");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        NormalNewsModel news = new NormalNewsModel();
                                        news.setTitle(jsonArray.getJSONObject(i).getString("title"));
                                        news.setLink(jsonArray.getJSONObject(i).getString("link"));
                                        news.setDescription(jsonArray.getJSONObject(i).getString("description"));
                                        news.setPublish_date(jsonArray.getJSONObject(i).getString("pubDate"));
                                        newsList.add(news);
                                    }
                                    normalNewsAdapter = new Normal_News_Adapter(newsList,getContext());
                                    recyclerView.setAdapter(normalNewsAdapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "Oops!! something went wrong, please try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }

        });

        getstockNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instant now = Instant.now();
                long elapsed = now.getEpochSecond() - lastNewsCall.getEpochSecond();
                if (elapsed > timeLimit) {
                    newsCount = 0;
                    lastNewsCall = now;
                }
                // Check if the call limit has been reached
                if (newsCount >= limit) {
                    long remainingTime = timeLimit - elapsed;
                    try {
                        Thread.sleep(remainingTime * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    newsCount = 0;
                    lastNewsCall = Instant.now();
                }
                newsCount++;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://mboum-finance.p.rapidapi.com/ne/news")
                        .get()
                        .addHeader("X-RapidAPI-Key", "b8457a3c19msh6b8f44d876294c3p1e11dbjsnb5f81e2e8e41")
                        .addHeader("X-RapidAPI-Host", "mboum-finance.p.rapidapi.com")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call,Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                        String responseString = response.body().string();

                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    JSONArray resultJson = new JSONArray(responseString);
                                    List<StockNewsModel> mainNewsList = new ArrayList<>();
                                    // JSONArray jsonArray = resultJson.getJSONArray("item");
                                    for (int i = 0; i < resultJson.length(); i++){
                                        StockNewsModel mainNews = new StockNewsModel();
                                        mainNews.setTitle(resultJson.getJSONObject(i).getString("title"));
                                        mainNews.setLink(resultJson.getJSONObject(i).getString("link"));
                                        mainNews.setPubDate(resultJson.getJSONObject(i).getString("pubDate"));
                                        mainNews.setSource(resultJson.getJSONObject(i).getString("source"));
                                        mainNewsList.add(mainNews);
                                    }
                                    stockNewsAdapter = new Stock_News_Adapter(mainNewsList, getContext());
                                    recyclerView.setAdapter(stockNewsAdapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "Oops!! something went wrong, please try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });






//        expandableListView = view.findViewById(R.id.elistView);
//
//        showList();
//
//        listViewAdapter = new ExpandableListViewAdapter(getContext(), answer_list, question_list);
//        expandableListView.setAdapter(listViewAdapter);
//
//        // initializing all our views.
//        AutoScrollViewPager viewPager = view.findViewById(R.id.idViewPager);
//        dotsLL = view.findViewById(R.id.idLLDots);
//
//        // in below line we are creating a new array list.
//        ArrayList<SliderModal> sliderModalArrayList = new ArrayList<>();
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
//

        return view;

    }

//    private void showList() {
//        answer_list = new ArrayList<String>();
//        question_list = new HashMap<String, List<String>>();
//
//        answer_list.add("How does Mutual Fund tracking work?");
//        answer_list.add("How often should I sync my portfolio?");
//        answer_list.add("How can I track my family's portfolio?");
//        answer_list.add("What is manual sync?");
//        answer_list.add("Why is my data incorrect?");
//
//        List<String> topic1 = new ArrayList<>();
//
//        topic1.add("We generated an eCAS (Consolidated Account Statement) for your registered email,scan it, and show you a consolidated view of the portfolio.");
////        topic1.add("Topic 2");
////        topic1.add("Topic 3");
//
//        List<String> topic2 = new ArrayList<>();
//
//        topic2.add("It is recommended to sync once in every month, also you can sync whenever you do a new transaction so that your portfolio is up to date.");
////        topic2.add("Topic 2");
////        topic2.add("Topic 3");
//
//        List<String> topic3 = new ArrayList<>();
//
//        topic3.add("Add your family member's email address and initiate sync. You can do this for as many family members as you want.");
////        topic3.add("Topic 2");
////        topic3.add("Topic 3");
//
//        List<String> topic4 = new ArrayList<>();
//
//        topic4.add("To track your portfolio, please do a manual sync by forwarding the eCAS to review@kuberio.in");
////        topic4.add("Topic 2");
////        topic4.add("Topic 3");
//
//        List<String> topic5 = new ArrayList<>();
//
//        topic5.add("It could be because of Funds are in a demat account, Consolidated funds and Funds mapped to some other email." +
//                "  For any difficulty, please reach out to support@kuberio.in");
////        topic5.add("Topic 2");
////        topic5.add("Topic 3");
//
//        question_list.put(answer_list.get(0), topic1);
//        question_list.put(answer_list.get(1),topic2);
//        question_list.put(answer_list.get(2),topic3);
//        question_list.put(answer_list.get(3),topic4);
//        question_list.put(answer_list.get(4),topic5);
//
//    }
//
//    private void addDots(int size, int pos) {
//        // inside this method we are
//        // creating a new text view.
//        TextView[] dots = new TextView[size];
//
//        // below line is use to remove all
//        // the views from the linear layout.
//        dotsLL.removeAllViews();
//
//        // running a for loop to add
//        // number of dots to our slider.
//        for (int i = 0; i < size; i++) {
//            // below line is use to add the
//            // dots and modify its color.
//            if (getContext()!=null){
//                dots[i] = new TextView(getContext());
//                dots[i].setText(Html.fromHtml("•"));
//                dots[i].setTextSize(35);
//                dots[i].setTextColor(getResources().getColor(R.color.black));
//                dotsLL.addView(dots[i]);
//            }
//
//
//            // below line is called when the dots are not selected.
//
//        }
//        if (dots.length > 0) {
//            // this line is called when the dots
//            // inside linear layout are selected
//            if(getContext()!=null){
//                dots[pos].setTextColor(ContextCompat.getColor(getContext(), R.color.purple_200));
//            }
////            dots[pos].setTextColor(ContextCompat.getColor(getContext(), R.color.purple_200));
//
//        }
//    }
//
//    // creating a method for view pager for on page change listener.
//    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            // we are calling our dots method to
//            // change the position of selected dots.
//            addDots(size, position);
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    };
}