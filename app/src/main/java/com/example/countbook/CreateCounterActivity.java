package com.example.countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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


public class CreateCounterActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<Counter> CountList;
    private ArrayAdapter<Counter> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);
        loadFromFile();
        adapter = new ArrayAdapter<Counter>(this,
                R.layout.list_item, CountList);

    }
    public void createCounter(View view) {
        Intent intent = new Intent(CreateCounterActivity.this,MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.Name);
        EditText editText2 = (EditText) findViewById(R.id.initialVal);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        String comment = editText3.getText().toString();
        String name = editText.getText().toString();
        int value;
        if (editText2.getText().toString().trim().equals("")) {
            value = -1;
        }
        else {
            value = Integer.parseInt(editText2.getText().toString());
        }
        if (!comment.isEmpty() && !name.isEmpty() && value>=0) {
            Counter newCounter = new Counter(name,value,comment);
            CountList.add(newCounter);
        }
        else if (!name.isEmpty() && value>=0){
            Counter newCounter = new Counter(name, value);
            CountList.add(newCounter);
        }
        else {
            finish();
        }
        adapter.notifyDataSetChanged();
        saveInFile();
        finish();
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
