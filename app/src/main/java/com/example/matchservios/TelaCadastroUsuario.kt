package com.example.matchservios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.matchservios.entity.UsuarioEntidade
import com.example.matchservios.repository.UsuarioRepository
import com.google.android.material.textfield.TextInputEditText

class TelaCadastroUsuario : AppCompatActivity() {

    private lateinit var btnCadastrar : Button
    private lateinit var tinptxtNomeCadUsuario : TextInputEditText
    private lateinit var tinptxtEmailCadUsuario: TextInputEditText
    private lateinit var tinptxtTelCadUsuario : TextInputEditText
    private lateinit var tinptxtSenhaCadUsuario : TextInputEditText
    private lateinit var tinptxtConfirmarSenhaCadUsuario : TextInputEditText
    private lateinit var imgbtnVoltar : ImageButton
    private val usuarioRepository = UsuarioRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_tela_cadastrousuario)

        iniciarComponentes()


        btnCadastrar.setOnClickListener{
            saveToLocalDb()
        }


        imgbtnVoltar.setOnClickListener{
            val intentvoltar = Intent(this, TelaLogin::class.java)
            startActivity(intentvoltar)
        }
    }




    private fun iniciarComponentes(){
        btnCadastrar = findViewById(R.id.btnCadastrarTelacadusuario)
        tinptxtNomeCadUsuario = findViewById(R.id.txtinpNomeCadUsuario)
        tinptxtEmailCadUsuario = findViewById(R.id.txtinpEmailTelaCadUsuario)
        tinptxtTelCadUsuario = findViewById(R.id.txtinpTelTelaCadUsuario)
        tinptxtSenhaCadUsuario = findViewById(R.id.txtinpSenhaTelaCadUsuario)
        tinptxtConfirmarSenhaCadUsuario = findViewById(R.id.txtinpConfirmarSenhaTelaCadUsuario)
        imgbtnVoltar =  findViewById(R.id.imgbtnVoltarTelacadusuario)

    }


    private fun saveToLocalDb() {
        val campoNome = tinptxtNomeCadUsuario.text.toString()
        val campoEmail = tinptxtEmailCadUsuario.text.toString()
        val campoTelefone = tinptxtTelCadUsuario.text.toString()
        val campoSenha = tinptxtSenhaCadUsuario.text.toString()
        val campoConfirmarSenha = tinptxtConfirmarSenhaCadUsuario.text.toString()
        if (campoNome.isNotEmpty() && campoEmail.isNotEmpty() && campoTelefone.isNotEmpty()
            && campoSenha.isNotEmpty() && campoConfirmarSenha.isNotEmpty()) {
            tinptxtNomeCadUsuario.error = null
            tinptxtEmailCadUsuario.error = null
            tinptxtTelCadUsuario.error = null
            tinptxtSenhaCadUsuario.error = null
            tinptxtConfirmarSenhaCadUsuario.error = null

            if(campoConfirmarSenha.equals(campoSenha)){
                val usuarioEntidade =
                    UsuarioEntidade(
                       nome = campoNome,
                        email = campoEmail,
                        telefone = campoTelefone,
                        senha = campoSenha
                    )

                usuarioRepository.inserir(usuarioEntidade)
                val intent = Intent(this, TelaLogin::class.java)
                startActivity(intent)

            }
            else {
                tinptxtConfirmarSenhaCadUsuario.error = "Senha diferente informada"
            }



        } else {
            tinptxtNomeCadUsuario.error = "Campo não pode estar em branco"
            tinptxtEmailCadUsuario.error = "Campo não pode estar em branco"
            tinptxtTelCadUsuario.error = "Campo não pode estar em branco"
            tinptxtSenhaCadUsuario.error = "Campo não pode estar em branco"
            tinptxtConfirmarSenhaCadUsuario.error = "Campo não pode estar em branco"
        }
    }







}