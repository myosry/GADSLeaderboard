package com.noha.gadsleaderboard.utils

import android.app.Activity
import android.content.ContextWrapper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mustafayosri.gadsleaderboard.R
import com.squareup.picasso.Picasso

@BindingAdapter("loadImageUrl")
fun loadImage(imageView: ImageView, url: String?) {

    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_baseline_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(imageView)
}


fun hideKeyboard(view: View) {
    var context = view.context
    while (context !is Activity && context is ContextWrapper) {
        context = context.baseContext
    }

    val inputMethodManager =
        (context as Activity).getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

}