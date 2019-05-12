package com.booleansystems.mycv.ui.home.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.booleansystems.mycv.R
import kotlinx.android.synthetic.main.item_skill.view.*

/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */
class SkillsAdapter(private val context: Context, private val skills: MutableList<String>) :
    RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.item_skill,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return skills.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(skills[position])
    }

    fun notifyDataSetChanged(list: List<String>) {
        if (!skills.isEmpty())
            skills.clear()
        skills.addAll(list)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(skill: String) {
            itemView.chip_skill.text = skill
        }
    }
}