package com.example.homin.p8.interfaces;

import com.example.homin.p8.interfaces.base.BasePresenter;
import com.example.homin.p8.interfaces.base.BaseView;

/**
 * Created by HOMIN on 2017-01-10.
 **/

public interface MainMVP {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void onCreate();

    }
}
