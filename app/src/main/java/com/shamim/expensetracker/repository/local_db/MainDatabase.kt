package com.shamim.expensetracker.repository.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shamim.expensetracker.dao.IncomeHeadDao
import com.shamim.expensetracker.model.IncomeHead

@Database(

    entities = [IncomeHead::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun getIncomeHeadDao():IncomeHeadDao
//    abstract fun getProductList():ProductListDao
//    abstract fun getProductDetails():ProductDetailsDao

}