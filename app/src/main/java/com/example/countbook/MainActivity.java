package com.example.countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ListView oldCountersList;

    private ArrayList<Counter> CountList;
    private ArrayAdapter<Counter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button createButton = (Button) findViewById(R.id.create);
        TextView title = (TextView) findViewById(R.id.oldCountersTitle);

        //adapted from https://stackoverflow.com/questions/14175153/how-to-make-my-listview-items-clickable
        //25-09-2017
        oldCountersList = (ListView) findViewById(R.id.oldCountersList);
        oldCountersList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CounterDetail.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                setResult(RESULT_OK);
                startActivity(new Intent(MainActivity.this,CreateCounterActivity.class));
            }
        });
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        TextView title = (TextView) findViewById(R.id.oldCountersTitle);
        title.setText("Counters: "+CountList.size());
        adapter = new ArrayAdapter<Counter>(this,
                R.layout.list_item, CountList);
        oldCountersList.setAdapter(adapter);

    }

    /**
     * Loads list of counters from file
     */
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
