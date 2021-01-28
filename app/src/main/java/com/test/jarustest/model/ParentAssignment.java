package com.test.jarustest.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.test.jarustest.model.ChildAssignment;

import java.util.List;

public class ParentAssignment implements ParentListItem {
    private final int mId;
    private final String mTitle;
    private boolean isChecked;
    private List<ChildAssignment> mChildItemList;

    public ParentAssignment(int mId, String mTitle) {
        this.mId = mId;
        this.mTitle = mTitle;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getMId() {
        return mId;
    }

    public String getMTitle() {
        return mTitle;
    }

    @Override
    public List<ChildAssignment> getChildItemList() {
        return mChildItemList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public void setChildItemList(List<ChildAssignment> list) {
        mChildItemList = list;
    }
}
