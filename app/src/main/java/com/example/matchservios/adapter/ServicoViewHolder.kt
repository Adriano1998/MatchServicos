package com.example.matchservios.adapter

import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.matchservios.R
import com.example.matchservios.entity.ServicoEntidade

class ServicoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val txtNomeCategoria : TextView = itemView.findViewById(R.id.txtCategoriaItemServico)
    private val txtDescricao : TextView = itemView.findViewById(R.id.txtDescricaoItemServico)
    private val txtPrestador: TextView = itemView.findViewById(R.id.txtPrestadorItemServico)
    private val txtTelefone : TextView = itemView.findViewById(R.id.txtTelefoneItemServico)
    private val txtRua : TextView = itemView.findViewById(R.id.txtRuaItemServico)
    private val txtNumero : TextView = itemView.findViewById(R.id.txtNumeroItemServico)
    private val txtBairro: TextView = itemView.findViewById(R.id.txtBairroItemServico)
    private val txtCidade: TextView = itemView.findViewById(R.id.txtCidadeItemServico)
    private val txtEstado: TextView = itemView.findViewById(R.id.txtEstadoItemServico)
    private val txtPais : TextView = itemView.findViewById(R.id.txtPaisItemServico)
   // var btnPesquisar : AppCompatImageButton = itemView.findViewById(R.id.imgbtnPesquisaTelaListaServicos)

    fun vincula(servico : ServicoEntidade){
        txtNomeCategoria.text = servico.categoria
        txtDescricao.text = servico.descricao
        txtPrestador.text = servico.usuario.nome
        txtTelefone.text = servico.usuario.telefone
        txtRua.text = servico.endereco.rua
        txtNumero.text = servico.endereco.numero
        txtBairro.text = servico.endereco.bairro
        txtCidade.text = servico.endereco.cidade
        txtEstado.text = servico.endereco.estado
        txtPais.text = servico.endereco.pais
    }

}