package com.test.jarustest.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.jarustest.R
import com.test.jarustest.databinding.ActivityMainBinding
import com.test.jarustest.error_handler.ErrorHandlerInterface
import com.test.jarustest.model.ChildAssignment
import com.test.jarustest.model.ParentAssignment
import java.util.*

class AssignmentActivity : AppCompatActivity(), ErrorHandlerInterface {
    private var binding: ActivityMainBinding? = null
    private var assignmentViewModel: AssignmentViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        assignmentViewModel = AssignmentViewModel()
        loadData()
    }

    override fun handleError(msg: String?) {
        Snackbar.make(binding!!.toolbarTop, msg!!, Snackbar.LENGTH_LONG).setAction(getString(R.string.retry)) { loadData() }.show()
    }

    private fun loadData() {
        assignmentViewModel!!.getData(applicationContext)!!.observe(this, Observer { outputString: String? ->
            if (!TextUtils.isEmpty(outputString)) {
                val listUserType = object : TypeToken<List<ChildAssignment?>?>() {}.type
                val totalAssignments = Gson().fromJson<List<ChildAssignment>>(outputString, listUserType)
                val parentListItems: MutableList<ParentListItem> = ArrayList()
                for (i in Objects.requireNonNull(totalAssignments).indices) {
                    val childAssignments: MutableList<ChildAssignment?> = ArrayList()
                    val childAssignment = ChildAssignment(
                            totalAssignments[i].id,
                            totalAssignments[i].vin,
                            totalAssignments[i].year,
                            totalAssignments[i].make,
                            totalAssignments[i].value,
                            totalAssignments[i].length)
                    childAssignments.add(childAssignment)
                    val parentAssignment = ParentAssignment(totalAssignments[i].id, totalAssignments[i].make)
                    parentAssignment.setChildItemList(childAssignments)
                    parentListItems.add(parentAssignment)
                }
                if (parentListItems.size > 0) {
                    val layoutManager = LinearLayoutManager(applicationContext)
                    binding!!.rvAssignments.layoutManager = layoutManager
                    binding!!.rvAssignments.adapter = AssignmentAdapter(applicationContext, parentListItems)
                } else {
                    handleError(getString(R.string.no_data_found))
                }
            } else {
                handleError(getString(R.string.something))
            }
        })
    }
}