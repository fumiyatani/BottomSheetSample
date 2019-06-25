package jp.androidbook.bottomsheetsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_bottom_sheet_recycler_view.view.*

class BottomSheetRecyclerViewAdapter(private val list: List<String>, private val listener: ListTappedListener): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_bottom_sheet_recycler_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position]
        holder.itemView.setOnClickListener {
            listener.onClickList(list[position])
        }
    }

    interface ListTappedListener{
        fun onClickList(text: String)
    }
}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.textView
}