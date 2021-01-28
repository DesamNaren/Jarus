package com.test.jarustest.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.jarustest.R
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.concurrent.Executors

class AssignmentViewModel : ViewModel() {
    var stringMutableLiveData: MutableLiveData<String?>? = MutableLiveData()
    fun getData(context: Context): LiveData<String?>? {
        if (stringMutableLiveData != null) {
            val executorService = Executors.newSingleThreadExecutor()
            executorService.execute { stringMutableLiveData!!.postValue(getJsonFromAssets(context)) }
            executorService.shutdown()
        }
        return stringMutableLiveData
    }

    private fun getJsonFromAssets(context: Context): String? {
        val outputString: String
        val charset: Charset = Charsets.UTF_8

        outputString = try {
            val `is` = context.assets.open(context.getString(R.string.file_name))
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String(buffer, StandardCharsets.UTF_8)
            } else {
                String(buffer, charset)
            }

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return outputString
    }
}