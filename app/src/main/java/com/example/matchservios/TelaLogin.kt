package com.example.matchservios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.matchservios.repository.UsuarioRepository
import com.google.android.material.textfield.TextInputEditText

class TelaLogin : AppCompatActivity(), View.OnClickListener {

    private lateinit var txtinputlogin: TextInputEditText
    private lateinit var txtinputsenha: TextInputEditText
    private lateinit var btnentrar: Button
    lateinit var txtCadastro: TextView
    private val usuarioRepository = UsuarioRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_telalogin)
        iniciarComponentes()


        btnentrar.setOnClickListener {
            buscarLogineSenhaBanco()
        }

    }


    private fun iniciarComponentes() {
        txtinputlogin = findViewById(R.id.txtinploginTelaLogin)
        txtinputsenha = findViewById(R.id.txtinpsenhaTelaLogin)
        btnentrar = findViewById(R.id.btnEntrarTelaLogin)
        txtCadastro = findViewById(R.id.txtcadastreseTelaLogin)
    }


    override fun onClick(v: View?) {
        val intentdetalhes = Intent(this, TelaCadastroUsuario::class.java)
        startActivity(intentdetalhes)
    }

    fun onClickBuscarServico(v: View?) {
        val intentdetalhes = Intent(this, TelaListaServicos::class.java)
        startActivity(intentdetalhes)
    }

    private fun buscarLogineSenhaBanco() {
        val textlogin = txtinputlogin.text.toString()
        val textSenha = txtinputsenha.text.toString()
        if (textlogin.isNotEmpty() && textSenha.isNotEmpty()) {
            txtinputlogin.error = null
            txtinputsenha.error = null
            val usuario = usuarioRepository.buscarPorEmaileSenha(textlogin, textSenha)
            val usuarioparaid = usuario?.id
            usuario?.let {
                val intentUsuario = Intent(this, TelaHome::class.java)
                intentUsuario.putExtra("usuario_id", usuarioparaid)
                startActivity(intentUsuario)

            }
        } else {
            txtinputlogin.error = "Digite o campo em branco"
            txtinputsenha.error = "Digite o campo em branco"
        }
    }
}