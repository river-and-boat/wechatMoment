package com.thoughtworks.wechatmoment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thoughtworks.wechatmoment.R;
import com.thoughtworks.wechatmoment.model.ChatMoment;
import com.thoughtworks.wechatmoment.model.ContentImage;
import com.thoughtworks.wechatmoment.model.UserSender;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WeChatItemAdapter extends RecyclerView.Adapter<WeChatItemAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<ChatMoment> chatMoments;
    private Context context;

    public static final String VIEW_NAME = "Admire Click";
    public static final String COMMENT = "COMMENT";
    public static final String ITEM = "ITEM";

    private final int editButtonId = R.id.edit_button;
    private final int commentButtonId = R.id.comment;

    public WeChatItemAdapter(Context context,
                             List<ChatMoment> chatMoments) {
        this.chatMoments = chatMoments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wechat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserSender sender = chatMoments.get(position).getSender();
        String content = chatMoments.get(position).getContent();
        List<ContentImage> images = chatMoments.get(position).getImages();

        holder.username.setText(sender.getUsername());
        holder.content.setText(content);

        Glide.with(context).asBitmap()
                .load(sender.getAvatar())
                .into(holder.userAvatar);

        holder.admireIcon.setVisibility(View.GONE);
        holder.admireList.setText("");
        holder.editTime.setText("30分钟前");
        holder.admireList.setVisibility(View.GONE);
        holder.admireButton.setTag(position);
        holder.commentInput.setVisibility(View.GONE);
        holder.sendCommentButton.setVisibility(View.GONE);
        holder.commentButton.setTag(position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return this.chatMoments.size();
    }

    public void addItems(ChatMoment chatMoment) {
        this.chatMoments.add(chatMoment);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, String viewName, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (onItemClickListener != null) {
            switch (v.getId()) {
                case editButtonId:
                    onItemClickListener.onItemClick(v, VIEW_NAME, position);
                    break;
                case commentButtonId:
                    onItemClickListener.onItemClick(v, COMMENT, position);
                    break;
                default:
                    onItemClickListener.onItemClick(v, ITEM, position);
                    break;
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView content;
        public TextView editTime;
        public CircleImageView userAvatar;
        public Button admireButton;
        public Button commentButton;
        public ImageView admireIcon;
        public TextView admireList;
        public EditText commentInput;
        public Button sendCommentButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            content = itemView.findViewById(R.id.content);
            userAvatar = itemView.findViewById(R.id.user_avatar);
            editTime = itemView.findViewById(R.id.edit_time);
            admireButton = itemView.findViewById(R.id.edit_button);
            admireIcon = itemView.findViewById(R.id.admire_icon);
            admireList = itemView.findViewById(R.id.admire_list);
            commentInput = itemView.findViewById(R.id.comment_input);
            sendCommentButton = itemView.findViewById(R.id.send_comment);
            commentButton = itemView.findViewById(R.id.comment);
            admireButton.setOnClickListener(WeChatItemAdapter.this);
            commentButton.setOnClickListener(WeChatItemAdapter.this);
            itemView.setOnClickListener(WeChatItemAdapter.this);
        }
    }
}
