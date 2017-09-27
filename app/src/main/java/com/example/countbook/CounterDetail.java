package com.example.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class CounterDetail extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<Counter> CountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_detail);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        loadFromFile();
        Counter counter = CountList.get(position);
        String comment = counter.getComment();
        String name = counter.getName();
        String date = counter.getDate();
        int currentValue = counter.getCurrentValue();
        int initialValue = counter.getInitialValue();
        TextView Name = (TextView) findViewById(R.id.Name);
        TextView Comment = (TextView) findViewById(R.id.Comment);
        TextView Count = (TextView) findViewById(R.id.Count);
        TextView InitialValue = (TextView) findViewById(R.id.initialValue);
        TextView Date = (TextView) findViewById(R.id.Date);
        Button Reset = (Button) findViewById(R.id.Reset);
        Button Decrement = (Button) findViewById(R.id.Decrement);
        Button Increment = (Button) findViewById(R.id.Increment);

        Name.setText(name);
        Comment.setText(comment);
        Count.setText(Integer.toString(currentValue));
        InitialValue.setText("Started at: "+Integer.toString(initialValue));
        Date.setText("Last modified: "+date);





    }
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
}
