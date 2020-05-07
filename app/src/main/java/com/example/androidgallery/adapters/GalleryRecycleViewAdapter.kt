package com.example.androidgallery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.example.androidgallery.R
import java.lang.Exception

class GalleryRecycleViewAdapter(val images : List<String>,val textView: TextView,val viewPager: ViewPager) : RecyclerView.Adapter<GalleryRecycleViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //change View holder = single element inside recycle view
        val mainImage = itemView.findViewById(R.id.galleryImageViewHorizontal) as ImageView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = images[position]

        holder.mainImage.setOnClickListener {
            viewPager.currentItem = position
            val text = "${position + 1}/${images.size}"
            textView.text = text
        }

        openWithPicasso(imageUrl, holder.mainImage);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_recyclerview_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun openWithPicasso(imageUrl : String, imageView: ImageView) {
        try {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView)
        }
        catch (e : Exception){println("openWithPicasso function: error:$e")}
    }
}