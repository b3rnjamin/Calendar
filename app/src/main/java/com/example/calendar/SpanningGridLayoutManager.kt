package com.example.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil
import kotlin.math.roundToInt

/**
 * GridLayoutManager personalizado para dibujar grid con el alto total disponible del recyclerView que lo utilice
 */
class SpanningGridLayoutManager : GridLayoutManager {
    private val horizontalSpace: Int
        get() = width - paddingRight - paddingLeft

    private val verticalSpace: Int
        get() = height - paddingBottom - paddingTop

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, spanCount: Int) : super(context, spanCount)
    constructor(context: Context, spanCount: Int, orientation: Int, reverseLayout: Boolean) :
            super(context, spanCount, orientation, reverseLayout)

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateDefaultLayoutParams())
    }

    override fun generateLayoutParams(c: Context, attrs: AttributeSet): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateLayoutParams(c, attrs))
    }

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateLayoutParams(lp))
    }

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
        val layoutParams = generateDefaultLayoutParams()
        return super.checkLayoutParams(lp) &&
                layoutParams.width == lp.width &&
                layoutParams.height == lp.height
    }

    private fun spanLayoutSize(layoutParams: RecyclerView.LayoutParams): RecyclerView.LayoutParams {
        when (orientation) {
            HORIZONTAL -> layoutParams.width = (horizontalSpace / maxItemsInAllLines()).roundToInt()
            VERTICAL -> layoutParams.height = (verticalSpace / maxItemsInAllLines()).roundToInt()
        }

        return layoutParams
    }

    override fun canScrollVertically(): Boolean = false

    override fun canScrollHorizontally(): Boolean = false

    private fun maxItemsInAllLines(): Double = ceil(itemCount / spanCount.toDouble())
}