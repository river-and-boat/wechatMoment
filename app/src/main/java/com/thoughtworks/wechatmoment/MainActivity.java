package com.thoughtworks.wechatmoment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.thoughtworks.wechatmoment.adapter.WeChatItemAdapter;
import com.thoughtworks.wechatmoment.core.AdmireOperation;
import com.thoughtworks.wechatmoment.core.CommentOperation;
import com.thoughtworks.wechatmoment.model.ChatMoment;
import com.thoughtworks.wechatmoment.model.ContentImage;
import com.thoughtworks.wechatmoment.model.UserSender;
import com.thoughtworks.wechatmoment.viewmodel.WeChatItemViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String FOLD_TITLE = "朋友圈";
    public static final String UNFOLD_TITLE = "";

    private WeChatItemAdapter weChatItemAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;

    private WeChatItemViewModel chatItemViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    private AdmireOperation admireOperation;
    private CommentOperation commentOperation;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initChatItemViewModel();
        initToolBar();
        initWeChatAdapter();
        initRecycleView();
        initSwipeRefreshLayout();
        addAppBarListener();

        admireOperation = new AdmireOperation();
        commentOperation = new CommentOperation();
    }

    private void initRecycleView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.we_chat_container);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(weChatItemAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    private void initChatItemViewModel() {
        chatItemViewModel = ViewModelProviders.of(this).get(WeChatItemViewModel.class);
        chatItemViewModel.init();
        chatItemViewModel.getChatMomentList().observe(this, (Observer<List<ChatMoment>>)
                chatMoments -> weChatItemAdapter.notifyDataSetChanged());
        chatItemViewModel.getIsUpdating().observe(this, isUpdating -> {
            swipeRefreshLayout.setRefreshing(isUpdating);
            if (!isUpdating) {
                recyclerView.smoothScrollToPosition(chatItemViewModel
                        .getChatMomentList().getValue().size() - 1);
            }
        });
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            chatItemViewModel.addNewItems();
        });
    }

    private void initWeChatAdapter() {
        weChatItemAdapter = new WeChatItemAdapter(this,
                chatItemViewModel.getChatMomentList().getValue());
        weChatItemAdapter.setOnItemClickListener((v, viewName, position) -> {
            View chatItem = linearLayoutManager.findViewByPosition(position);
            InputMethodManager inputMethodManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (chatItem != null) {
                switch (v.getId()) {
                    case R.id.edit_button:
                        admireOperation.admireWeChatItem(viewName, chatItem, position);
                        break;
                    case R.id.comment:
                        commentOperation.shrinkCommentWeChatItem(inputMethodManager);
                        inputMethodManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        commentOperation.expandCommentWeChatItem(inputMethodManager, chatItem, recyclerView);
                        break;
                    default:
                        inputMethodManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        commentOperation.shrinkCommentWeChatItem(inputMethodManager);
                        break;
                }
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void addAppBarListener() {
        AppBarLayout appBarLayout = findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener((bar, offset) -> {
            CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
            int color = Color.argb(255, 255, 255, 255);
            collapsingToolbarLayout.setCollapsedTitleTextColor(color);
            ImageView avatar = findViewById(R.id.avatar);
            TextView username = findViewById(R.id.username);
            if (Math.abs(offset) >= bar.getTotalScrollRange()) {
                // 折叠状态
                collapsingToolbarLayout.setTitle(FOLD_TITLE);
                avatar.setVisibility(View.GONE);
                username.setVisibility(View.GONE);
            } else {
                // 展开状态
                collapsingToolbarLayout.setTitle(UNFOLD_TITLE);
                avatar.setVisibility(View.VISIBLE);
                username.setVisibility(View.VISIBLE);
            }
        });
    }
}