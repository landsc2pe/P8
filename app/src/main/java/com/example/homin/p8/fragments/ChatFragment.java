package com.example.homin.p8.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homin.p8.R;
import com.example.homin.p8.adapter.ChatAdapter;
import com.example.homin.p8.models.ChatData;
import com.example.homin.p8.utils.LogTag;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HOMIN on 2017-01-10.
 **/

public class ChatFragment extends Fragment {
    public static final String TAG = ChatFragment.class.getSimpleName();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChatAdapter adapter;

    @BindView(R.id.view_chat) RecyclerView chatView;
    @BindView(R.id.text_chat_input) EditText chatText;
    @BindView(R.id.button_send) TextView chatSend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        setRecyclerView();
        setFireBase();
    }
    
    private void setFireBase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(LogTag.DEBUG)Log.i(TAG, "On Child Added !");
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                adapter.setChatData(chatData);
                adapter.notifyDataSetChanged();
                chatView.scrollToPosition(adapter.getDataSize() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if(LogTag.DEBUG)Log.i(TAG, "On Child Changed !");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                if(LogTag.DEBUG)Log.i(TAG, "On Child Removed !");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                if(LogTag.DEBUG)Log.i(TAG, "On Child Moved !");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if(LogTag.DEBUG)Log.i(TAG, "On Child Cancelled !");
            }
        });
    }

    private void setRecyclerView() {
        adapter = new ChatAdapter();
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        chatView.setLayoutManager(layoutManager);
        chatView.setAdapter(adapter);

        chatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatData chatData = new ChatData("User", chatText.getText().toString());
                databaseReference.child("message").push().setValue(chatData);
                chatText.setText("");
                Log.i(TAG, "Click chatSend");
            }
        });
    }

}
