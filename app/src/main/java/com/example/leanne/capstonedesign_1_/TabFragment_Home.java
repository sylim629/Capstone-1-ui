package com.example.leanne.capstonedesign_1_;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
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
public class TabFragment_Home extends ListFragment {

    private List<ListViewItem> mItems;        // ListView items list
    private LoggedInUser loggedInUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<>();

        loggedInUser = new LoggedInUser();
        // 내가 테스트 하기 위해 그냥 만든 임의의 String
        String rankingResult = "5;id1;toeic1;age1;major1;company_type1;duty1;company_name1;gender1;univ1;certificate1;isEmp;id2;toeic2;age2;major2;company_type2;duty2;company_name2;gender2;univ2;certificate2;isEmp;id3;toeic3;age3;major3;company_type3;duty3;company_name3;gender3;univ3;certificate3;isEmp;id4;toeic4;age4;major4;company_type4;duty4;company_name4;gender4;univ4;certificate4;isEmp;id5;toeic5;age5;major5;company_type5;duty5;company_name5;gender5;univ5;certificate5;isEmp";
        int topN = 0;
        String[] tokens = rankingResult.split(";");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("!")) tokens[i] = "";
        }
        topN = Integer.parseInt(tokens[0]);
        int j = 0;
        ArrayList<String> idFavs = new ArrayList<String>();

        idFavs = loggedInUser.getFav_ids();
        boolean isFav = false;
        int ranking = 0;

        for (int i = 1; j < topN; i += 11, j++) {
            ranking = i;
            String idInfo = new String("ID : ");
            for (int k = 0; k < idFavs.size(); k++) {
                if (idInfo.equals(idFavs.get(k)))    // LoggedInUser의 Fav ID와 비교해서 일치하면 isFav = true. 아니면 false
                    isFav = true;
                else
                    isFav = false;
            }
            String majorInfo = new String("전공 : ");
            String dutyInfo = new String("직무 : ");
            String certifiInfo = new String("자격증 : ");
            String toeicInfo = new String("토익 :  ");

            idInfo += tokens[i];
            majorInfo += tokens[i + 3];
            dutyInfo += tokens[i + 5];
            certifiInfo += tokens[i + 9];
            toeicInfo += tokens[i + 1];
            certifiInfo = certifiInfo.replace("|", ",");
            mItems.add(new ListViewItem(ranking, idInfo, majorInfo, dutyInfo, certifiInfo, toeicInfo, isFav));  // 여기서 isFav도 같이 저장해서 넘김
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
