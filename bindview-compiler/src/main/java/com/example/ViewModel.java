package com.example;

/**
 * Created by husongzhen on 17/11/10.
 */

public class ViewModel {
    private String viewType;
    private String id;

    public ViewModel(String viewType, String id) {
        this.viewType = viewType;
        this.id = id;
    }


    public String getViewType() {
        return viewType;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ViewModel{" +
                "viewType='" + viewType + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
