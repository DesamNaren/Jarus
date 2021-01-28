package com.test.jarustest.model

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem

class ParentAssignment(val mId: Int, val mTitle: String) : ParentListItem {
    var isChecked = false
    private var mChildItemList: List<ChildAssignment?>? = null

    override fun getChildItemList(): List<ChildAssignment?> {
        return mChildItemList!!
    }

    override fun isInitiallyExpanded(): Boolean {
        return false
    }

    fun setChildItemList(list: List<ChildAssignment?>?) {
        mChildItemList = list
    }

}