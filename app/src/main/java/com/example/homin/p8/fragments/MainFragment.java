package com.example.homin.p8.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homin.p8.R;
import com.example.homin.p8.interfaces.MainMVP;
import com.example.homin.p8.presenters.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HOMIN on 2017-01-10.
 **/

public class MainFragment extends Fragment implements MainMVP.View{
    public static final String TAG = MainFragment.class.getSimpleName();
    MainMVP.Presenter mPresenter;

    @BindView(R.id.button2) TextView mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter = new MainPresenter();
        setPresenter(mPresenter);

        init();
    }

    private void init() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment chatFragment = new ChatFragment();
                FragmentTransaction transaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container_main_fragment, chatFragment, ChatFragment.TAG);
                transaction.addToBackStack(ChatFragment.TAG);
                transaction.commit();

            }
        });
    }

    @Override
    public void setPresenter(MainMVP.Presenter presenter) {
        presenter.onCreate();
    }
}
