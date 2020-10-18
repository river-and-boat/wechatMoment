package com.thoughtworks.wechatmoment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.wechatmoment.R;
import com.thoughtworks.wechatmoment.viewmodel.WeChatItemViewModel;

import java.util.List;

public class WeChatItemAdapter extends RecyclerView.Adapter<WeChatItemAdapter.ViewHolder> {

    private List<String> mWeChatItemList;

    public WeChatItemAdapter(List<String> items) {
        this.mWeChatItemList = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.wechat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView.setText(this.mWeChatItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mWeChatItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.item_view);
        }
    }
}
