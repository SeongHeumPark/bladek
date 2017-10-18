package com.navercorp.nationinfo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * @author seongheum.park
 */
data class NationData(var resId: Int,
                      var name: String,
                      var capital: String)

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imgFlag = view.findViewById<ImageView>(R.id.img_flag)
    val name = view.findViewById<TextView>(R.id.text_name)
    val capital = view.findViewById<TextView>(R.id.capital)
}

class NationAdapter(private val context: Context, private val items: List<NationData>) : RecyclerView.Adapter<ViewHolder>() {
    lateinit var onItemClick: View.OnClickListener

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgFlag.setImageResource(items[position].resId)
        holder.name.text = items[position].name
        holder.capital.text = items[position].capital
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val mainView: View = LayoutInflater.from(context).inflate(R.layout.nation_list_item, parent, false)
        mainView.setOnClickListener(onItemClick)

        return ViewHolder(mainView)
    }

    fun setOnItemClickListener(listener: View.OnClickListener) {
        onItemClick = listener
    }
}