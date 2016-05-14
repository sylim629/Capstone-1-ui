package com.example.leanne.capstonedesign_1_;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Chloe on 4/13/2016.
 * 홈화면 랭킹 페이지. 랭킹 탭
 */
public class TabFragment_Home extends ListFragment{

    private List<ListViewItem> mItems;        // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<>();

        // 내가 테스트 하기 위해 그냥 만든 임의의 String
        String rankingResult = "5;kwjel88;885;29;컴퓨터공학부;company_type1;웹기획∙웹마케팅∙PM;company_name1;gender1;univ1;정보처리기사;isEmp;iammeee;795;22;컴퓨터공학부;company_type2;통신∙모바일;company_name2;gender2;univ2;정보처리기사|정보보안기사;isEmp;qwerty101;835;27;컴퓨터공학부;company_type3;서버∙네트워크∙보안;company_name3;gender3;univ3;정보처리기사|정보보안기사;isEmp;gotrules;985;26;컴퓨터공학부;company_type4;시스템프로그래머;company_name4;gender4;univ4;정보처리기사;isEmp;id5;toeic5;age5;major5;company_type5;duty5;company_name5;gender5;univ5;certificate5;isEmp";
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
/*
        // 일단은 컴공 밖에 선택 못 하니까
        mItems.add(new ListViewItem(1, "id_1", "컴퓨터공학부", "시스템프로그래머", "자격증..?", "900", false));
        mItems.add(new ListViewItem(2, "id_2", "컴퓨터공학부", "웹프로그래머", "자격증..?", "900", false));
        mItems.add(new ListViewItem(3, "id_3", "컴퓨터공학무", "응용프로그래머", "자격증..?", "900", false));
        mItems.add(new ListViewItem(4, "id_4", "컴퓨터공학부", "하드웨어/소프트웨어", "자격증..?", "900", false));
        mItems.add(new ListViewItem(5, "id_5", "컴퓨터공학부", "데이터베이스DBA", "자격증..?", "900", false));
*/

        // initialize and set the list adapter
        setListAdapter(new ListViewDemoAdaptor(getActivity(), mItems));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        ListViewItem item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.id, Toast.LENGTH_SHORT).show();
    }
}
