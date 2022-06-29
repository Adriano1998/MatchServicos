package com.example.matchservios.dao

import androidx.room.*
import com.example.matchservios.entity.UsuarioEntidade

@Dao
interface UsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserir(usuarioEntidade: UsuarioEntidade)

    @Query("Select * from usuario_table where usuario_email =  :email and usuario_senha  = :senha")
    fun buscarLogineSenha(email : String, senha: String) : UsuarioEntidade

    @Query("select * from usuario_table where usuario_id = :id")
    fun buscarPorId(id: Int): UsuarioEntidade


}