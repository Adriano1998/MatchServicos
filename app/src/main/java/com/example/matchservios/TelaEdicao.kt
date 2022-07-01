package com.example.matchservios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageButton
import com.example.matchservios.entity.UsuarioEntidade
import com.example.matchservios.repository.UsuarioRepository
import com.google.android.material.textfield.TextInputEditText

class TelaEdicao : AppCompatActivity() {

    private lateinit var txtinpedttxtNome: TextInputEditText
    private lateinit var txtinpedttxtEmail: TextInputEditText
    private lateinit var txtinpedtxtTelefone: TextInputEditText
    private lateinit var btnAlterar: Button
    private lateinit var imgbtnVoltar: AppCompatImageButton
    private var usuarioId: Int? = null
    private val usuarioRepository = UsuarioRepository(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_edicao)
        supportActionBar?.hide()
        iniciarComponentes()

        usuarioId = intent.getIntExtra("usuario_id", 0)

        val usuarioSelecionado = usuarioRepository.buscarPorId(usuarioId!!)
        usuarioSelecionado?.let { usu ->
            txtinpedttxtNome.setText(usu.nome)
            txtinpedttxtEmail.setText(usu.email)
            txtinpedtxtTelefone.setText(usu.telefone)
        }

        imgbtnVoltar.setOnClickListener {
            finish()
        }

        btnAlterar.setOnClickListener {
            atualizaDB()
        }
    }


    private fun iniciarComponentes() {
        imgbtnVoltar = findViewById(R.id.imgbtnVoltarTelaEdicao)
        txtinpedttxtNome = findViewById(R.id.txtinpNomeEdtxtTelaEdicao)
        txtinpedttxtEmail = findViewById(R.id.txtinpEmailEdtxtTelaEdicao)
        txtinpedtxtTelefone = findViewById(R.id.txtinpTelefoneEdtxtTelaEdicao)
        btnAlterar = findViewById(R.id.btnEditarTelaEdicao)
    }

    private fun atualizaDB() {
        val campoNome = txtinpedttxtNome.text.toString()
        val campoEmail = txtinpedttxtEmail.text.toString()
        val campoTelefone = txtinpedtxtTelefone.text.toString()

        if (campoNome.isNotEmpty() && campoEmail.isNotEmpty() && campoTelefone.isNotEmpty()) {
            txtinpedttxtNome.error = null
            txtinpedttxtEmail.error = null
            txtinpedtxtTelefone.error = null

            val usuario = usuarioRepository.buscarPorId(usuarioId!!)

            val usuarioEntidade: UsuarioEntidade
            usuarioEntidade = UsuarioEntidade(
                id = usuarioId!!,
                nome = campoNome,
                email = campoEmail,
                telefone = campoTelefone,
                senha = usuario!!.senha
            )
            finish()
            usuarioRepository.inserir(usuarioEntidade)

        }

    }


}