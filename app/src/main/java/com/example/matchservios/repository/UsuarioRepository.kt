package com.example.matchservios.repository

import android.content.Context
import com.example.matchservios.database.AppDatabase
import com.example.matchservios.entity.UsuarioEntidade

class UsuarioRepository(private val context: Context) {

    fun inserir(usuarioEntidade: UsuarioEntidade) {
        AppDatabase.getInstance(context)?.getUsuarioDAO()?.inserir(usuarioEntidade)
    }

    fun buscarPorEmaileSenha(email: String, senha: String): UsuarioEntidade? {
        return AppDatabase.getInstance(context)?.getUsuarioDAO()?.buscarLogineSenha(email, senha)
    }

    fun buscarPorId(id: Int): UsuarioEntidade? {
        return AppDatabase.getInstance(context)?.getUsuarioDAO()?.buscarPorId(id)
    }

}