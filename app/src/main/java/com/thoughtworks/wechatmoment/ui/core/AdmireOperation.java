package com.thoughtworks.wechatmoment.ui.core;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtworks.wechatmoment.R;

import java.util.HashMap;

public class AdmireOperation {
    private final HashMap<Integer, Boolean> admireStatus;

    public AdmireOperation() {
        admireStatus = new HashMap<>();
    }

    public void admireWeChatItem(String username, View chatItem, int position) {
        TextView admireList = ((TextView) chatItem.findViewById(R.id.admire_list));
        ImageView admireIcon = chatItem.findViewById(R.id.admire_icon);
        if (!admireStatus.containsKey(position)) {
            admireIcon.setVisibility(View.VISIBLE);
            admireList.setVisibility(View.VISIBLE);
            admireList.setText(username);
            admireStatus.put(position, true);
        } else {
            if (!admireStatus.get(position)) {
                admireIcon.setVisibility(View.VISIBLE);
                admireList.setVisibility(View.VISIBLE);
                admireList.setText(username);
                admireStatus.put(position, true);
            } else {
                admireIcon.setVisibility(View.GONE);
                admireList.setText("");
                admireList.setVisibility(View.GONE);
                admireStatus.put(position, false);
            }
        }
    }
}
