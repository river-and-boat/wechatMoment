package com.thoughtworks.wechatmoment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.thoughtworks.wechatmoment.adapter.WeChatItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String FOLD_TITLE = "朋友圈";
    public static final String UNFOLD_TITLE = "";

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private WeChatItemAdapter mWeChatItemAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private AppBarLayout mAppBarLayout;

    private List<String> mDataSource;

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

        mDataSource = new ArrayList<>();
        initData();

        mRecyclerView = findViewById(R.id.we_chat_container);
        mWeChatItemAdapter = new WeChatItemAdapter(mDataSource);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mWeChatItemAdapter);

        addAppBarListener();
    }

    private void initToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mDataSource.add("ITEM " + i);
        }
    }

    private void addAppBarListener() {
        mAppBarLayout = findViewById(R.id.appBar);
        mAppBarLayout.addOnOffsetChangedListener((bar, offset) -> {
            CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
            int color = Color.argb(255, 255, 255, 255);
            collapsingToolbarLayout.setCollapsedTitleTextColor(color);
            ImageView avatar = findViewById(R.id.avatar);
            if (Math.abs(offset) >= bar.getTotalScrollRange()) {
                // 折叠状态
                collapsingToolbarLayout.setTitle(FOLD_TITLE);
                avatar.setVisibility(View.GONE);
            } else {
                // 展开状态
                collapsingToolbarLayout.setTitle(UNFOLD_TITLE);
                avatar.setVisibility(View.VISIBLE);
            }
        });
    }
}