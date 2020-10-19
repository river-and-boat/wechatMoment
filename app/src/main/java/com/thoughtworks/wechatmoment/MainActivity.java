package com.thoughtworks.wechatmoment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.thoughtworks.wechatmoment.adapter.WeChatItemAdapter;
import com.thoughtworks.wechatmoment.viewmodel.WeChatItemViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String FOLD_TITLE = "朋友圈";
    public static final String UNFOLD_TITLE = "";

    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private WeChatItemAdapter mWeChatItemAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private AppBarLayout mAppBarLayout;

    private List<WeChatItemViewModel> mDataSource;

    private HashMap<Integer, Boolean> admireStatus;

    private Handler mHandler;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admireStatus = new HashMap<>();

        initToolBar();

        mDataSource = new ArrayList<>();
        initData();

        mRecyclerView = findViewById(R.id.we_chat_container);
        mSwipeRefreshLayout = findViewById(R.id.swipe_layout);

        initWeChatAdapter();

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mWeChatItemAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        mHandler = new Handler();
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            mWeChatItemAdapter.addItems(new WeChatItemViewModel("新的测试用户", "今天真是开心的一天啊，这是一个新的测试用户"));
            mHandler.postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 1000);
        });

        addAppBarListener();
    }

    private void initWeChatAdapter() {
        mWeChatItemAdapter = new WeChatItemAdapter(mDataSource);
        mWeChatItemAdapter.setOnItemClickListener((v, viewName, position) -> {
            View chatItem = mLinearLayoutManager.findViewByPosition(position);
            if (!admireStatus.keySet().contains(position)) {
                chatItem.findViewById(R.id.admire_icon).setVisibility(View.VISIBLE);
                ((TextView)chatItem.findViewById(R.id.admire_list)).setText(viewName);
                admireStatus.put(position, true);
            } else {
                if (!admireStatus.get(position)) {
                    chatItem.findViewById(R.id.admire_icon).setVisibility(View.VISIBLE);
                    ((TextView)chatItem.findViewById(R.id.admire_list)).setText(viewName);
                    admireStatus.put(position, true);
                } else {
                    chatItem.findViewById(R.id.admire_icon).setVisibility(View.GONE);
                    ((TextView)chatItem.findViewById(R.id.admire_list)).setText("");
                    admireStatus.put(position, false);
                }

            }
        });
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
        for (int i = 0; i < 2; i++) {
            mDataSource.add(new WeChatItemViewModel("小江爱学术", "今天真是开心的一天啊，吃了很久没吃的垃圾食品大炸鸡，嘻嘻"));
        }
    }

    private void addAppBarListener() {
        mAppBarLayout = findViewById(R.id.appBar);
        mAppBarLayout.addOnOffsetChangedListener((bar, offset) -> {
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