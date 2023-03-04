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

import com.vikash.kuberio10.Dashboard_2.NewsModel.StockNewsModel;
import com.vikash.kuberio10.OpenWeb;
import com.vikash.kuberio10.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Stock_News_Adapter extends RecyclerView.Adapter<Stock_News_Adapter.ViewHolder> {

    private List<StockNewsModel> mainNews;
    private Context context;

    public Stock_News_Adapter(List<StockNewsModel> mainNews ,Context context){
        this.mainNews=mainNews;
        this.context=context;
    }
    @NonNull
    @Override
    public Stock_News_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stock_news,parent,false);
        return new ViewHolder(view);
    }



    public void onBindViewHolder(@NonNull  Stock_News_Adapter.ViewHolder holder, int position) {
        final StockNewsModel mainNews1 = mainNews.get(position);

        holder.title1.setText(mainNews1.getTitle());

        holder.source1.setText(mainNews1.getSource());

        holder.knowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenWeb.class);
                intent.putExtra("link",mainNews1.getLink());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mainNews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title1,source1;
        TextView knowmore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title1=itemView.findViewById(R.id.title1);
            source1=itemView.findViewById(R.id.source1);
            knowmore=itemView.findViewById(R.id.link1);

        }
    }
}

