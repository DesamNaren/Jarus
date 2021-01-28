package com.test.jarustest.ui

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import com.test.jarustest.R
import com.test.jarustest.databinding.ItemChildBinding
import com.test.jarustest.databinding.ItemParentBinding
import com.test.jarustest.model.ChildAssignment
import com.test.jarustest.model.ParentAssignment
import com.test.jarustest.ui.AssignmentAdapter.MyChildViewHolder
import com.test.jarustest.ui.AssignmentAdapter.MyParentViewHolder

class AssignmentAdapter(context: Context?, itemList: List<ParentListItem?>?) : ExpandableRecyclerAdapter<MyParentViewHolder, MyChildViewHolder>(itemList!!) {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateParentViewHolder(viewGroup: ViewGroup): MyParentViewHolder {
        val itemParentBinding: ItemParentBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_parent, viewGroup, false)
        return MyParentViewHolder(itemParentBinding)
    }

    override fun onCreateChildViewHolder(viewGroup: ViewGroup): MyChildViewHolder {
        val itemChildBinding: ItemChildBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_child, viewGroup, false)
        return MyChildViewHolder(itemChildBinding)
    }

    override fun onBindParentViewHolder(parentViewHolder: MyParentViewHolder, position: Int, parentListItem: ParentListItem) {
        try {
            val parentAssignment = parentListItem as ParentAssignment
            parentViewHolder.itemParentBinding.parentData = parentAssignment
            if (parentAssignment.isChecked) {
                parentViewHolder.itemParentBinding.cbMake.setImageDrawable(
                        ContextCompat.getDrawable(mInflater.context, android.R.drawable.checkbox_on_background))
            } else {
                parentViewHolder.itemParentBinding.cbMake.setImageDrawable(
                        ContextCompat.getDrawable(mInflater.context, android.R.drawable.checkbox_off_background))
            }
            parentViewHolder.itemParentBinding.cbMake.setOnClickListener {
                if (parentAssignment.isChecked) {
                    parentAssignment.isChecked = false
                    parentViewHolder.itemParentBinding.cbMake.setImageDrawable(
                            ContextCompat.getDrawable(mInflater.context, android.R.drawable.checkbox_off_background))
                } else {
                    parentAssignment.isChecked = true
                    parentViewHolder.itemParentBinding.cbMake.setImageDrawable(
                            ContextCompat.getDrawable(mInflater.context, android.R.drawable.checkbox_on_background))
                }
            }
        } catch (e: NotFoundException) {
            e.printStackTrace()
        }
    }

    override fun onBindChildViewHolder(childViewHolder: MyChildViewHolder, position: Int, childListItem: Any) {
        val childAssignment = childListItem as ChildAssignment
        childViewHolder.itemChildBinding.childData = childAssignment
    }

    class MyParentViewHolder(var itemParentBinding: ItemParentBinding) : ParentViewHolder(itemParentBinding.root)

    class MyChildViewHolder(var itemChildBinding: ItemChildBinding) : ChildViewHolder(itemChildBinding.root)

}