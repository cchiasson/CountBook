package com.example.countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class CreateCounterActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<Counter> CountList;
    private ArrayAdapter<Counter> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);
    }
    public void createCounter(View view) {
        Intent intent = new Intent(CreateCounterActivity.this,MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String name = editText.getText().toString();
        int value = Integer.parseInt(editText2.getText().toString());
        Counter newCounter = new Counter(name,value);
        //PROBLEM STARTS HERE
        //CountList.add(newCounter);
        //adapter.notifyDataSetChanged();
        //saveInFile();
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

}
