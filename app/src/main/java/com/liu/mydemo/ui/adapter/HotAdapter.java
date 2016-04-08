package com.liu.mydemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liu.mydemo.R;

import java.util.ArrayList;

/**
 * Created by jing on 2016/2/23.
 */
public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<String> list;

    public HotAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_item, parent, false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeHolder homeHolder = (HomeHolder) holder;
        homeHolder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder{
        private TextView tv;

        public HomeHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public void addList(ArrayList<String> addList){
        list.addAll(addList);
        notifyDataSetChanged();
    }

    public void addObject(String s){
        list.add(s);
        notifyDataSetChanged();
    }

    public void clearAll(){
        list.clear();
    }

    public void clearObject(String s){
        list.remove(s);
        notifyDataSetChanged();
    }
}
