package com.example.androidgallery.adapters

import com.example.androidgallery.R
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso



class GalleryImageAdapter(private val activity : Activity, private val images : List<String>) : PagerAdapter() {

    var layoutInflater : LayoutInflater? = null
    lateinit var builder:Dialog;

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun openWithPicasso(imageUrl : String, imageView: ImageView) {
        try {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView)
        }
        catch (e : Exception){println("openWithPicasso function: error$e")}
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = activity.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = layoutInflater!!.inflate(R.layout.gallery_pager_item, container, false)

        val imageView : ImageView = itemView.findViewById(R.id.galleryImageViewItem)

        val dis = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dis)

        imageView.minimumHeight = dis.heightPixels
        imageView.minimumWidth = dis.widthPixels

        openWithPicasso(images[position], imageView);

        itemView.setOnClickListener {
            showImage(images[position])
        }

        container.addView(itemView);
        return itemView
    }

    fun showImage(imageUrl : String) {
        builder = Dialog(activity)
        builder.setContentView(R.layout.zoom_image)

        val imageView : ImageView = builder.findViewById(R.id.popupImage)

        openWithPicasso(imageUrl, imageView)

//      Close Button
        val closeBtn : ImageButton = builder.findViewById(R.id.btnClose);

        closeBtn.setOnClickListener {
            builder.dismiss()
        }

        builder.getWindow().setBackgroundDrawable(ColorDrawable(Color.parseColor("#80000000")))
        builder.show()
    }
}


