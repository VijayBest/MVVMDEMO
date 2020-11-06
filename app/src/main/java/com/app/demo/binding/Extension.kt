@file:JvmName("ViewExt")
@file:Suppress("unused")
package com.enact.binding


import android.view.View

import org.jetbrains.annotations.NotNull

@NotNull
fun View.setVisibleOrGone(visibility: Boolean?) {
    val visible = if (visibility == true) View.VISIBLE else View.GONE
    this.visibility = visible
}


fun View.setVisibleOrInvisible(visibility: Boolean?) {
    val visible = if (visibility == true) View.VISIBLE else View.INVISIBLE
    this.visibility = visible
}


