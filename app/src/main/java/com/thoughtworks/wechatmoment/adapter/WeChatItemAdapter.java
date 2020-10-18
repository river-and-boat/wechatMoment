package com.thoughtworks.wechatmoment.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.wechatmoment.R;
import com.thoughtworks.wechatmoment.viewmodel.WeChatItemViewModel;

import java.util.List;

public class WeChatItemAdapter extends RecyclerView.Adapter<WeChatItemAdapter.ViewHolder> {

    private List<WeChatItemViewModel> mWeChatItemList;

    public WeChatItemAdapter(List<WeChatItemViewModel> items) {
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
        holder.username.setText(this.mWeChatItemList.get(position).getUsername());
        holder.content.setText(this.mWeChatItemList.get(position).getContent());
        holder.userAvatar.setImageResource(R.drawable.user_avatar);
    }

    @Override
    public int getItemCount() {
        return this.mWeChatItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username;

        public TextView content;

        public ImageView userAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            content = itemView.findViewById(R.id.content);
            userAvatar = itemView.findViewById(R.id.user_avatar);
        }
    }
}
