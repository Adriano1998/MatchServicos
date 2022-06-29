package com.example.matchservios.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.matchservios.entity.ServicoEntidade
import com.example.matchservios.entity.UsuarioEntidade

@Dao
interface ServicoDAO {

    @Insert
    fun inserir(servicoEntidade: ServicoEntidade)

    @Query("SELECT * FROM servico_table ORDER BY servico_categoria")
    fun buscar(): List<ServicoEntidade>

    @Query("SELECT * FROM SERVICO_TABLE WHERE servico_categoria = :categoria")
    fun buscarPorCategoria(categoria: String): List<ServicoEntidade>

    @Query("SELECT * FROM SERVICO_TABLE WHERE usuario_id = :id")
    fun buscarPorUsuario(id: Int) : List<ServicoEntidade>

    @Delete
    fun delete(servicoEntidade: ServicoEntidade)

}