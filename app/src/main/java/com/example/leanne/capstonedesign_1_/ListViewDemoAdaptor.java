package com.example.leanne.capstonedesign_1_;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Image;
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

        if(convertView == null) {
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
            viewHolder.addToFav = (ImageButton) convertView.findViewById(R.id.heart_white);
            viewHolder.remFromFav = (ImageButton) convertView.findViewById(R.id.heart_red);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        ListViewItem item = getItem(position);
        viewHolder.ranking.setText(Integer.toString(item.ranking));
        viewHolder.id.setText(item.id);
        viewHolder.major.setText(item.major);
        viewHolder.wish_duty.setText(item.wish_duty);
        viewHolder.certificates.setText(item.certificates);
        viewHolder.toeicScore.setText(item.toeicScore);
        if(viewHolder.isFav == false) {       // 흰 하트이면
            viewHolder.addToFav.setVisibility(View.VISIBLE);
            viewHolder.remFromFav.setVisibility(View.GONE);
            viewHolder.addToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("id check", viewHolder.id.toString());
                    //System.out.println("clicked id: " + viewHolder.id.toString());  // check for id
                    //loggedInUser.addFav_id(viewHolder.id.toString());
                    viewHolder.addToFav.setVisibility(View.GONE);
                    viewHolder.remFromFav.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "clicked white heart:" + viewHolder.id.toString() + "!", Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.isFav = true;
        }
        else {                                  // 빨강 하트이면
            viewHolder.addToFav.setVisibility(View.GONE);
            viewHolder.remFromFav.setVisibility(View.VISIBLE);
            viewHolder.remFromFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("id check", viewHolder.id.toString());
                    //System.out.println("clicked id: " + viewHolder.id.toString());  // check for id
                    //loggedInUser.deleteFav_id(viewHolder.id.toString());
                    viewHolder.remFromFav.setVisibility(View.GONE);
                    viewHolder.addToFav.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "clicked red heart:" + viewHolder.id.toString() + "!", Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.isFav = false;
        }

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
        ImageButton addToFav, remFromFav;
        boolean isFav;   // 초기값은 일단 false(white heart)로
    }
}
