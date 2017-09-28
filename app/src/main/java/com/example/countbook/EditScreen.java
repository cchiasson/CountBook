package com.example.countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class EditScreen extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<Counter> CountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);
        Intent intent = getIntent();
        final int position = intent.getIntExtra("position",0);

        loadFromFile();

        final Counter counter = CountList.get(position);
        String comment = counter.getComment();
        String name = counter.getName();
        int currentValue = counter.getCurrentValue();
        int initialValue = counter.getInitialValue();

        EditText Name = (EditText) findViewById(R.id.Name);
        EditText initialVal = (EditText) findViewById(R.id.initialVal);
        EditText currentVal = (EditText) findViewById(R.id.currentVal);
        EditText Comment = (EditText) findViewById(R.id.Comment);
        Button save = (Button) findViewById(R.id.save);

        Name.setHint("Name: "+name);
        initialVal.setHint("Initial Value: "+Integer.toString(initialValue));
        currentVal.setHint("Current Value: "+Integer.toString(currentValue));
        Comment.setHint("Comment: "+comment);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(CountList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
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
