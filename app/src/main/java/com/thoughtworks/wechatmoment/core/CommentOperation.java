package com.thoughtworks.wechatmoment.core;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.wechatmoment.R;

public class CommentOperation {

    private EditText commentInput;
    private Button commentSendButton;

    private Handler cancelCommentHandler;
    private Handler addCommentHandler;

    public CommentOperation() {
        cancelCommentHandler = new android.os.Handler();
        addCommentHandler = new Handler();
    }

    public void expandCommentWeChatItem(InputMethodManager inputMethodManager,
                                        View chatItem, RecyclerView recyclerView) {
        addCommentHandler.postDelayed(() -> {
            commentInput = chatItem.findViewById(R.id.comment_input);
            commentSendButton = chatItem.findViewById(R.id.send_comment);
            commentInput.setVisibility(View.VISIBLE);
            commentSendButton.setVisibility(View.VISIBLE);

            // 弹出评论框
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            commentInput.setFocusableInTouchMode(true);
            commentInput.requestFocus();

            // 距离滑动
            scrollRecycleViewTopOfTypeWriting(chatItem, commentInput, recyclerView);
        }, 300);
    }

    public void shrinkCommentWeChatItem(InputMethodManager inputMethodManager) {
        if (commentInput != null && commentSendButton != null && inputMethodManager != null) {
            cancelCommentHandler.postDelayed(() -> {
                commentInput.setVisibility(View.GONE);
                commentSendButton.setVisibility(View.GONE);
                inputMethodManager
                        .hideSoftInputFromWindow(commentInput.getWindowToken(), 0);
            }, 200);
        }
    }

    private void scrollRecycleViewTopOfTypeWriting(View chatItem, TextView commentInput,
                                                   RecyclerView recyclerView) {
        int[] chatItemPosition = new int[2];
        int[] commentInputPosition = new int[2];
        chatItem.getLocationOnScreen(chatItemPosition);
        commentInput.getLocationOnScreen(commentInputPosition);

        Log.d("SCROLL", "y1:" + chatItemPosition[1]);
        Log.d("SCROLL", "y2:" + commentInputPosition[1]);

        recyclerView.scrollBy(0, chatItemPosition[1] - commentInputPosition[1]);
    }
}
