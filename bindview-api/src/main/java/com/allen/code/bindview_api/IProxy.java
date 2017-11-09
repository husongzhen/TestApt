package com.allen.code.bindview_api;

import android.view.View;

public interface IProxy<T> {
    /**
     * @param target 所在的类
     * @param root   查找 View 的地方
     */
    void inject(final T target, View root);
}  