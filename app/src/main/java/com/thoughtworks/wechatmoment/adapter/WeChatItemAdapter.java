package com.thoughtworks.wechatmoment.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.wechatmoment.MainActivity;
import com.thoughtworks.wechatmoment.R;
import com.thoughtworks.wechatmoment.decoration.WeChatItemDecoration;
import com.thoughtworks.wechatmoment.viewmodel.WeChatItemViewModel;

import java.util.List;

import butterknife.OnClick;

public class WeChatItemAdapter
        extends RecyclerView.Adapter<WeChatItemAdapter.ViewHolder>
        implements View.OnClickListener {

    public static final String VIEW_NAME = "Admire Click";
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
        holder.admireIcon.setVisibility(View.GONE);
        holder.admireList.setText("");
        holder.admireButton.setTag(position);
    }

    @Override
    public int getItemCount() {
        return this.mWeChatItemList.size();
    }

    public void addItems(WeChatItemViewModel model) {
        this.mWeChatItemList.add(model);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, String viewName, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            switch (v.getId()) {
                case R.id.edit_button:
                    mOnItemClickListener.onItemClick(v, VIEW_NAME, position);
                    break;
                default:
                    break;
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username;

        public TextView content;

        public ImageView userAvatar;

        public Button admireButton;

        public ImageView admireIcon;

        public TextView admireList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            content = itemView.findViewById(R.id.content);
            userAvatar = itemView.findViewById(R.id.user_avatar);
            admireButton = itemView.findViewById(R.id.edit_button);
            admireIcon = itemView.findViewById(R.id.admire_icon);
            admireList = itemView.findViewById(R.id.admire_list);
            admireButton.setOnClickListener(WeChatItemAdapter.this);
        }
    }
}
