package com.example.leanne.capstonedesign_1_;

/**
 * Created by Chloe on 4/11/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TabFragment_MyPage extends Fragment implements View.OnClickListener {

    private Button editMyInfo, editRankingSettings;
    private TextView textName, textId, textMajor, textWishCompType, textWishComp;
    private ImageButton settings;
    private String sName, sId, sMajor, sWishCompType, sWishComp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_mypage, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        editMyInfo = (Button)v.findViewById(R.id.button_edit_myinfo);
        editRankingSettings = (Button)v.findViewById(R.id.button_edit_rankings);
        settings = (ImageButton)v.findViewById(R.id.button_settings);
        editMyInfo.setOnClickListener(this);
        editRankingSettings.setOnClickListener(this);
        settings.setOnClickListener(this);

        textName = (TextView)v.findViewById(R.id.mypage_name);
        textId = (TextView)v.findViewById(R.id.mypage_id);
        textMajor = (TextView)v.findViewById(R.id.mypage_major);
        textWishCompType = (TextView)v.findViewById(R.id.mypage_wish_company_type);
        textWishComp = (TextView)v.findViewById(R.id.mypage_wish_company);

        showInfo();
    }

    private void showInfo() {
        // 서버에서 받은 값들로 TextView에서 보여주기
       sName = "null";
        sId = "null";
        sMajor = "null";
        sWishCompType = "null";
        sWishComp = "null";

        textName.setText(sName);
        textId.setText(sId);
        textMajor.setText(sMajor);
        textWishCompType.setText(sWishCompType);
        textWishComp.setText(sWishComp);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_edit_myinfo:
                Intent goEditMyInfo = new Intent(getActivity(), EditMyInfoActivity.class);
                startActivity(goEditMyInfo);
                getActivity().overridePendingTransition(
                        R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                break;
            case R.id.button_edit_rankings:
                Intent goEditRankings = new Intent(getActivity(), EditRankingsActivity.class);
                startActivity(goEditRankings);
                getActivity().overridePendingTransition(
                        R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                break;
            case R.id.button_settings:
                Intent goSettings = new Intent(getActivity(), SettingsActivity.class);
                startActivity(goSettings);
                getActivity().overridePendingTransition(
                        R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                break;
        }
    }

}