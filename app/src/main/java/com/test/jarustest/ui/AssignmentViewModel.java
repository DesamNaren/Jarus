package com.test.jarustest.ui;

import android.content.Context;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AssignmentViewModel extends ViewModel {

    MutableLiveData<String> output = new MutableLiveData<>();

    public LiveData<String> getData(Context context) {
        if (output != null) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                public void run() {
                    output.postValue(getJsonFromAssets(context));
                }
            });

            executorService.shutdown();

        }
        return output;
    }

    private String getJsonFromAssets(Context context) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open("assignment.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                jsonString = new String(buffer, StandardCharsets.UTF_8);
            } else {
                jsonString = new String(buffer, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
}
