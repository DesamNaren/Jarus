package com.test.jarustest.ui;

import android.content.Context;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.jarustest.R;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AssignmentViewModel extends ViewModel {

    MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public LiveData<String> getData(Context context) {
        if (stringMutableLiveData != null) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(() -> stringMutableLiveData.postValue(getJsonFromAssets(context)));

            executorService.shutdown();

        }
        return stringMutableLiveData;
    }

    private String getJsonFromAssets(Context context) {
        String outputString;
        try {
            InputStream is = context.getAssets().open(context.getString(R.string.file_name));

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                outputString = new String(buffer, StandardCharsets.UTF_8);
            } else {
                outputString = new String(buffer, context.getString(R.string.format_name));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return outputString;
    }
}
