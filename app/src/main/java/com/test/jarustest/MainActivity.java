package com.test.jarustest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.jarustest.model.Assignment;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext());
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Assignment>>() { }.getType();

        List<Assignment> assignments = gson.fromJson(jsonFileString, listUserType);
        if (assignments != null) {
            for (int i = 0; i < assignments.size(); i++) {
                Log.i("data", "> Item " + i + "\n" + assignments.get(i));
            }
        }
    }
}