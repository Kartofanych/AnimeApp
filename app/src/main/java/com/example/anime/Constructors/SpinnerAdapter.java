package com.example.anime.Constructors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anime.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public SpinnerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.popup_view_item, parent, false);
        TextView title = rootView.findViewById(R.id.title_spinner_text);
        title.setText(list.get(position));

        return rootView;
    }
}
