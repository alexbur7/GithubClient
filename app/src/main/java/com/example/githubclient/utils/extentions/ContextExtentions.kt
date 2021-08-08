package com.example.githubclient.utils.extentions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes res: Int) {
    Toast.makeText(this, this.getString(res), Toast.LENGTH_SHORT).show()
}