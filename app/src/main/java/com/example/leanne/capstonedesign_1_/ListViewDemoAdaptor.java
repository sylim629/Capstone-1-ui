package com.example.leanne.capstonedesign_1_;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.logging.LoggingMXBean;

/**
 * Created by Chloe on 4/13/2016.
 */
public class ListViewDemoAdaptor extends ArrayAdapter<ListViewItem> {

    LoggedInUser loggedInUser;

    public ListViewDemoAdaptor(Context context, List<ListViewItem> items) {
        super(context, R.layout.listview_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.ranking = (TextView) convertView.findViewById(R.id.ranking_num);
            viewHolder.id = (TextView) convertView.findViewById(R.id.rankings_id);
            viewHolder.major = (TextView) convertView.findViewById(R.id.rankings_major);
            viewHolder.wish_duty = (TextView) convertView.findViewById(R.id.rankings_wish_duty);
            viewHolder.certificates = (TextView) convertView.findViewById(R.id.rankings_certificates);
            viewHolder.toeicScore = (TextView) convertView.findViewById(R.id.rankings_toeicScore);
            viewHolder.fav = (ImageButton) convertView.findViewById(R.id.heart_white);

            // viewHolder.remFromFav = (ImageButton) convertView.findViewById(R.id.heart_red);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        final ListViewItem item = getItem(position);
        viewHolder.ranking.setText(Integer.toString(position + 1));
        viewHolder.id.setText(item.id);
        viewHolder.major.setText(item.major);
        viewHolder.wish_duty.setText(item.wish_duty);
        viewHolder.certificates.setText(item.certificates);
        viewHolder.toeicScore.setText(item.toeicScore);

        if (item.isFav == true)
            viewHolder.fav.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_red));
        else
            viewHolder.fav.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_white));

        viewHolder.fav.setOnClickListener(new View.OnClickListener() {  // 하트 클릭 리스너
            @Override
            public void onClick(View v) {
                if (item.isFav == true)   // isFav 값 잘 받아오는지 확인
                    Log.d("isFav", "true");
                else
                    Log.d("isFav", "false");
                Log.d("id check", item.getId());    // id 잘 받아오는지 확인
                if (item.isFav == false) {  // 흰 하트일 때
                    viewHolder.fav.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_red));    // 빨간 줄 뜨면 무시..
                    item.isFav = true;    // 클릭했으니 이제 isFav는 true
                    loggedInUser.setFav_ids(item.getId());
                    Toast.makeText(getContext(), "clicked white heart:" + item.getId() + "!", Toast.LENGTH_SHORT).show();
                } else {   // 빨간 하트일 때
                    viewHolder.fav.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_white));  // 빨간 줄 뜨면 무시..
                    item.isFav = false;   // 클릭했으니 다시 isFav는 false
                    loggedInUser.deleteFav_id(item.getId());
                    Toast.makeText(getContext(), "clicked red heart:" + item.getId() + "!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */
    private static class ViewHolder {
        TextView ranking, id, major, wish_duty, certificates, toeicScore;
        ImageButton fav;
    }
}
