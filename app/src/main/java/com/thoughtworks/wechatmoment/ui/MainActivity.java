package com.thoughtworks.wechatmoment.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.thoughtworks.wechatmoment.R;
import com.thoughtworks.wechatmoment.db.entity.ChatMomentEntity;
import com.thoughtworks.wechatmoment.db.entity.CommentEntity;
import com.thoughtworks.wechatmoment.ui.adapter.WeChatCommentAdapter;
import com.thoughtworks.wechatmoment.ui.adapter.WeChatItemAdapter;
import com.thoughtworks.wechatmoment.ui.core.AdmireOperation;
import com.thoughtworks.wechatmoment.ui.core.CommentOperation;
import com.thoughtworks.wechatmoment.ui.viewmodel.UserViewModel;
import com.thoughtworks.wechatmoment.ui.viewmodel.WeChatItemViewModel;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String FOLD_TITLE = "朋友圈";
    public static final String UNFOLD_TITLE = "";

    private WeChatItemAdapter weChatItemAdapter;

    private LinearLayoutManager linearLayoutManager;

    private RecyclerView recyclerView;

    private WeChatItemViewModel chatItemViewModel;
    private UserViewModel userViewModel;

    private SwipeRefreshLayout swipeRefreshLayout;

    private AdmireOperation admireOperation;
    private CommentOperation commentOperation;

    private TextView username;
    private ImageView avatar;
    private ImageView profileImage;

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
        initWeChatAdapter();
        initChatItemViewModel();
        initWeChatItemRecycleView();
        initSwipeRefreshLayout();
        initUserInfo();
        addAppBarListener();
        admireOperation = new AdmireOperation();
        commentOperation = new CommentOperation();
    }

    private void initWeChatItemRecycleView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.we_chat_container);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(weChatItemAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    private void initChatItemViewModel() {
        chatItemViewModel = ViewModelProviders.of(this).get(WeChatItemViewModel.class);
        chatItemViewModel.getChatMomentList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chatMomentEntities -> {
                    weChatItemAdapter.setDataSource(chatMomentEntities);
                    weChatItemAdapter.notifyDataSetChanged();
                });
    }

    private void initUserInfo() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        avatar = findViewById(R.id.avatar);
        username = findViewById(R.id.username);
        profileImage = findViewById(R.id.back_image);
        userViewModel.getUserInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userEntity -> {
                    username.setText(userEntity.getNick());
                    Glide.with(MainActivity.this).asBitmap()
                            .load(userEntity.getAvatar()).into(avatar);
                    Glide.with(MainActivity.this).asBitmap()
                            .load(userEntity.getProfileImage()).into(profileImage);
                });
        profileImage.setOnClickListener(v -> {
//            UserEntity userEditing = userViewModel.getUserInfo().getValue();
//            userEditing.setProfileImage(TEST_USER_EDIT_PROFILE);
//            userViewModel.editUserInfo(userEditing);
        });
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            chatItemViewModel.addNewItems();
        });
    }

    private void initWeChatAdapter() {
        weChatItemAdapter = new WeChatItemAdapter(this);
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
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void addAppBarListener() {
        AppBarLayout appBarLayout = findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener((bar, offset) -> {
            CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
            int color = Color.argb(255, 255, 255, 255);
            collapsingToolbarLayout.setCollapsedTitleTextColor(color);
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