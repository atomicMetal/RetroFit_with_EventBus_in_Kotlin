package com.metalsack.demouno

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.animal_list_item.view.*
import org.greenrobot.eventbus.EventBus

class Recycler_Adapter (val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvAnimalType?.text = items.get(position)
        holder.tvAnimalType.setOnClickListener(View.OnClickListener {
            //Toast.makeText(context,"Click", Toast.LENGTH_SHORT).show()
            EventBus.getDefault().post(MainActivity.MessageEvent(items.get(position)))
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.animal_list_item,
                parent, false))
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.txtMsg

}