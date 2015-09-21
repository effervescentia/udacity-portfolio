package com.tkstr.portfolio;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static java.util.Arrays.asList;

public class PortfolioActivity extends AppCompatActivity {

    private List<String> apps = asList("spotify streamer", "scores app", "library app",
            "build it bigger", "xyz reader", "capstone: my own app");
    private List<String> appsSimple = asList("spotify", "scores", "library", "build it bigger", "reader", "capstone");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        setTitle("My App Portfolio");

        ListView listView = (ListView) findViewById(R.id.list_portfolio);
        AppListAdapter listAdapter = new AppListAdapter(getApplicationContext(), apps, appsSimple);
        listView.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_portfolio, menu);
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

    private class AppListAdapter extends ArrayAdapter<String> {

        private static final int REGULAR_APP = 1;
        private static final int CAPSTONE_APP = 2;

        private List<String> messages;

        public AppListAdapter(Context context, List<String> objects, List<String> messages) {
            super(context, -1, objects);
            this.messages = messages;
        }

        @Override
        public int getItemViewType(int position) {
            return position < getCount() - 1 ? REGULAR_APP : CAPSTONE_APP;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View row = inflater.inflate(getItemViewType(position) == REGULAR_APP ?
                    R.layout.app_list_item : R.layout.capstone_list_item, parent, false);
            Button button = (Button) row.findViewById(R.id.btn_app);
            button.setText(getItem(position));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Portfolio", "trying to toast");
                    Toast.makeText(getApplicationContext(), "This button will launch my " + messages.get(position) + " app", Toast.LENGTH_SHORT).show();
                }
            });

            return row;
        }
    }
}
