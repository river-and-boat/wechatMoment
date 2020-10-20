package com.thoughtworks.wechatmoment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.thoughtworks.wechatmoment.viewmodel.WeChatItemViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String FOLD_TITLE = "朋友圈";
    public static final String UNFOLD_TITLE = "";

    private WeChatItemAdapter weChatItemAdapter;
    private List<WeChatItemViewModel> dataSource;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;

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
        initToolBar();
        initData();
        initWeChatAdapter();
        initRecycleView();
        initSwipeRefreshLayout();
        addAppBarListener();

        admireOperation = new AdmireOperation();
        commentOperation = new CommentOperation();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initRecycleView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.we_chat_container);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(weChatItemAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    private void initSwipeRefreshLayout() {
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_layout);
        Handler handler = new Handler();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            weChatItemAdapter.addItems(new WeChatItemViewModel("新的测试用户",
                    "今天真是开心的一天啊，这是一个新的测试用户"));
            handler.postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 1000);
        });
    }

    private void initWeChatAdapter() {
        weChatItemAdapter = new WeChatItemAdapter(dataSource);
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
                        EditText commentInput = v.findViewById(R.id.comment_input);
                        Button commentSendButton = v.findViewById(R.id.send_comment);
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

    private void initData() {
        dataSource = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            dataSource.add(new WeChatItemViewModel("小江爱学术", "今天真是开心的一天啊，吃了很久没吃的垃圾食品大炸鸡，嘻嘻"));
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