package com.example.matchservios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchservios.adapter.ServicoAdapter
import com.example.matchservios.entity.ServicoEntidade
import com.example.matchservios.repository.ServicoRepository

class TelaListaServicos : AppCompatActivity() {

    private lateinit var imgBtnVoltar: AppCompatImageButton
    private lateinit var imgBtnPesquisar: AppCompatImageButton
    private lateinit var rvListaServicos: RecyclerView
    private lateinit var spinnerCategorias: Spinner
    private val servicoRepository = ServicoRepository(this)
    private lateinit var txtsemservicos: TextView
    private var isBooleanMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_tela_lista_servicos)
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
                    this@TelaListaServicos,
                    "Categoria selecionada : " + categorias[position],
                    Toast.LENGTH_SHORT
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        imgBtnVoltar.setOnClickListener {
            finish()
        }



        imgBtnPesquisar.setOnClickListener {
            atualizarServicoPorCategoria()
            isBooleanMode = false

        }

    }

    private fun iniciarComponentes() {
        imgBtnPesquisar = findViewById(R.id.imgbtnPesquisaTelaListaServicos)
        rvListaServicos = findViewById(R.id.rvListaServicos)
        spinnerCategorias = findViewById(R.id.spnCategoriaTelaListaServicos)
        imgBtnVoltar = findViewById(R.id.imgbtnVoltarTelaListaServicos)
        txtsemservicos = findViewById(R.id.txtvwSemServicoTelaListaServicos)
    }


    private fun startRecyclerView(listadeServicos: List<ServicoEntidade>) {
        rvListaServicos.adapter = ServicoAdapter(
            listadeServicos.toMutableList()
        )
        rvListaServicos.layoutManager = LinearLayoutManager(this)


    }

    private fun atualizarServicoPorCategoria() {
        val listaDeServicos =
            servicoRepository.buscarPorCategoria(categoria = spinnerCategorias.selectedItem.toString())
        if (listaDeServicos.isNullOrEmpty()) {
            isBooleanMode = true
        }
        if (isBooleanMode) {
            txtsemservicos.visibility = View.VISIBLE
            rvListaServicos.visibility = View.GONE
        } else {
            rvListaServicos.visibility = View.VISIBLE
        }
        listaDeServicos?.let {
            startRecyclerView(it)
        }
    }


}