package com.dafay.demo.lib.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public LayoutInflater layoutInflater;
    public Context context;
    public List<T> datas;

    public BaseAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
        //设置为true,onbindview不执行，？？
        //setHasStableIds(true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void addData(T newData) {
        final int insertRangeStart = getItemCount();
        datas.add(newData);
        notifyItemRangeInserted(insertRangeStart, 1);
    }

    public void addDatas(List<T> newDatas) {
        final int insertRangeStart = getItemCount();
        datas.addAll(newDatas);
        notifyItemRangeInserted(insertRangeStart, newDatas.size());
    }

    public void addFirstItem(T item) {
        datas.add(0, item);
        notifyItemRangeInserted(0, 1);
    }

    public void setDatas(List<T> newDatas) {
        datas.clear();
        datas.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    public void refreshDatas(List<T> newDatas) {
        datas.addAll(0, newDatas);
        notifyItemRangeInserted(0, newDatas.size());
    }

    public void removeItem(int position) {
        notifyItemRemoved(position);
        datas.remove(position);
    }

    public void removeItemWithNodify(int position) {
        datas.remove(position);
        notifyDataSetChanged();
    }

    public T getItem(int i) {
        if (datas == null || datas.size() == 0) {
            return null;
        }
        if (i < 0 || i >= datas.size()) {
            return null;
        }
        return datas.get(i);
    }

    public T getFirstItem() {
        if (datas != null && datas.size() > 0) {
            return datas.get(0);
        } else {
            return null;
        }
    }

    public T getLastItem() {
        if (datas != null && datas.size() > 0) {
            return datas.get(datas.size() - 1);
        } else {
            return null;
        }
    }

}






