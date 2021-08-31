package com.app.projectname.ui.component.customTextView

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.app.projectname.R
import com.app.projectname.ui.component.customTextView.enum.FontStyle

class CustomTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private val defaultFontStyle = FontStyle.REGULAR
    init {
        var selectedFontStyle = defaultFontStyle
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        //loop for future attributes addition
        for (i in 0..typedArray.indexCount) {
            when (val attr = typedArray.getIndex(i)) {
                R.styleable.CustomTextView_textFont -> {
                    selectedFontStyle =
                        FontStyle.fromId(typedArray.getInt(attr, defaultFontStyle.id))
                }
            }
        }
        typedArray.recycle()
        setupFont(selectedFontStyle)
    }
    private fun setupFont(selectedFontStyle: FontStyle) {
        val typeface = when (selectedFontStyle) {
            FontStyle.LIGHT ->
                ResourcesCompat.getFont(context, R.font.brown_light)

            FontStyle.REGULAR ->
                ResourcesCompat.getFont(context, R.font.brown_regular)

            FontStyle.BOLD ->
                ResourcesCompat.getFont(context, R.font.brown_bold)
        }

        setTypeface(typeface)
    }

}