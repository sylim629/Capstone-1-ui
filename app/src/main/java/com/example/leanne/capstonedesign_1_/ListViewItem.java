package com.example.leanne.capstonedesign_1_;

import android.graphics.drawable.Drawable;

/**
 * Created by Chloe on 4/13/2016.
 */
public class ListViewItem {
    public final int ranking;
    public final String id;
    public final String major;
    public final String wish_duty;
    public final String certificates;
    public final String toeicScore;
    public final boolean isFav;

    public ListViewItem(int ranking, String id, String major, String wish_duty, String certificates, String toeicScore, boolean isFav) {
        this.ranking = ranking;
        this.id = id;
        this.major = major;
        this.wish_duty = wish_duty;
        this.certificates = certificates;
        this.toeicScore = toeicScore;
        this.isFav = isFav;
    }

    public String getId(){
        return id;
    }
}
