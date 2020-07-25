package com.wkb.jet2tt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wkb.jet2tt.R
import com.wkb.jet2tt.model.ArticalModel
import de.hdodenhof.circleimageview.CircleImageView


class ArticlesListAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<ArticlesListAdapter.ServiceHolder>() {


    var articleList: ArrayList<ArticalModel> = ArrayList()

    fun setData(articleList: ArrayList<ArticalModel>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_article_list, parent, false)
        return ServiceHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int) {

        holder.tvArticleContent.text = articleList[position].content

        holder.tvUserName.text = articleList[position].user!![0]!!.name
        Glide.with(mContext).load(articleList[position].user!![0]!!.avatar)
            .into(holder.ivAvatar)
        Glide.with(mContext).load(articleList[position].media!![0]!!.image)
            .into(holder.ivProfileIcon)
        holder.tvUserDesignation.text = articleList[position].user!![0]!!.designation

        var likes = ""
        if (Math.abs(articleList[position].likes!! / 1000000) > 1) {
            likes = (articleList[position].likes!! / 1000000).toString() + "m"

        } else if (Math.abs(articleList[position].likes!! / 1000) > 1) {
            likes = (articleList[position].likes!! / 1000).toString() + "k"

        } else {
            likes = articleList[position].likes.toString()

        }

        var comment = ""
        if (Math.abs(articleList[position].comments!! / 1000000) > 1) {
            comment = (articleList[position].comments!! / 1000000).toString() + "m"

        } else if (Math.abs(articleList[position].comments!! / 1000) > 1) {
            comment = (articleList[position].comments!! / 1000).toString() + "k"

        } else {
            comment = articleList[position].comments.toString()

        }

        holder.tvArticleLike.text = likes + " Likes"
        holder.tvArticleComment.text = comment + " Comments"
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class ServiceHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var tvUserName: TextView = mView.findViewById(R.id.tvUserName)
        var ivProfileIcon: CircleImageView = mView.findViewById(R.id.ivProfileIcon)
        var tvUserDesignation: TextView = mView.findViewById(R.id.tvUserDesignation)
        var ivAvatar: ImageView = mView.findViewById(R.id.ivAvatar)
        var tvArticleContent: TextView = mView.findViewById(R.id.tvArticleContent)
        var tvArticleLike: TextView = mView.findViewById(R.id.tvArticleLike)
        var tvArticleComment: TextView = mView.findViewById(R.id.tvArticleComment)
    }


}
