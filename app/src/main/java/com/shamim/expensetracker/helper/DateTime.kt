package com.shamim.expensetracker.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTime {


    fun now(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }

    fun getDay():String{
        return  SimpleDateFormat("dd",Locale.getDefault()).format(Date())
    }
    fun getMonth():String{
        return  SimpleDateFormat("MM",Locale.getDefault()).format(Date())
    }

    fun getYear():String{
        return  SimpleDateFormat("yyyy",Locale.getDefault()).format(Date())
    }

}