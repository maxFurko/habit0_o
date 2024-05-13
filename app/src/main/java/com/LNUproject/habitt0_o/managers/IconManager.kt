package com.LNUproject.habitt0_o.managers

import android.content.Context
import android.graphics.drawable.Drawable
import com.LNUproject.habitt0_o.R
import com.LNUproject.habitt0_o.models.IconModel

class IconManager(context: Context) {

    val iconModels = ArrayList<IconModel>()

    init {
        val iconKeys = context.resources.getStringArray(R.array.HabitIconKeys)
        val iconTitles = context.resources.getStringArray(R.array.HabitIconTitles)
        val iconsTypedArray = context.resources.obtainTypedArray(R.array.HabitIcons)

        for(i in iconTitles.indices) {
            val key = iconKeys[i]
            val title = iconTitles[i]
            val drawable = iconsTypedArray.getDrawable(i)!!
            val iconModel = IconModel(key, drawable, title)

            iconModels.add(iconModel)
        }

        iconsTypedArray.recycle()
    }

    fun getIconByKey(key : String?) : Drawable? {
        if(key == null) return null

        return getIconModelByKey(key)?.drawable
    }

    fun getIconModelByKey(key : String?) : IconModel? {
        if(key == null) return null

        for(iconModel in iconModels) {
            if(iconModel.key == key) return iconModel
        }

        return null
    }
}