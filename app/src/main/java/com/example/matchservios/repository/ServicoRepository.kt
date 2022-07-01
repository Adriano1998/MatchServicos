package com.example.matchservios.repository

import android.content.Context
import com.example.matchservios.database.AppDatabase
import com.example.matchservios.entity.ServicoEntidade

class ServicoRepository(private val context: Context) {

    fun inserir(servicoEntidade: ServicoEntidade) {
        AppDatabase.getInstance(context)?.getServicoDAO()?.inserir(servicoEntidade)
    }

    fun buscar(): List<ServicoEntidade>? {
        return AppDatabase.getInstance(context)?.getServicoDAO()?.buscar()
    }

    fun buscarPorCategoria(categoria: String): List<ServicoEntidade>? {
        return AppDatabase.getInstance(context)?.getServicoDAO()?.buscarPorCategoria(categoria)
    }

    fun buscarPorUsuario(id: Int): List<ServicoEntidade>? {
        return AppDatabase.getInstance(context)?.getServicoDAO()?.buscarPorUsuario(id)
    }

    fun deletar(servicoEntidade: ServicoEntidade) {
        AppDatabase.getInstance(context)?.getServicoDAO()?.delete(servicoEntidade)
    }
}