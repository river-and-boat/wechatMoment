package com.thoughtworks.wechatmoment.ui.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class WeChatItemDecoration extends RecyclerView.ItemDecoration {

    private final Drawable mDrawable;

    public static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public WeChatItemDecoration(Context context, int orientation) {
        final TypedArray array = context.obtainStyledAttributes(ATTRS);
        mDrawable = array.getDrawable(0);
        array.recycle();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent,
                       @NonNull RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < parent.getChildCount(); i++) {
            View itemView = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            // 分割线的top和bottom
            int top = itemView.getBottom() + params.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if (position != Objects.requireNonNull(parent.getAdapter()).getItemCount() - 1) {
            outRect.set(0, 0, 0, mDrawable.getIntrinsicHeight());
        }
    }
}