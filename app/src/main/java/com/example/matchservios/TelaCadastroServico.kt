package com.example.matchservios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatImageView
import com.example.matchservios.entity.EnderecoDTO
import com.example.matchservios.entity.ServicoEntidade
import com.example.matchservios.repository.ServicoRepository
import com.example.matchservios.repository.UsuarioRepository
import com.google.android.material.textfield.TextInputEditText

class TelaCadastroServico : AppCompatActivity() {

    private lateinit var btnCadastrar: Button
    private lateinit var tinptxtDescricaoCadServico: TextInputEditText
    private lateinit var tinptxtRuaCadServico: TextInputEditText
    private lateinit var tinptxtNumeroCadServico: TextInputEditText
    private lateinit var tinptxtBairroCadServico: TextInputEditText
    private lateinit var tinptxtCidadeCadServico: TextInputEditText
    private lateinit var tinptxtEstadoCadServico: TextInputEditText
    private lateinit var tinptxtPaisCadServico: TextInputEditText
    private lateinit var spinnerCategorias: Spinner
    private var usuarioId: Int? = null
    private lateinit var imgbtnVoltar: AppCompatImageView
    private val servicoRepository = ServicoRepository(this)
    private val usuarioRepository = UsuarioRepository(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_tela_cadastro_servico)
        iniciarComponentes()

        val categorias =
            arrayOf("Administrativo", "Construção Civil", "Evento", "Limpeza", "Manutenção")
        val arrayAdapter = ArrayAdapter<String>(this, R.layout.item_spinner, categorias)
        spinnerCategorias.adapter = arrayAdapter
        spinnerCategorias.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@TelaCadastroServico,
                    "Categoria selecionada : " + categorias[position],
                    Toast.LENGTH_SHORT
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        btnCadastrar.setOnClickListener {
            saveToLocalDb()

        }

        imgbtnVoltar.setOnClickListener {
            finish()
        }


    }


    private fun iniciarComponentes() {
        tinptxtDescricaoCadServico = findViewById(R.id.txtinpDescricaoTelaCadServico)
        tinptxtRuaCadServico = findViewById(R.id.txtinpRuaTelaCadServico)
        tinptxtNumeroCadServico = findViewById(R.id.txtinpNumeroTelaCadServico)
        tinptxtBairroCadServico = findViewById(R.id.txtinpBairroTelaCadServico)
        tinptxtCidadeCadServico = findViewById(R.id.txtinpCidadeTelaCadServico)
        tinptxtEstadoCadServico = findViewById(R.id.txtinpEstadoTelaCadServico)
        tinptxtPaisCadServico = findViewById(R.id.txtinpPaisTelaCadServico)
        spinnerCategorias = findViewById(R.id.spnCategoriaTelaCadServico)
        btnCadastrar = findViewById(R.id.btnCadastrarTelaCadServico)
        imgbtnVoltar = findViewById(R.id.imgbtnVoltarTelaCadServico)

    }

    private fun saveToLocalDb() {
        val campoDescricao = tinptxtDescricaoCadServico.text.toString()
        val campoRua = tinptxtRuaCadServico.text.toString()
        val campoNumero = tinptxtNumeroCadServico.text.toString()
        val campoBairro = tinptxtBairroCadServico.text.toString()
        val campoCidade = tinptxtCidadeCadServico.text.toString()
        val campoEstado = tinptxtEstadoCadServico.text.toString()
        val campoPais = tinptxtPaisCadServico.text.toString()
        val campoCategoria = spinnerCategorias.selectedItem.toString()


        if (campoDescricao.isNotEmpty() && campoRua.isNotEmpty() && campoNumero.isNotEmpty()
            && campoBairro.isNotEmpty() && campoBairro.isNotEmpty() && campoCidade.isNotEmpty()
            && campoEstado.isNotEmpty() && campoPais.isNotEmpty() && campoCategoria.isNotEmpty()
        ) {
            tinptxtDescricaoCadServico.error = null
            tinptxtRuaCadServico.error = null
            tinptxtNumeroCadServico.error = null
            tinptxtBairroCadServico.error = null
            tinptxtCidadeCadServico.error = null
            tinptxtEstadoCadServico.error = null
            tinptxtPaisCadServico.error = null

            usuarioId = intent.getIntExtra("usuario_id", 0)

            val usuarioSelecionado = usuarioRepository.buscarPorId(usuarioId!!)
            usuarioSelecionado?.let { usu ->
                ServicoEntidade(
                    descricao = campoDescricao,
                    endereco = EnderecoDTO(
                        rua = campoRua,
                        numero = campoNumero,
                        bairro = campoBairro,
                        cidade = campoCidade,
                        estado = campoEstado,
                        pais = campoPais,
                    ),
                    categoria = campoCategoria,
                    usuario = usu
                ).also {
                    servicoRepository.inserir(it)
                }

            }
            finish()

        } else {
            tinptxtDescricaoCadServico.error = "Campo não pode estar em branco"
            tinptxtRuaCadServico.error = "Campo não pode estar em branco"
            tinptxtNumeroCadServico.error = "Campo não pode estar em branco"
            tinptxtBairroCadServico.error = "Campo não pode estar em branco"
            tinptxtCidadeCadServico.error = "Campo não pode estar em branco"
            tinptxtEstadoCadServico.error = "Campo não pode estar em branco"
            tinptxtPaisCadServico.error = "Campo não pode estar em branco"

        }
    }

}