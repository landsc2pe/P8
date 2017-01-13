package com.example.homin.p8.interfaces.base;

/**
 * Created by HOMIN on 2017-01-10.
 **/

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
