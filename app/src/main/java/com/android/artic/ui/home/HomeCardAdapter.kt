package com.android.artic.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.ui.home.data.ArchiveHomeCard
import com.android.artic.ui.home.data.HomeCard
import com.android.artic.ui.home.data.HomeCardKind
import com.android.artic.ui.home.data.LinkHomeCard
import com.bumptech.glide.Glide
import kotlin.IllegalArgumentException

class HomeCardAdapter(
    private val context: Context,
    var data: List<HomeCard>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            HomeCardKind.ARCHIVE.ordinal -> {
                val view = LayoutInflater.from(context).inflate(R.layout.rv_item_archive_home_card, parent, false)
                ArchiveHolder(view)
            }
            HomeCardKind.LINK.ordinal -> {
                val view = LayoutInflater.from(context).inflate(R.layout.rv_item_link_home_card, parent, false)
                LinkHolder(view)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.run {
            when(this) {
                is ArchiveHolder -> {
                    val cur = data[position] as ArchiveHomeCard
                    img_rv_item_a_home_card.clipToOutline = true // 이미지 라운딩 처리를 위함
                    Glide.with(context)
                        .load(cur.img_url)
                        .into(img_rv_item_a_home_card)
                    txt_rv_item_a_home_card_title.text = cur.title
                    txt_rv_item_a_home_card_desc.text = cur.desc
                }
                is LinkHolder -> {
                    val cur = data[position] as LinkHomeCard
                    img_rv_item_l_home_card.clipToOutline = true // 이미지 라운딩 처리를 위함
                    Glide.with(context)
                        .load(cur.img_url)
                        .into(img_rv_item_l_home_card)
                    txt_rv_item_l_home_card_title.text = cur.title
                    txt_rv_item_l_home_card_curator.text = cur.curator
                }
                else -> IllegalArgumentException()
            }
        }
    }

    override fun getItemViewType(position: Int): Int = data[position].viewType.ordinal

    inner class ArchiveHolder(view: View): RecyclerView.ViewHolder(view) {
        val container_rv_item_a_home = view.findViewById<View>(R.id.container_rv_item_a_home)
        val img_rv_item_a_home_card = view.findViewById<ImageView>(R.id.img_rv_item_a_home_card)
        val txt_rv_item_a_home_card_title = view.findViewById<TextView>(R.id.txt_rv_item_a_home_card_title)
        val txt_rv_item_a_home_card_desc = view.findViewById<TextView>(R.id.txt_rv_item_a_home_card_desc)
    }

    inner class LinkHolder(view: View): RecyclerView.ViewHolder(view) {
        val container_rv_item_l_home_card = view.findViewById<View>(R.id.container_rv_item_l_home_card)
        val img_rv_item_l_home_card = view.findViewById<ImageView>(R.id.img_rv_item_l_home_card)
        val txt_rv_item_l_home_card_title = view.findViewById<TextView>(R.id.txt_rv_item_l_home_card_title)
        val txt_rv_item_l_home_card_curator = view.findViewById<TextView>(R.id.txt_rv_item_l_home_card_curator)
    }
}