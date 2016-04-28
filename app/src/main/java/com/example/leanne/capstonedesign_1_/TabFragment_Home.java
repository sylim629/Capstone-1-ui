package com.example.leanne.capstonedesign_1_;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.example.leanne.capstonedesign_1_.ListViewDemoAdaptor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chloe on 4/13/2016.
 */
public class TabFragment_Home extends ListFragment {

    private List<ListViewItem> mItems;        // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();

        // 일단은 컴공 밖에 선택 못 하니까
        mItems.add(new ListViewItem("id_1", "컴퓨터공학부", "시스템프로그래머", "자격증..?", "900"));
        mItems.add(new ListViewItem("id_2", "컴퓨터공학부", "웹프로그래머", "자격증..?", "900"));
        mItems.add(new ListViewItem("id_3", "컴퓨터공학무", "응용프로그래머", "자격증..?", "900"));
        mItems.add(new ListViewItem("id_4", "컴퓨터공학부", "하드웨어/소프트웨어", "자격증..?", "900"));
        mItems.add(new ListViewItem("id_5", "컴퓨터공학부", "데이터베이스DBA", "자격증..?", "900"));

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
