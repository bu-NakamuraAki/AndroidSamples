package com.exsample.androidsamples.okhttp3

import QiitaResponse
import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exsample.androidsamples.R
import com.squareup.picasso.Picasso
import timber.log.Timber


class QiitaViewAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<QiitaResponse>()
//    private val myActivity by lazy {OkHttp3Activity() }
    fun clear(list: List<QiitaResponse>) {
        items.apply {
            clear()//リスト内のデータをクリアする
        }
    }
    fun refresh(list: List<QiitaResponse>) {
        items.apply {
//            clear()//リスト内のデータをクリアする
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.qiita_view_cell,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            onBindViewHolder(holder, position)
    }

    private fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = items[position]
        holder.titleTextView.text = data.title
        holder.likeCountTextView.text = "${data.likes_count}"//$何とかで文字列ではなくデータの中身を見せろという意味
        holder.userNameTextView.text = data.user.name
        Picasso.get().load(data.user.profile_image_url).into(holder.imageView)
        holder.rootView.setOnClickListener {
//            Toast.makeText(context, "${data.title}", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, WebView::class.java)
//            // intentにurlを渡すと...
//            context.startActivity(intent);
            intent.putExtra("url",data.url)

            context.startActivity(intent)


        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rootView: ConstraintLayout = view.findViewById(R.id.rootView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val likeCountTextView: TextView = view.findViewById(R.id.likeCountTextView)
        val userNameTextView:TextView =  view.findViewById(R.id.userNameTextView)
    }
}