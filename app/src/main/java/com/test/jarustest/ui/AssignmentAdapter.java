package com.test.jarustest.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.test.jarustest.R;
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
        View view = mInflater.inflate(R.layout.item_parent, viewGroup, false);
        return new MyParentViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.item_child, viewGroup, false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(MyParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        ParentAssignment parentAssignment = (ParentAssignment) parentListItem;
        parentViewHolder.idTv.setText(String.valueOf(parentAssignment.getMId()));
        parentViewHolder.makeTv.setText(parentAssignment.getMTitle());
        if (parentAssignment.isChecked()) {
            parentViewHolder.cbMake.setImageDrawable(mInflater
                    .getContext().getResources().getDrawable(android.R.drawable.
                            checkbox_on_background));
        } else {
            parentViewHolder.cbMake.setImageDrawable(mInflater
                    .getContext().getResources().getDrawable(android.R.drawable.
                            checkbox_off_background));
        }

        parentViewHolder.cbMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentAssignment.isChecked()) {
                    parentAssignment.setChecked(false);

                    parentViewHolder.cbMake.setImageDrawable(mInflater
                            .getContext().getResources().getDrawable(android.R.drawable.
                                    checkbox_off_background));
                } else {
                    parentAssignment.setChecked(true);
                    parentViewHolder.cbMake.setImageDrawable(mInflater
                            .getContext().getResources().getDrawable(android.R.drawable.
                                    checkbox_on_background));
                }
            }
        });

    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder childViewHolder, int position, Object childListItem) {
        ChildAssignment childAssignment = (ChildAssignment) childListItem;
        childViewHolder.tvVin.setText(childAssignment.getVin());
        childViewHolder.tvYear.setText(String.valueOf(childAssignment.getYear()));
        childViewHolder.tvMake.setText(childAssignment.getMake());
        childViewHolder.tvValue.setText("$" + childAssignment.getValue());
        childViewHolder.tvLength.setText(childAssignment.getLength() + " ft");
    }

    public static class MyParentViewHolder extends ParentViewHolder {

        public TextView idTv, makeTv;
        public ImageView ivExpand;
        public ImageView cbMake;

        public MyParentViewHolder(View itemView) {
            super(itemView);
            idTv = itemView.findViewById(R.id.tv_id);
            makeTv = itemView.findViewById(R.id.tv_make);
            ivExpand = itemView.findViewById(R.id.iv_expand);
            cbMake = itemView.findViewById(R.id.cb_make);
        }
    }


    public static class MyChildViewHolder extends ChildViewHolder {

        public TextView tvVin, tvYear, tvMake, tvValue, tvLength;

        public MyChildViewHolder(View itemView) {
            super(itemView);

            tvVin = itemView.findViewById(R.id.tv_vin_val);
            tvYear = itemView.findViewById(R.id.tv_year_val);
            tvMake = itemView.findViewById(R.id.tv_make_val);
            tvValue = itemView.findViewById(R.id.tv_value_val);
            tvLength = itemView.findViewById(R.id.tv_length_val);
        }
    }
}