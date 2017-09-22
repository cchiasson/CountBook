package com.example.countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/*
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
*/
public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    private ListView oldCountersList;

    private ArrayList<Counter> CountList;
    private ArrayAdapter<Counter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        oldCountersList = (ListView) findViewById(R.id.list_item);
        //oldCountersList.setOnItemClickListener(this);

        //ADD NEW COUNTER BUTTON
        FloatingActionButton addCounter = (FloatingActionButton) findViewById(R.id.fab);
        addCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                //Intent intent = new Intent(this, CreateCounterActivity.class);
                startActivity(new Intent(MainActivity.this,CreateCounterActivity.class));
                /*
                String name = bodyText.getText().toString();
                int value = Integer.parseInt(bodyText.getText().toString());
                Counter newCounter = new Counter(name,value);
                CountList.add(newCounter);*/
                //test???
                //adapter.notifyDataSetChanged();
                //loadFromFile();
            }
        });
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Counter>(this,
                R.layout.content_main, CountList);
        oldCountersList.setAdapter(adapter);
    }
    //CLICK ON COUNTER
    /*public void onItemClick(AdapterView<?> l,View v, int position,long id) {
        Log.i("HelloListView", "You clicked Item: "+id+" at position: "+position);
            Intent intent = new Intent();
        intent.setClass(this,CounterDetail.class);
        intent.putExtra("position",position);
        intent.putExtra("id",id);
        startActivity(intent);
    }*/
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/43345046/converting-a-json-string-to-an-arraylist-of-objects-with-gson
            //2017-09-19
            Type listType = new TypeToken<ArrayList<Counter>>() {
            }.getType();
            CountList = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            CountList = new ArrayList<Counter>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }*/
}
