package com.example.matchservios.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario_table")
data class UsuarioEntidade(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "usuario_id")
    val id: Int = 0,
    @ColumnInfo(name = "usuario_nome")
    var nome : String,
    @ColumnInfo(name = "usuario_email")
    var email : String,
    @ColumnInfo(name = "usuario_tel")
    var telefone : String,
    @ColumnInfo(name = "usuario_senha")
    var senha : String


) {
}