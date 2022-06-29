package com.example.matchservios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchservios.adapter.MeuServicoAdapter
import com.example.matchservios.adapter.ServicoAdapter
import com.example.matchservios.entity.ServicoEntidade
import com.example.matchservios.repository.ServicoRepository

class TelaMeusServicos : AppCompatActivity() {

    private lateinit var rvListaMeusServicos : RecyclerView
    private lateinit var imgbtnVoltar : AppCompatImageButton
    private var usuarioId: Int? = null
    val servicoRepository = ServicoRepository(this)
    private lateinit var txtTemServicoOuNao : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_meus_servicos)
        supportActionBar?.hide()

        iniciarComponentes()

        imgbtnVoltar.setOnClickListener{
            finish()
        }
        atualizarServicoUsuario()

    }



    private fun iniciarComponentes(){
        rvListaMeusServicos = findViewById(R.id.rvListaMeusServicos)
        imgbtnVoltar = findViewById(R.id.imgbtnVoltarTelaMeusServicos)
        txtTemServicoOuNao = findViewById(R.id.txtvwSemServicoTelaMeusServicos)
    }


    private fun startRecyclerView(listaServicos: List<ServicoEntidade>){
            rvListaMeusServicos.adapter = MeuServicoAdapter(listaServicos.toMutableList(),
                onClick = {
                    servico ->
                    deletarServico(servico)
                    atualizarServicoUsuario()
                }
            )
            rvListaMeusServicos.layoutManager = LinearLayoutManager(this)


        }

    private fun deletarServico(servico: ServicoEntidade) {
        val deletar = servicoRepository.deletar(servico)
    }
    private fun atualizarServicoUsuario(){
        usuarioId = intent.getIntExtra("usuario_id", 0)
        val listaDeServicos = servicoRepository.buscarPorUsuario(usuarioId!!)
        if(listaDeServicos.isNullOrEmpty()){
            txtTemServicoOuNao.visibility = View.VISIBLE
        }
        else {
            rvListaMeusServicos.visibility = View.VISIBLE
        }
        listaDeServicos?.let {
            startRecyclerView(it)
        }
    }

    }

