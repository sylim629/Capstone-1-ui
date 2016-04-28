package com.example.leanne.capstonedesign_1_;

import android.graphics.drawable.Drawable;

/**
 * Created by Chloe on 4/13/2016.
 */
public class ListViewItem {

    public final String id;
    public final String major;
    public final String wish_duty;
    public final String certificates;
    public final String toeicScore;

    public ListViewItem(String id, String major, String wish_duty, String certificates, String toeicScore) {
        this.id = id;
        this.major = major;
        this.wish_duty = wish_duty;
        this.certificates = certificates;
        this.toeicScore = toeicScore;
    }


}
