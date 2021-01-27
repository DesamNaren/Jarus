package com.test.jarustest.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.jarustest.R;
import com.test.jarustest.model.ChildAssignment;
import com.test.jarustest.model.ParentAssignment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private String jsonFileString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssignmentViewModel assignmentViewModel = new AssignmentViewModel();
        assignmentViewModel.getData(getApplicationContext()).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                jsonFileString = s;
                Log.i("data", jsonFileString);

                Gson gson = new Gson();
                Type listUserType = new TypeToken<List<ChildAssignment>>() {
                }.getType();
                List<ChildAssignment> totalAssignments = gson.fromJson(jsonFileString, listUserType);

                List<ParentListItem> parentListItems = new ArrayList<>();

                for (int i = 0; i < Objects.requireNonNull(totalAssignments).size(); i++) {
                    List<ChildAssignment> childAssignments = new ArrayList<>();
                    ChildAssignment childAssignment = new ChildAssignment(
                            totalAssignments.get(i).getId(),
                            totalAssignments.get(i).getVin(),
                            totalAssignments.get(i).getYear(),
                            totalAssignments.get(i).getMake(),
                            totalAssignments.get(i).getValue(),
                            totalAssignments.get(i).getLength());
                    childAssignments.add(childAssignment);
                    ParentAssignment parentAssignment = new ParentAssignment(totalAssignments.get(i).getId(), totalAssignments.get(i).getMake());
                    parentAssignment.setChildItemList(childAssignments);

                    parentListItems.add(parentAssignment);
                }


                RecyclerView recyclerView = findViewById(R.id.rv_assignments);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new AssignmentAdapter(getApplicationContext(), parentListItems));
            }
        });

    }
}