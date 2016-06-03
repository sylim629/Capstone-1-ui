package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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
import java.util.concurrent.ExecutionException;

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

        //----------------
        // initialize the items list
        mItems = new ArrayList<ListViewItem>();
        /*Resources resources = getResources();

        RequestMsgSender userInfoMsgSender = (RequestMsgSender) new RequestMsgSender().execute("8;");
        String rankingResult = null;

        try {
            rankingResult = userInfoMsgSender.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        //-----------------
        /*// initialize the items list
        mItems = new ArrayList<>();
        // 테스트 하기 위해 그냥 만든 임의의 String*/
        rankingResult = "5;kwjel88;990;29;컴퓨터공학부;사기업;통신∙모바일;삼성전자;false;서울대학교;정보처리기사;0;4.12;4.5;|인턴:SKT:2|;" +
                "iammeee;980;27;컴퓨터공학부;공기업;데이터베이스 DBA;인천국제공항공사;true;서강대학교;정보처리기사;0;4.3;4.5;|인턴:KT:2|;" +
                "qwerty101;875;24;컴퓨터공학부;외국계기업;응용프로그래머;한국IBM;false;이화여자대학교;정보처리기사|정보보안기사;0;3.9;4.5;|인턴:네이버(구 NHN):2|;" +
                "gotrules;800;26;컴퓨터공학부;공기업;시스템프로그래머;한국산업은행;false;인하대학교;정보처리기사;0;3.8;4.0;|인턴:삼성디스플레이:2|;" +
                "bigbangtheory;795;25;컴퓨터공학부;사기업;응용프로그래머;LG전자;false;홍익대학교;정보처리기사;0;3.53;4.5;|인턴:신한카드:2|;";
        int topN=0;
        String[] tokens = rankingResult.split(";");
        for(int i = 0 ; i < tokens.length ; i++){
            if(tokens[i].equals("!")) tokens[i]="";
        }
        topN = Integer.parseInt(tokens[0]);
        int j=0;
        ArrayList<String> idFavs = new ArrayList<String>();

        idFavs = LoggedInUser.getLoggedinUser().getFav_ids();

        boolean[] isFavArray = new boolean[topN];
        int k=0;
        for(int i=1; k<topN; i+=14, k++) {
            String id = tokens[i];
            for(int l=0; l<idFavs.size(); l++) {
                if (id.equals(idFavs.get(l))) {    // LoggedInUser의 Fav ID와 비교해서 일치하면 isFav = true. 아니면 false
                    isFavArray[k] = true;
                    break;
                }else
                    isFavArray[k] = false;
            }
        }

        for(int i = 1 ; j < topN ; i+=14, j++ ){
            String idInfo = new String("ID : ");
            String majorInfo = new String("전공 : ");
            String dutyInfo = new String("직무 : ");
            String certifiInfo = new String("자격증 : ");
            String toeicInfo = new String("토익 :  ");
            String ageInfo = new String("나이 : ");
            String wishCompTypeInfo = new String("희망 회사 종류: ");
            String wishCompInfo = new String("희망 회사: ");
            String genderInfo = new String("성별: ");
            String uniInfo = new String("대학: ");
            String empInfo = new String("취업여부: ");
            String gpaInfo = new String("학점: ");
            String careerInfo = new String("경력: ");
            //---------------

            idInfo += tokens[i];
            majorInfo += tokens[i+3];
            dutyInfo += tokens[i+5];
            certifiInfo += tokens[i+9];
            toeicInfo += tokens[i+1];
            certifiInfo = certifiInfo.replace("|",",");
            ageInfo += tokens[i+2];
            wishCompTypeInfo += tokens[i+4];
            wishCompInfo += tokens[i+6];
            genderInfo += tokens[i+7];
            uniInfo += tokens[i+8];
            empInfo += tokens[i+10];
            gpaInfo += (tokens[i+11] + "/" + tokens[i+12]);
            careerInfo += alterCareer(tokens[i+13]);

            mItems.add(new ListViewItem(idInfo,majorInfo,dutyInfo,certifiInfo,toeicInfo, isFavArray[j], ageInfo, wishCompTypeInfo, wishCompInfo, genderInfo, uniInfo, empInfo, gpaInfo, careerInfo));  // 여기서 isFav도 같이 저장해서 넘김
        }

        // initialize and set the list adapter
        setListAdapter(new ListViewDemoAdaptor(getActivity(), mItems));


        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }

    public String alterCareer(String input){
        if(input.equals("!")) {
            return "";
        }
        String result = "";
        if( input != null ){
            input = input.substring(1, input.length()-1);
            String[] tokens = input.split(":",0);
            for(int j = 0 ; j < tokens.length ; j++ ){
                result += (tokens[j] + " " );
            }
        }
        result = result + "개월";
        return result;
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
        String item_age = item.getAge();
        String item_gender = item.getGender();
        String item_uni = item.getUni();
        String item_isEmp = item.getIsEmp();
        String item_wishCompType = item.getWish_comp_type();
        String item_wishComp = item.getWish_comp();
        String item_gpa = item.getGpa();
        String item_career = item.getCareer();

        // declare items in popup
        //TextView seeMore_num = (TextView) seeMore.findViewById(R.id.ranking_num);
        TextView seeMore_id = (TextView) seeMore.findViewById(R.id.rankings_id);
        TextView seeMore_major = (TextView) seeMore.findViewById(R.id.rankings_major);
        TextView seeMore_wishduty = (TextView) seeMore.findViewById(R.id.rankings_wish_duty);
        TextView seeMore_certificates = (TextView) seeMore.findViewById(R.id.rankings_certificates);
        TextView seeMore_toeicScore = (TextView) seeMore.findViewById(R.id.rankings_toeicScore);
        TextView seeMore_age = (TextView) seeMore.findViewById(R.id.rankings_age);
        TextView seeMore_gender = (TextView) seeMore.findViewById(R.id.rankings_gender);
        TextView seeMore_uni = (TextView) seeMore.findViewById(R.id.rankings_uni);
        TextView seeMore_wishCompType = (TextView) seeMore.findViewById(R.id.rankings_wish_comp_type);
        TextView seeMore_wishComp = (TextView) seeMore.findViewById(R.id.rankings_wish_comp);
        TextView seeMore_isEmp = (TextView) seeMore.findViewById(R.id.rankings_isEmp);
        TextView seeMore_gpa = (TextView) seeMore.findViewById(R.id.rankings_gpa);
        TextView seeMore_career = (TextView) seeMore.findViewById(R.id.rankings_career);

        // set textView with actual data text
        //seeMore_num.setText(Integer.toString(position+1));
        seeMore_id.setText(item_id);
        seeMore_major.setText(item_major);
        seeMore_wishduty.setText(item_wishduty);
        seeMore_certificates.setText(item_certificates);
        seeMore_toeicScore.setText(item_toeicScore);
        seeMore_age.setText(item_age);
        seeMore_gender.setText(item_gender);
        seeMore_uni.setText(item_uni);
        seeMore_wishCompType.setText(item_wishCompType);
        seeMore_wishComp.setText(item_wishComp);
        seeMore_isEmp.setText(item_isEmp);
        seeMore_gpa.setText(item_gpa);
        seeMore_career.setText(item_career);
    }
}
