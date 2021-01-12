package com.techyourchance.dagger2course.screens.common.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import javax.inject.Inject

class ImageLoader @Inject constructor(private val activity: BaseActivity) {

    private val requestOptions = RequestOptions().centerCrop()

    fun loadImage(imageUrl: String, target: ImageView) {
        Glide.with(activity).load(imageUrl).into(target)
    }

}