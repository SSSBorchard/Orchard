package com.sssb.orchard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.sssb.orchard.R;

import java.util.ArrayList;

public class listLegislatorAdaptor extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> ids;
    private Context context;



    public listLegislatorAdaptor(ArrayList<String> list, ArrayList<String> ids, Context context) {
        this.list = list;
        this.context = context;
        this.ids = ids;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        //return list.get(pos).getId();
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.person_list_item, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
       // listItemText.setText(list.get(position));
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button viewBtn = (Button)view.findViewById(R.id.view_btn);

        viewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
//                list.remove(position); //or some other task
                //notifyDataSetChanged();

                Intent intent = new Intent(context, LegislatorInfo.class);
                context.startActivity(intent);
            }
        });


        return view;
    }
}