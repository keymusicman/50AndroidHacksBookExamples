package com.maleev.learning.a50androidhacks.hacks.hack3

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.view.children
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.utils.obtainStyledAttributes

class CascadeLayout : ViewGroup {
    var horizontalSpacing: Int =
        DefaultHorizontalSpacing
        set(value) {
            field = value
            requestLayout()
        }

    var verticalSpacing: Int =
        DefaultVerticalSpacing
        set(value) {
            field = value
            requestLayout()
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initUi(context, attrs)
    }

    private fun initUi(context: Context, attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.CascadeLayout)
        {
            horizontalSpacing =
                getDimensionPixelSize(
                    R.styleable.CascadeLayout_horizontalSpacing,
                    DefaultHorizontalSpacing
                )
            verticalSpacing = getDimensionPixelSize(
                R.styleable.CascadeLayout_verticalSpacing,
                DefaultVerticalSpacing
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var height: Int = paddingTop
        var width: Int = paddingLeft

        children.forEachIndexed { index, child ->
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val lp = child.layoutParams as LayoutParams

            lp.x = width
            lp.y = height

            if (index != childCount - 1) {
                width += if (lp.horizontalSpacing > 0) lp.horizontalSpacing else horizontalSpacing
                height += if (lp.verticalSpacing > 0) lp.verticalSpacing else verticalSpacing
            }
        }

        children.last().apply {
            height += measuredHeight
            width += measuredWidth
        }

        setMeasuredDimension(
            resolveSize(width, widthMeasureSpec),
            View.resolveSize(height, heightMeasureSpec)
        )
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        children.forEach { child ->
            val lp = child.layoutParams as LayoutParams
            child.layout(lp.x, lp.y, lp.x + child.measuredWidth, lp.y + child.measuredHeight)
        }
    }

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams =
        LayoutParams(
            WRAP_CONTENT,
            WRAP_CONTENT
        )

    override fun generateLayoutParams(attrs: AttributeSet): ViewGroup.LayoutParams =
        LayoutParams(context, attrs)

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): ViewGroup.LayoutParams =
        LayoutParams(
            p.width,
            p.height
        )

    class LayoutParams : ViewGroup.LayoutParams {
        var x: Int = 0
        var y: Int = 0
        var horizontalSpacing: Int = 0
        var verticalSpacing: Int = 0

        constructor(c: Context, attrs: AttributeSet) : super(c, attrs) {
            c.obtainStyledAttributes(attrs, R.styleable.CascadeLayout_LayoutParams) {
                horizontalSpacing = getDimensionPixelSize(
                    R.styleable.CascadeLayout_LayoutParams_layout_horizontal_spacing,
                    0
                )
                verticalSpacing = getDimensionPixelSize(
                    R.styleable.CascadeLayout_LayoutParams_layout_vertical_spacing,
                    0
                )
            }
        }

        constructor(width: Int, height: Int) : super(width, height)

    }

    companion object {
        const val DefaultVerticalSpacing = 30
        const val DefaultHorizontalSpacing = 20
    }
}