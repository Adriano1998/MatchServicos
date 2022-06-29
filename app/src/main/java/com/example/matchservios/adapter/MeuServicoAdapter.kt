package com.example.matchservios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchservios.R
import com.example.matchservios.entity.ServicoEntidade

class MeuServicoAdapter(
    private val listaDeServicos: MutableList<ServicoEntidade>,
    private val onClick: (ServicoEntidade) -> Unit
) : RecyclerView.Adapter<MeuServicoViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeuServicoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meus_servicos, parent, false)
        return MeuServicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeuServicoViewHolder, position: Int) {
        val servico = listaDeServicos[position]
        holder.vincula(servico)
        holder.imgbtnDelete.setOnClickListener{
            onClick.invoke(servico)
        }
    }

    override fun getItemCount(): Int {
        return listaDeServicos.size
    }


}