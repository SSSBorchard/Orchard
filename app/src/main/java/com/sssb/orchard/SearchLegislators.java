package com.sssb.orchard;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Shreya on 5/19/18.
 */

public class SearchLegislators extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_legislators);

        Intent intent = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView legislators = findViewById(R.id.legislators);
        final ArrayList<String> legList = new ArrayList();
        final ArrayList<String> idList = new ArrayList();
        String code = new String();

        Log.v("hello down there", "huya friends");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            String res = Util.get("https://congres.herokuapp.com/listPeople/115/house/");
            res +=  Util.get("https://congres.herokuapp.com/listPeople/115/senate/");

            Log.v("result", res);

            Log.d("Parse", "Gets Here. ");

            String[] people = res.split("\n");
            for (String person : people) {
                String[] arr = person.split(",");
                code = arr[0];
                Log.v(person, "" + arr.length);
                if (arr.length >= 3) {
                    legList.add(arr[1]);
                    idList.add(arr[0]);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("failed", "");
        }

        Log.v("hello", "" + legList.size());


        //ArrayAdapter adapter = new ArrayAdapter<String>(legislators.getContext(), android.R.layout.simple_list_item_1, legList);
        listLegislatorAdaptor adapter = new listLegislatorAdaptor(legList, idList, this);

        legislators.setAdapter(adapter);

//        legislators.setOnItemClickListener(new AdapterView.onItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
//                Intent appInfo = new Intent(SearchLegislators.this, LegislatorInfo.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("code",code);
//                startActivity(appInfo);
//            }
//        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_home)
        {
            Intent cinemaIntent = new Intent(this, Home.class);
            startActivity(cinemaIntent);
        }
        else if (id == R.id.nav_search_legislators) {
            Intent cinemaIntent = new Intent(this, SearchLegislators.class);
            startActivity(cinemaIntent);
        }
        else if (id == R.id.nav_search_bills)
        {
            Intent cinemaIntent = new Intent(this, SearchBills.class);
            startActivity(cinemaIntent);
        }
        else if (id == R.id.nav_your_state)
        {
            Intent cinemaIntent = new Intent(this, YourState.class);
            startActivity(cinemaIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}