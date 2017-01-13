package com.example.homin.p8.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homin.p8.R;
import com.example.homin.p8.models.ChatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOMIN on 2017-01-10.
 **/

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    public static final String TAG = ChatAdapter.class.getSimpleName();
    private List<ChatData> mChatDataList;

    public ChatAdapter() {
        mChatDataList = new ArrayList<>();
        Log.d(TAG, "List Size : " + mChatDataList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mChatDataList != null && mChatDataList.size() != 0) {
            ChatData textData = mChatDataList.get(position);
            Log.d(TAG, "List Size2 : " + mChatDataList.size());
            holder.textView.setText(textData.getUserName() + ": " + textData.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        if (mChatDataList == null) {
            return 0;
        } else {
            return mChatDataList.size();
        }
    }

    public void setChatData(ChatData chatData) {
        mChatDataList.add(chatData);
        Log.i(TAG, "Set Chat Data");
    }

    public int getDataSize() {
        if (mChatDataList == null) {
            return 0;
        } else {
            return mChatDataList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.text_chat_output);
        }
    }
}
