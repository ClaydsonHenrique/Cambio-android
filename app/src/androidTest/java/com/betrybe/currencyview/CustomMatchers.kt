package com.betrybe.currencyview

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.progressindicator.BaseProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withParentEqualsTo(id: Int): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with parent equals to $id")
    }

    override fun matchesSafely(item: View?): Boolean {
        var parent = item?.parent
        while (parent != null) {
            if ((parent as View).id == id) return true
            parent = item?.parent?.parent
        }

        return false
    }
}

fun withLayoutWidthEqualsToMatchParent(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_width equal to match_parent")
    }

    override fun matchesSafely(item: View?): Boolean {
        return item?.layoutParams?.width == ViewGroup.LayoutParams.MATCH_PARENT
    }
}

fun withLayoutHeightEqualsToMatchParent(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_height equal to match_parent")
    }

    override fun matchesSafely(item: View?): Boolean {
        return item?.layoutParams?.height == ViewGroup.LayoutParams.MATCH_PARENT
    }
}

fun withLayoutWidthEqualsToWrapContent(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_width equal to wrap_content")
    }

    override fun matchesSafely(item: View?): Boolean {
        return item?.layoutParams?.width == ViewGroup.LayoutParams.WRAP_CONTENT
    }
}

fun withLayoutHeightEqualsToWrapContent(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_height equal to wrap_content")
    }

    override fun matchesSafely(item: View?): Boolean {
        return item?.layoutParams?.height == ViewGroup.LayoutParams.WRAP_CONTENT
    }
}

fun withLayoutWidthEqualsTo(value: Int): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_width equal to $value")
    }

    override fun matchesSafely(item: View?): Boolean {
        val density = item!!.context.resources.displayMetrics.density
        val widthInDp = (item.layoutParams.width / density).toInt()
        return widthInDp == value
    }
}

fun withLayoutHeightEqualsTo(value: Int): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_height equal to $value")
    }

    override fun matchesSafely(item: View?): Boolean {
        val density = item!!.context.resources.displayMetrics.density
        val heightInDp = (item.layoutParams.height / density).toInt()
        return heightInDp == value
    }
}

fun withLinearLayoutVerticalOrientation(): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("with LinearLayout vertical orientation")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is LinearLayout) return false
        return item.orientation == LinearLayout.VERTICAL
    }
}

fun withLinearLayoutHorizontalOrientation(): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("with LinearLayout horizontal orientation")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is LinearLayout) return false
        return item.orientation == LinearLayout.HORIZONTAL
    }
}

fun withLayoutMarginTopEqualsTo(value: Int): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("with layout_marginTop equals to $value")
    }

    override fun matchesSafely(item: View?): Boolean {
        val layoutParams = item?.layoutParams
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val density = item.context.resources.displayMetrics.density
            val marginTopInDp = (layoutParams.topMargin / density).toInt()

            return marginTopInDp == value
        }
        return false
    }
}

fun withLayoutMarginBottomEqualsTo(value: Int): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("with layout_marginBottom equals to $value")
    }

    override fun matchesSafely(item: View?): Boolean {
        val layoutParams = item?.layoutParams
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val density = item.context.resources.displayMetrics.density
            val marginBottomInDp = (layoutParams.bottomMargin / density).toInt()

            return marginBottomInDp == value
        }
        return false
    }
}

fun withLayoutMarginStartEqualsTo(value: Int): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("with layout_marginStart equals to $value")
    }

    override fun matchesSafely(item: View?): Boolean {
        val layoutParams = item?.layoutParams
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val density = item.context.resources.displayMetrics.density
            val marginStartInDp = (layoutParams.marginStart / density).toInt()

            return marginStartInDp == value
        }
        return false
    }
}

fun withLayoutMarginEndEqualsTo(value: Int): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("with layout_marginEnd equals to $value")
    }

    override fun matchesSafely(item: View?): Boolean {
        val layoutParams = item?.layoutParams
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val density = item.context.resources.displayMetrics.density
            val marginEndInDp = (layoutParams.marginEnd / density).toInt()

            return marginEndInDp == value
        }
        return false
    }
}

fun withLayoutMarginEqualsTo(top: Int, bottom: Int, start: Int, end: Int): Matcher<View> =
    object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) {
            description?.appendText(
                "with layout_margin equals to top:$top, bottom:$bottom, start:$start, end:$end",
            )
        }

        override fun matchesSafely(item: View?): Boolean {
            val layoutParams = item?.layoutParams
            if (layoutParams is ViewGroup.MarginLayoutParams) {
                val density = item.context.resources.displayMetrics.density
                val marginTopInDp = (layoutParams.topMargin / density).toInt()
                val marginBottomInDp = (layoutParams.bottomMargin / density).toInt()
                val marginStartInDp = (layoutParams.marginStart / density).toInt()
                val marginEndInDp = (layoutParams.marginEnd / density).toInt()

                return marginTopInDp == top &&
                    marginBottomInDp == bottom &&
                    marginStartInDp == start &&
                    marginEndInDp == end
            }

            return false
        }
    }

fun withLayoutMarginsEqualsTo(value: Int): Matcher<View> =
    withLayoutMarginEqualsTo(value, value, value, value)

fun withLayoutConstraintTopToTopOfParent(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_constraintTop_toTopOf to parent")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item?.layoutParams is ConstraintLayout.LayoutParams) {
            val layoutParams = item.layoutParams as ConstraintLayout.LayoutParams
            val topConstraintTo = layoutParams.topToTop
            return topConstraintTo == ConstraintLayout.LayoutParams.PARENT_ID
        }

        return false
    }
}

fun withLayoutConstraintBottomToBottomOfParent(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_constraintBottom_toBottomOf to parent")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item?.layoutParams is ConstraintLayout.LayoutParams) {
            val layoutParams = item.layoutParams as ConstraintLayout.LayoutParams
            val bottomConstraintTo = layoutParams.bottomToBottom
            return bottomConstraintTo == ConstraintLayout.LayoutParams.PARENT_ID
        }

        return false
    }
}

fun withLayoutConstraintStartToStartOfParent(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_constraintStart_toStartOf to parent")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item?.layoutParams is ConstraintLayout.LayoutParams) {
            val layoutParams = item.layoutParams as ConstraintLayout.LayoutParams
            val startConstraintTo = layoutParams.startToStart
            return startConstraintTo == ConstraintLayout.LayoutParams.PARENT_ID
        }

        return false
    }
}

fun withLayoutConstraintEndToEndOfParent(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_constraintEnd_toEndOf to parent")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item?.layoutParams is ConstraintLayout.LayoutParams) {
            val layoutParams = item.layoutParams as ConstraintLayout.LayoutParams
            val endConstraintTo = layoutParams.endToEnd
            return endConstraintTo == ConstraintLayout.LayoutParams.PARENT_ID
        }

        return false
    }
}

fun withLayoutConstraintsOfParent(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with all layout_constraint to parent")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item?.layoutParams is ConstraintLayout.LayoutParams) {
            val layoutParams = item.layoutParams as ConstraintLayout.LayoutParams
            val topConstraintTo = layoutParams.topToTop
            val bottomConstraintTo = layoutParams.bottomToBottom
            val startConstraintTo = layoutParams.startToStart
            val endConstraintTo = layoutParams.endToEnd
            return topConstraintTo == ConstraintLayout.LayoutParams.PARENT_ID &&
                bottomConstraintTo == ConstraintLayout.LayoutParams.PARENT_ID &&
                startConstraintTo == ConstraintLayout.LayoutParams.PARENT_ID &&
                endConstraintTo == ConstraintLayout.LayoutParams.PARENT_ID
        }

        return false
    }
}

fun withLayoutConstraintTopToTopOf(id: Int): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_constraintTop_toTopOf to $id")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item?.layoutParams is ConstraintLayout.LayoutParams) {
            val layoutParams = item.layoutParams as ConstraintLayout.LayoutParams
            val topConstraintTo = layoutParams.topToTop
            return id == topConstraintTo
        }

        return false
    }
}

fun withMaterialTextViewGravityEqualsTo(gravity: Int): Matcher<View> =
    object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) {
            description?.appendText("with MaterialTextView with gravity equal to $gravity")
        }

        override fun matchesSafely(item: View?): Boolean {
            if (item is MaterialTextView) {
                return gravity == item.gravity
            }

            return false
        }
    }

fun withMaterialTextViewGravityEqualsToCenter(): Matcher<View> =
    withMaterialTextViewGravityEqualsTo(Gravity.CENTER)

fun withTextInputLayoutHintEqualsTo(hint: String): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("with TextInputLayoutHint hint equal to $hint")
        }

        override fun matchesSafely(item: View?): Boolean {
            if (item is TextInputLayout) {
                return item.hint == hint
            }

            return false
        }
    }

fun withMaterialTextViewTextSizeEqualsTo(value: Int): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("with MaterialTextView textSize equals to")
        }

        override fun matchesSafely(item: View?): Boolean {
            if (item is MaterialTextView) {
                val density = item.context.resources.displayMetrics.density
                val textSizeSp = (item.textSize / density).toInt()

                return textSizeSp == value
            }

            return false
        }
    }

fun withVisibility(value: Int): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with visibility $value")
    }

    override fun matchesSafely(item: View?): Boolean {
        return item?.visibility == value
    }
}

fun withLayoutGravityEqualsTo(value: Int): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with layout_gravity equals to $value")
    }

    override fun matchesSafely(item: View?): Boolean {
        return when (item?.layoutParams) {
            is LinearLayout.LayoutParams -> {
                val layoutParams = item.layoutParams as LinearLayout.LayoutParams
                layoutParams.gravity == value
            }

            is FrameLayout.LayoutParams -> {
                val layoutParams = item.layoutParams as FrameLayout.LayoutParams
                layoutParams.gravity == value
            }

            else -> {
                false
            }
        }
    }
}

fun withLayoutGravityEqualsToCenter(): Matcher<View> =
    withLayoutGravityEqualsTo(Gravity.CENTER)

fun withCircularProgressIndicatorIndeterminate(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("with CircularProgressIndicator indeterminate")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item is BaseProgressIndicator<*>) {
            return item.isIndeterminate
        }

        return false
    }
}

fun hasDrawable(): BoundedMatcher<View, ImageView> =
    object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

        override fun describeTo(description: Description?) {
            description?.appendText("has drawable")
        }

        override fun matchesSafely(item: ImageView?): Boolean {
            return item?.drawable != null
        }
    }
