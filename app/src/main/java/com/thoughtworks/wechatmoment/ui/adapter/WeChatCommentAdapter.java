package com.thoughtworks.wechatmoment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.wechatmoment.R;
import com.thoughtworks.wechatmoment.db.entity.CommentEntity;

import java.util.List;

public class WeChatCommentAdapter extends RecyclerView.Adapter<WeChatCommentAdapter.ViewHolder> {

    private Context context;
    private List<CommentEntity> comments;

    public WeChatCommentAdapter(Context context) {
        this.context = context;
    }

    public void setDataSource(List<CommentEntity> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wechat_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentEntity comment = comments.get(position);
        holder.commentUserName.setText(comment.getUsername());
        holder.commentContent.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        if (comments != null) {
            return this.comments.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView commentUserName;
        public TextView commentContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentUserName = itemView.findViewById(R.id.comment_user_name);
            commentContent = itemView.findViewById(R.id.comment_content);
        }
    }
}
