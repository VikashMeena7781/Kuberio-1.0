package com.vikash.kuberio10.SlideShow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.vikash.kuberio10.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> answer_list;
    private HashMap<String,List<String>> question_list;

    public ExpandableListViewAdapter(Context context, List<String> answer_list, HashMap<String,List<String>> question_list){
        this.context=context;
        this.answer_list=answer_list;
        this.question_list=question_list;
    }

    @Override
    public int getGroupCount() {
        return this.answer_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.question_list.get(this.answer_list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.answer_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.question_list.get(this.answer_list.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String chapterTitle = (String) getGroup(groupPosition);

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.question_list,null);

        }
        TextView chapterTv = convertView.findViewById(R.id.chapter_tv);
        chapterTv.setText(chapterTitle);

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String topicTitle = (String) getChild(groupPosition,childPosition);

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.answer_list,null);
        }
        TextView topicTv = convertView.findViewById(R.id.topics_tv);
        topicTv.setText(topicTitle);
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
