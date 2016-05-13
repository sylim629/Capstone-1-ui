package com.example.leanne.capstonedesign_1_;

/**
 * Created by Chloe on 4/13/2016.
 * 랭킹 화면에 나타나는 각 아이템에 출력될 내용물들 정의 클래스
 */
public class ListViewItem {
    public final String id;
    public final String major;
    public final String wish_duty;
    public final String certificates;
    public final String toeicScore;
    public boolean isFav;

    public ListViewItem(String id, String major, String wish_duty, String certificates, String toeicScore, boolean isFav) {
        this.id = id;
        this.major = major;
        this.wish_duty = wish_duty;
        this.certificates = certificates;
        this.toeicScore = toeicScore;
        this.isFav = isFav;
    }

    public String getId() {
        return id;
    }
}
