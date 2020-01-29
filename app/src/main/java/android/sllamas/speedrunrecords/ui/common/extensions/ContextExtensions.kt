package android.sllamas.speedrunrecords.ui.common.extensions

import android.content.Context
import android.widget.Toast

fun Context.showToast(messageKey: Int, lenght: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, messageKey, lenght).show()