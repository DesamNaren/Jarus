package com.test.jarustest.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.jarustest.R;
import com.test.jarustest.databinding.ActivityMainBinding;
import com.test.jarustest.error_handler.ErrorHandlerInterface;
import com.test.jarustest.model.ChildAssignment;
import com.test.jarustest.model.ParentAssignment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssignmentActivity extends AppCompatActivity implements ErrorHandlerInterface {

    private ActivityMainBinding binding;
    private AssignmentViewModel assignmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        assignmentViewModel = new AssignmentViewModel();
        loadData();
    }

    @Override
    public void handleError(String msg) {
        Snackbar.make(binding.toolbarTop, msg, Snackbar.LENGTH_LONG).setAction(getString(R.string.retry), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        }).show();
    }

    private void loadData() {
        assignmentViewModel.getData(getApplicationContext()).observe(this, outputString -> {
            if (!TextUtils.isEmpty(outputString)) {
                Type listUserType = new TypeToken<List<ChildAssignment>>() {
                }.getType();
                List<ChildAssignment> totalAssignments = new Gson().fromJson(outputString, listUserType);
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
                if (parentListItems.size() > 0) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    binding.rvAssignments.setLayoutManager(layoutManager);
                    binding.rvAssignments.setAdapter(new AssignmentAdapter(getApplicationContext(), parentListItems));
                }else {
                    handleError(getString(R.string.no_data_found));
                }
            } else {
                handleError(getString(R.string.something));
            }
        });
    }
}