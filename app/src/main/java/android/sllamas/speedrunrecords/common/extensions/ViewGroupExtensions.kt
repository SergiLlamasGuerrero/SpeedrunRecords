package android.sllamas.speedrunrecords.common.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(
        layoutId, this,
        attachToRoot
    )