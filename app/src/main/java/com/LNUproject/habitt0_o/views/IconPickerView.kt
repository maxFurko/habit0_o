package com.LNUproject.habitt0_o.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.LNUproject.habitt0_o.adapters.IconSelectionAdapter
import com.LNUproject.habitt0_o.databinding.LayoutIconPickerBinding
import com.LNUproject.habitt0_o.managers.IconManager
import com.LNUproject.habitt0_o.models.IconModel

class IconPickerView(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private val binding : LayoutIconPickerBinding = LayoutIconPickerBinding.inflate(LayoutInflater.from(context), this, true)

    private val iconSelectionAdapter : IconSelectionAdapter

    var iconManager : IconManager? = null
    set(value) {
        field = value

        value ?: return

        iconSelectionAdapter.data = value.iconModels
        iconSelectionAdapter.notifyDataSetChanged()
    }

    init {

        binding.recyclerView.layoutManager = GridLayoutManager(context, 5)

        iconSelectionAdapter = IconSelectionAdapter(context, ArrayList())
        binding.recyclerView.adapter = iconSelectionAdapter
    }

    fun setSelectedIcon(iconModel: IconModel?) {
        iconSelectionAdapter.selectedIconModel = iconModel
        iconSelectionAdapter.notifyDataSetChanged()
    }

    fun setIconSelectedListener(iconSelectedListener: IconSelectionAdapter.IconSelectedListener) {
        iconSelectionAdapter.iconSelectedListener = iconSelectedListener
    }
}