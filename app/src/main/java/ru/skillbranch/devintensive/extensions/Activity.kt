package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.R.attr.top
import android.R.attr.bottom
import android.graphics.Rect
import android.opengl.ETC1.getHeight
import android.util.TypedValue
import android.view.ViewGroup
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getHeight
import android.util.Log
import android.view.View


fun Activity.hideKeyboard(): Unit{
    val view = this.currentFocus
    view?.let { v ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
    }
}


fun calculateHeightDiff(activity: Activity):Int{
    val r = Rect()
    val view = activity.currentFocus
    view?.getWindowVisibleDisplayFrame(r)

    val screenHeight:Int? = view?.rootView?.height
    if (screenHeight != null) {
        val heightDifference = screenHeight - (r.bottom - r.top)
        Log.d("M_Keyboard Size", "Size: $heightDifference $screenHeight $r")
        return heightDifference
    }else {
        return 0
    }
}

fun Activity.isKeyboardOpen():Boolean{
        return calculateHeightDiff(this) > 200
    }

fun Activity.isKeyboardClosed():Boolean{
    return calculateHeightDiff(this) < 200
}



