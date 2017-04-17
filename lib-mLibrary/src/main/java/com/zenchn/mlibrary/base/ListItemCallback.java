package com.zenchn.mlibrary.base;

/**
 * 作者：wangr on 2016/12/30 11:01
 * 描述： 
 */

public abstract class ListItemCallback<T> {

    public void onItemClick(int position, T model, int tag) {}

    public void onItemLongClick(int position, T model, int tag) {}
}
