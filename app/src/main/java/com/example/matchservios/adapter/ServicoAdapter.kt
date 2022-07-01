package com.example.matchservios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchservios.R
import com.example.matchservios.entity.ServicoEntidade

class ServicoAdapter(
    private val listaDeServicos: MutableList<ServicoEntidade>,
) : RecyclerView.Adapter<ServicoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_servico, parent, false)
        return ServicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {
        val servico = listaDeServicos[position]
        holder.vincula(servico)

    }


    override fun getItemCount(): Int {
        return listaDeServicos.size
    }

    fun refresh(newList: List<ServicoEntidade>) {
        listaDeServicos.clear()
        listaDeServicos.addAll(newList)
        notifyDataSetChanged()
    }

}