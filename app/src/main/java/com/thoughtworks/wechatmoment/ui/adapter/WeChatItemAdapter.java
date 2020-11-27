package com.thoughtworks.wechatmoment.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.thoughtworks.wechatmoment.R;
import com.thoughtworks.wechatmoment.db.entity.ChatMomentEntity;
import com.thoughtworks.wechatmoment.ui.MainActivity;
import com.thoughtworks.wechatmoment.ui.viewmodel.CommentViewModel;
import com.thoughtworks.wechatmoment.ui.viewmodel.WeChatItemViewModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeChatItemAdapter extends RecyclerView.Adapter<WeChatItemAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<ChatMomentEntity> chatMoments;
    private Context context;

    private View view;

    public static final String VIEW_NAME = "Admire Click";
    public static final String COMMENT = "COMMENT";
    public static final String ITEM = "ITEM";

    private CommentViewModel commentViewModel;

    private final int editButtonId = R.id.edit_button;
    private final int commentButtonId = R.id.comment;

    public WeChatItemAdapter(Context context) {
        this.context = context;
        commentViewModel = ViewModelProviders.of((MainActivity) context)
                .get(CommentViewModel.class);
    }

    public void setDataSource(List<ChatMomentEntity> chatMoments) {
        this.chatMoments = chatMoments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wechat_item, parent, false);
        this.view = view;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMomentEntity chatMomentEntity = chatMoments.get(position);
        String content = chatMoments.get(position).getContent();
        // todo 嵌套recycleview
        // List<ContentImage> images = chatMoments.get(position).get();

        WeChatCommentAdapter weChatCommentAdapter = new WeChatCommentAdapter(context);
        initWeChatCommentRecycleView(view, weChatCommentAdapter);
        // 嵌套adapter评论区
        commentViewModel.getComments(chatMomentEntity.getChatId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentEntities -> {
                    weChatCommentAdapter.setDataSource(commentEntities);
                    weChatCommentAdapter.notifyDataSetChanged();
                });

        holder.username.setText(chatMomentEntity.getUsername());
        holder.content.setText(content);

        Glide.with(context).asBitmap()
                .load(chatMomentEntity.getAvatar())
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

    public interface OnItemClickListener {
        void onItemClick(View v, String viewName, int position, String id);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    private void initWeChatCommentRecycleView(View view, WeChatCommentAdapter weChatCommentAdapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        RecyclerView commentRecycleView = view.findViewById(R.id.comment_list);
        commentRecycleView.setLayoutManager(linearLayoutManager);
        commentRecycleView.setAdapter(weChatCommentAdapter);
        commentRecycleView.addItemDecoration(
                new DividerItemDecoration(context, LinearLayout.VERTICAL));
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (onItemClickListener != null) {
            switch (v.getId()) {
                case editButtonId:
                    onItemClickListener.onItemClick(v, VIEW_NAME, position, chatMoments.get(position).getChatId());
                    break;
                case commentButtonId:
                    onItemClickListener.onItemClick(v, COMMENT, position, chatMoments.get(position).getChatId());
                    break;
                default:
                    onItemClickListener.onItemClick(v, ITEM, position, chatMoments.get(position).getChatId());
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
