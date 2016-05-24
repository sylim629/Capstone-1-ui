package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chloe on 4/13/2016.
 * 홈화면 랭킹 페이지. 랭킹 탭
 */
public class TabFragment_Home extends ListFragment{

    private List<ListViewItem> mItems;        // ListView items list
    private PopupWindow seeMorePopup;
    DisplayMetrics displaymetrics = new DisplayMetrics();
    int screenWidth, screenHeight;
    private String rankingResult;   // 여기에 서버에서 스트링 받기

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<>();

        // 테스트 하기 위해 그냥 만든 임의의 String
        rankingResult = "5;kwjel88;885;29;컴퓨터공학부;company_type1;웹기획∙웹마케팅∙PM;company_name1;gender1;univ1;정보처리기사;isEmp;iammeee;795;22;컴퓨터공학부;company_type2;통신∙모바일;company_name2;gender2;univ2;정보처리기사|정보보안기사;isEmp;qwerty101;835;27;컴퓨터공학부;company_type3;서버∙네트워크∙보안;company_name3;gender3;univ3;정보처리기사|정보보안기사;isEmp;gotrules;985;26;컴퓨터공학부;company_type4;시스템프로그래머;company_name4;gender4;univ4;정보처리기사;isEmp;id5;toeic5;age5;major5;company_type5;duty5;company_name5;gender5;univ5;certificate5;isEmp";
        int topN=0;
        String[] tokens = rankingResult.split(";");
        for(int i = 0 ; i < tokens.length ; i++){
            if(tokens[i].equals("!")) tokens[i]="";
        }
        topN = Integer.parseInt(tokens[0]);
        int j=0;
        ArrayList<String> idFavs = new ArrayList<String>();

        idFavs = LoggedInUser.getInstance().getFav_ids();

        boolean[] isFavArray = new boolean[topN];
        int k=0;
        for(int i=1; k<topN; i+=11, k++) {
            String id = tokens[i];
            for(int l=0; l<idFavs.size(); l++) {
                if (id.equals(idFavs.get(l))) {    // LoggedInUser의 Fav ID와 비교해서 일치하면 isFav = true. 아니면 false
                    isFavArray[k] = true;
                    break;
                }else
                    isFavArray[k] = false;
            }
        }

        for(int i = 1 ; j < topN ; i+=11, j++ ){
            String idInfo = new String("ID : ");
            String majorInfo = new String("전공 : ");
            String dutyInfo = new String("직무 : ");
            String certifiInfo = new String("자격증 : ");
            String toeicInfo = new String("토익 :  ");

            idInfo += tokens[i];
            majorInfo += tokens[i+3];
            dutyInfo += tokens[i+5];
            certifiInfo += tokens[i+9];
            toeicInfo += tokens[i+1];
            certifiInfo = certifiInfo.replace("|",",");
            mItems.add(new ListViewItem(idInfo,majorInfo,dutyInfo,certifiInfo,toeicInfo, isFavArray[j]));  // 여기서 isFav도 같이 저장해서 넘김
        }

        // initialize and set the list adapter
        setListAdapter(new ListViewDemoAdaptor(getActivity(), mItems));


        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Log.d("item position", Integer.toString(position)); // test code

        LayoutInflater inflaterPopupComp = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View seeMore = inflaterPopupComp.inflate(R.layout.activity_popup_seemore,
                (ViewGroup) getActivity().findViewById(R.id.popup_element));
        seeMorePopup = new PopupWindow(seeMore, screenWidth / 4 * 3, screenHeight / 4 * 3);
        seeMorePopup.showAtLocation(seeMore, Gravity.CENTER, 0, 0);
        seeMorePopup.setFocusable(true);
        seeMorePopup.update();

        Button btnDismiss = (Button) seeMore.findViewById(R.id.dismiss);
        btnDismiss.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                seeMorePopup.dismiss();
            }
        });

        // get actual data from clicked item
        ListViewItem item = mItems.get(position);
        String item_id = item.getId();
        String item_major = item.getMajor();
        String item_wishduty = item.getWish_duty();
        String item_certificates = item.getCertificates();
        String item_toeicScore = item.getToeicScore();


        // declare items in popup
        TextView seeMore_num = (TextView) seeMore.findViewById(R.id.ranking_num);
        TextView seeMore_id = (TextView) seeMore.findViewById(R.id.rankings_id);
        TextView seeMore_major = (TextView) seeMore.findViewById(R.id.rankings_major);
        TextView seeMore_wishduty = (TextView) seeMore.findViewById(R.id.rankings_wish_duty);
        TextView seeMore_certificates = (TextView) seeMore.findViewById(R.id.rankings_certificates);
        TextView seeMore_toeicScore = (TextView) seeMore.findViewById(R.id.rankings_toeicScore);

        // set textView with actual data text
        seeMore_num.setText(Integer.toString(position+1));
        seeMore_id.setText(item_id);
        seeMore_major.setText(item_major);
        seeMore_wishduty.setText(item_wishduty);
        seeMore_certificates.setText(item_certificates);
        seeMore_toeicScore.setText(item_toeicScore);
    }
}
