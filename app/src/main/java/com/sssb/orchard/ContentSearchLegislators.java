package com.sssb.orchard;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shreya on 5/20/18.
 */

public class ContentSearchLegislators extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search_legislators);

        final ListView legislators = findViewById(R.id.legislators);
        final List<String> legList = new ArrayList();
        final List<String> ids = new ArrayList<>();
        Log.v("hello down there", "huya friends");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            String res = Util.get("https://congres.herokuapp.com/listPeople/115/house/");

            Log.v("result", res);

            Log.d("Parse", "Gets Here. ");

            String[] people = res.split("\n");
            for (String person : people) {
                String[] arr = person.split(",");
                Log.v(person, "" + arr.length);
                if (arr.length >= 3) {
                    legList.add(arr[1]);
                    ids.add(arr[0]);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("failed", "");
        }

        Log.v("hello", "" + legList.size());


        ArrayAdapter adapter = new ArrayAdapter<String>(legislators.getContext(), android.R.layout.simple_list_item_1, legList);
        legislators.setAdapter(adapter);
        Log.v("hello", "" + legList.size());

    }
}
