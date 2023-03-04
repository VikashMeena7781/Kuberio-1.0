package com.vikash.kuberio10.Dashboard_2.NewsAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.vikash.kuberio10.Dashboard_2.NewsModel.NormalNewsModel;
import com.vikash.kuberio10.OpenWeb;
import com.vikash.kuberio10.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Normal_News_Adapter extends RecyclerView.Adapter<Normal_News_Adapter.ViewHolder> {
    private List<NormalNewsModel> news;
    private Context context;

    public Normal_News_Adapter(List<NormalNewsModel> news ,Context context){
        this.news=news;
        this.context=context;
    }


    @NonNull
    @Override
    public Normal_News_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.normal_news,parent,false);
        return new Normal_News_Adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Normal_News_Adapter.ViewHolder holder, int position) {
        final NormalNewsModel news1 = news.get(position);

        holder.title.setText(news1.getTitle());
        holder.Description.setText(news1.getDescription());

        holder.knowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenWeb.class);
                intent.putExtra("link",news1.getLink());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,Description;
        TextView knowmore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            Description=itemView.findViewById(R.id.description);
            knowmore=itemView.findViewById(R.id.link);
        }
    }
}