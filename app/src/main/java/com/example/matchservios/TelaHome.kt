package com.example.matchservios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchservios.entity.UsuarioEntidade
import com.example.matchservios.repository.UsuarioRepository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TelaHome : AppCompatActivity(), View.OnClickListener {

    var usuarioId = 0
    private lateinit var txtNome: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtTelefone: TextView
    private lateinit var txtCadastroServico: TextView
    private lateinit var txtBuscaServico: TextView
    private lateinit var imgBtnEditarDados: AppCompatImageButton


    private val usuarioRepository = UsuarioRepository(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_tela_home)

        iniciarComponentes()
        usuarioId = intent.getIntExtra("usuario_id", 1)
        buscarDadosUsuario()

        imgBtnEditarDados.setOnClickListener {
            val intentUsuario = Intent(this, TelaEdicao::class.java)
            intentUsuario.putExtra("usuario_id", usuarioId)
            startActivity(intentUsuario)
        }

    }


    private fun buscarDadosUsuario() {

        val usuarioSelecionado = usuarioRepository.buscarPorId(usuarioId!!)

        usuarioSelecionado?.let { usu ->
            txtNome.setText(usu.nome)
            txtEmail.setText(usu.email)
            txtTelefone.setText(usu.telefone)
        }
    }

    override fun onResume() {
        super.onResume()
        buscarDadosUsuario()
    }

    private fun iniciarComponentes() {
        txtNome = findViewById(R.id.tilNomeTelaHome)
        txtBuscaServico = findViewById(R.id.txtBuscaServicoTelaHome)
        txtEmail = findViewById(R.id.tilEmailTelaHome)
        txtTelefone = findViewById(R.id.tilTelefoneTelaHome)
        txtCadastroServico = findViewById(R.id.txtCadastrarServicoTelaHome)
        imgBtnEditarDados = findViewById(R.id.imgbtnEditTelaHome)
    }

    override fun onClick(v: View?) {
        val intentCadastro = Intent(this, TelaCadastroServico::class.java)
        intentCadastro.putExtra("usuario_id", usuarioId)
        startActivity(intentCadastro)

    }

    fun onClickBuscarServico(v: View?) {
        val intentdetalhes = Intent(this, TelaListaServicos::class.java)
        intentdetalhes.putExtra("usuario_id", usuarioId)
        startActivity(intentdetalhes)
    }

    fun onClickBuscarMeusServicos(v: View?) {
        val intentdetalhes = Intent(this, TelaMeusServicos::class.java)
        intentdetalhes.putExtra("usuario_id", usuarioId)
        startActivity(intentdetalhes)
    }


}