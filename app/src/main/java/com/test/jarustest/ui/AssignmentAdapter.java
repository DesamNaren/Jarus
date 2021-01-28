package com.test.jarustest.ui;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.test.jarustest.R;
import com.test.jarustest.databinding.ItemChildBinding;
import com.test.jarustest.databinding.ItemParentBinding;
import com.test.jarustest.model.ChildAssignment;
import com.test.jarustest.model.ParentAssignment;

import java.util.List;

public class AssignmentAdapter extends ExpandableRecyclerAdapter<AssignmentAdapter.MyParentViewHolder, AssignmentAdapter.MyChildViewHolder> {
    private final LayoutInflater mInflater;

    public AssignmentAdapter(Context context, List<ParentListItem> itemList) {
        super(itemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        ItemParentBinding itemParentBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_parent, viewGroup, false);

        return new MyParentViewHolder(itemParentBinding);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        ItemChildBinding itemChildBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_child, viewGroup, false);
        return new MyChildViewHolder(itemChildBinding);
    }

    @Override
    public void onBindParentViewHolder(MyParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        try {
            ParentAssignment parentAssignment = (ParentAssignment) parentListItem;
            parentViewHolder.itemParentBinding.setParentData(parentAssignment);

            if (parentAssignment.isChecked()) {
                parentViewHolder.itemParentBinding.cbMake.setImageDrawable(mInflater
                        .getContext().getResources().getDrawable(android.R.drawable.
                                checkbox_on_background));
            } else {
                parentViewHolder.itemParentBinding.cbMake.setImageDrawable(mInflater
                        .getContext().getResources().getDrawable(android.R.drawable.
                                checkbox_off_background));
            }

            parentViewHolder.itemParentBinding.cbMake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (parentAssignment.isChecked()) {
                        parentAssignment.setChecked(false);

                        parentViewHolder.itemParentBinding.cbMake.setImageDrawable(mInflater
                                .getContext().getResources().getDrawable(android.R.drawable.
                                        checkbox_off_background));
                    } else {
                        parentAssignment.setChecked(true);
                        parentViewHolder.itemParentBinding.cbMake.setImageDrawable(mInflater
                                .getContext().getResources().getDrawable(android.R.drawable.
                                        checkbox_on_background));
                    }
                }
            });
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder childViewHolder, int position, Object childListItem) {
        ChildAssignment childAssignment = (ChildAssignment) childListItem;
        childViewHolder.itemChildBinding.setChildData(childAssignment);
    }

    public static class MyParentViewHolder extends ParentViewHolder {

        ItemParentBinding itemParentBinding;

        public MyParentViewHolder(ItemParentBinding itemParentBinding) {
            super(itemParentBinding.getRoot());
            this.itemParentBinding = itemParentBinding;
        }
    }


    public static class MyChildViewHolder extends ChildViewHolder {
        ItemChildBinding itemChildBinding;
        public MyChildViewHolder(ItemChildBinding itemChildBinding) {
            super(itemChildBinding.getRoot());
            this.itemChildBinding = itemChildBinding;
        }
    }
}