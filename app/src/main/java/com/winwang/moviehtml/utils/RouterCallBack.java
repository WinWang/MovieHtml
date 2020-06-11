package com.winwang.moviehtml.utils;

import android.app.Activity;

/**
 * Created by WinWang on 2020/3/13
 * Description->
 */
public interface RouterCallBack {

    void onBefore(Activity from, Class<?> to);

    void onNext(Activity from, Class<?> to);

    void onError(Activity from, Class<?> to, Throwable throwable);
}
