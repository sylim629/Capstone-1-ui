package com.example.leanne.capstonedesign_1_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chloe on 4/13/2016.
 */
public class ListViewDemoAdaptor extends ArrayAdapter<ListViewItem> {

    public ListViewDemoAdaptor(Context context, List<ListViewItem> items) {
        super(context, R.layout.listview_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.rankings_id);
            viewHolder.major = (TextView) convertView.findViewById(R.id.rankings_major);
            viewHolder.wish_duty = (TextView) convertView.findViewById(R.id.rankings_wish_duty);
            viewHolder.certificates = (TextView) convertView.findViewById(R.id.rankings_certificates);
            viewHolder.toeicScore = (TextView) convertView.findViewById(R.id.rankings_toeicScore);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        ListViewItem item = getItem(position);
        viewHolder.id.setText(item.id);
        viewHolder.major.setText(item.major);
        viewHolder.wish_duty.setText(item.wish_duty);
        viewHolder.certificates.setText(item.certificates);
        viewHolder.toeicScore.setText(item.toeicScore);

        return convertView;
    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */
    private static class ViewHolder {
        TextView id, major, wish_duty, certificates, toeicScore;
    }
}
