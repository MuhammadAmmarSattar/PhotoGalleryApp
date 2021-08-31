package com.app.projectname.ui.component.customTextView.customEditext

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.app.projectname.R
import com.app.projectname.ui.component.customTextView.enum.FontStyle

open class CustomEditText constructor(context: Context,attributeSet: AttributeSet):
    AppCompatEditText(context,attributeSet) {
    private val defaultFontStyle = FontStyle.REGULAR

    init {
        var selectedFontStyle = defaultFontStyle
        val typedArray : TypedArray=
            context.obtainStyledAttributes(R.styleable.CustomEditText)
        for(i in 0..typedArray.indexCount){
            when (val attr = typedArray.getIndex(i)) {
                R.styleable.CustomEditText_textTypeFont -> {
                    selectedFontStyle = FontStyle.fromId(typedArray.getInt(attr, defaultFontStyle.id))
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