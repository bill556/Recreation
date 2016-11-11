package com.bill.recreation.listener;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface RequestCallBack<T> {

    void beforeRequest();

    void success(T data);

    void onError(String errorMsg);
}
