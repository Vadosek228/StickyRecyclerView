package com.example.stickyrecyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemDecoration(
    private val context: Context,
    private val headerHeight: Int,
    private val isSticky: Boolean,
    private val sectionCallback: SectionCallback
) : RecyclerView.ItemDecoration() {

    private var headerOffset = headerHeight
    private var sticky = isSticky

    private var headerView: View? = null
    private lateinit var tvTitle: TextView

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if(sectionCallback.isSectionHeader(position)) {
            outRect.top = headerOffset
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        if (headerView == null) {
            headerView = inflateHeader(parent)
            tvTitle = headerView!!.findViewById(R.id.text_view_item_main_text)
            fixLayoutSize(headerView!!, parent)
        }

        var prefTitlt = ""
        for (i in 0..parent.childCount) {
            if (parent.getChildAt(i) != null) {
                val child: View = parent.getChildAt(i)
                val childPosition: Int = parent.getChildAdapterPosition(child)
                val title: String = sectionCallback.getSectionHeaderName(childPosition)
                tvTitle.setText(title)
                if (prefTitlt != title || sectionCallback.isSectionHeader(childPosition)) { // todo возможен косяк
                    drawHeader(c, child, headerView!!)
                    prefTitlt = title
                }
            }
        }
    }

    private fun drawHeader(c: Canvas, child: View, headerView: View) {
        c.save()
        if (sticky) {
            c.translate(0f, Math.max(0, child.top - headerView.height).toFloat())
        } else {
            c.translate(0f, (child.top - headerView.height).toFloat())
        }
        headerView.draw(c)
        c.restore()
    }

    private fun fixLayoutSize(headerView: View, parent: ViewGroup) {
        val widthSpec: Int = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heighrSpec: Int = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        val childWidth = ViewGroup.getChildMeasureSpec(widthSpec, parent.paddingLeft + parent.paddingRight, headerView.layoutParams.width)
        val childHeight = ViewGroup.getChildMeasureSpec(heighrSpec, parent.paddingTop + parent.paddingBottom, headerView.layoutParams.height)

        headerView.measure(childWidth, childHeight)
        headerView.layout(0,0, headerView.measuredWidth, headerView.measuredHeight)
    }

    private fun inflateHeader(recyclerView: RecyclerView) : View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_main_header, recyclerView, false)
        return view
    }

    public interface SectionCallback {
        public fun isSectionHeader(position: Int) : Boolean
        public fun getSectionHeaderName(position: Int) : String
    }
}