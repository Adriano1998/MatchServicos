package com.example.matchservios.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matchservios.dao.ServicoDAO
import com.example.matchservios.dao.UsuarioDAO
import com.example.matchservios.entity.ServicoEntidade
import com.example.matchservios.entity.UsuarioEntidade


@Database(
    version = 1,
    entities = [
        UsuarioEntidade::class,
        ServicoEntidade::class

    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUsuarioDAO(): UsuarioDAO
    abstract fun getServicoDAO(): ServicoDAO


    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }

    }

}