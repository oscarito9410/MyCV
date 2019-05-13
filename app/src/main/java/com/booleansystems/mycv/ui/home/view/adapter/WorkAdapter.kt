package com.booleansystems.mycv.ui.home.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.booleansystems.mycv.R
import com.booleansystems.mycv.ui.home.mapper.Work
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_work_experience.view.*

/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */
class WorkAdapter(private val context: Context, private val workItems: MutableList<Work>) :
    RecyclerView.Adapter<WorkAdapter.ViewHolder>() {

    val inflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.item_work_experience,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return workItems.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(context, workItems[position])
    }


    fun notifyDataSetChanged(list: List<Work>) {
        if (!workItems.isEmpty())
            workItems.clear()
        workItems.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(context: Context, item: Work) {
            Glide.with(context).load(item.urlImage)
                .fitCenter().thumbnail(0.5f).into(itemView.iv_work_company)
            itemView.tv_work_position.text = item.position
            itemView.tv_work_init_date_end_date.text =
                context.getString(R.string.text_date_template_format, item.startDate, item.endDate)
            itemView.tv_work_company.text = item.company
            itemView.tv_work_summary.text = item.summary
        }
    }
}