package com.example.androidgallery

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.androidgallery.adapters.GalleryImageAdapter
import com.example.androidgallery.adapters.GalleryRecycleViewAdapter

class MainActivity : AppCompatActivity() {
    // Hardcoded images
    val gallery = listOf(
        "https://66.media.tumblr.com/bb63c4a4683fd3748ade46ff86b589b3/tumblr_p72tnfGxAr1vlb8rjo1_1280.jpg",
        "https://thumbs.dreamstime.com/b/environment-earth-day-hands-trees-growing-seedlings-bokeh-green-background-female-hand-holding-tree-nature-field-gra-130247647.jpg",
        "https://cdn.pixabay.com/photo/2015/06/19/21/24/the-road-815297__340.jpg",
        "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This variable will be used to handle status of gallery
        // Find View with ID 'galleryStatus' and sign to variable 'imgStats'
        val imgStats : TextView = findViewById(R.id.galleryStatus)

        // Create initial value for gallery status View = 1/(size of gallery array witch declared above)
        val tempVar = "1/${gallery.size}"

        // Set text property of gallery status on initial value
        imgStats.text = tempVar

        // Find view pager element from 'activity_main.xml' by id
        val viewPager = findViewById<ViewPager>(R.id.galleryViewPager)

        val viewPagerAdapter = GalleryImageAdapter(this, gallery)

        // Set pager adapter to View Pager
        viewPager.adapter = viewPagerAdapter

        // Configuration for Recycler View
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.gallery_recycle_view)
        recyclerView.layoutManager = layoutManager

        // Set Gallery Recycler View Adapter
        val recyclerViewAdapter = GalleryRecycleViewAdapter(gallery, imgStats, viewPager)
        recyclerView.adapter = recyclerViewAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                val tempVar = "${position + 1}/${gallery.size}"
                imgStats.text = tempVar
            }
        })
    }
}
