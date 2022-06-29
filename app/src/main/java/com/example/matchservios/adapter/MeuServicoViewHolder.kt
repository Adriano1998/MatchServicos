package com.example.matchservios.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.matchservios.R
import com.example.matchservios.entity.ServicoEntidade

class MeuServicoViewHolder(itemView : View):  RecyclerView.ViewHolder(itemView) {

    val imgbtnDelete : ImageButton = itemView.findViewById(R.id.imgbtnDeleteMeusServicos)
    private val txtCategoria : TextView = itemView.findViewById(R.id.txtCategoriaItemMeusServicos)
    private val txtDescricao : TextView = itemView.findViewById(R.id.txtDescricaoItemMeusServicos)

    fun vincula(servico: ServicoEntidade){
        txtCategoria.text = servico.categoria
        txtDescricao.text = servico.descricao
    }


}