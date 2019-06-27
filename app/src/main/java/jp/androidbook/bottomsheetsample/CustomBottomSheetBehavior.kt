package jp.androidbook.bottomsheetsample

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CustomBottomSheetBehavior<T : View>(context: Context, attrs: AttributeSet) :
    BottomSheetBehavior<T>(context, attrs), GestureDetector.OnGestureListener {

    private lateinit var customBottomSheetCallback: CustomBottomSheetCallback<T>

    // 上下スワイプを検知するために必要
    private val gestureDetector: GestureDetector = GestureDetector(context, this)

    interface BottomSheetStateChangeListener {
        fun changeBottomSheetState(state: Int)
    }

    fun setupCallback(listener: BottomSheetStateChangeListener) {
        customBottomSheetCallback = CustomBottomSheetCallback(this, listener)
        this.setBottomSheetCallback(customBottomSheetCallback)
    }

    override fun onDown(motionEvent: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(motionEvent: MotionEvent) {

    }

    override fun onSingleTapUp(motionEvent: MotionEvent): Boolean {
        // 指が画面に押下され、画面から指が離れると呼び出される
        customBottomSheetCallback.isFling = false
        customBottomSheetCallback.isTapped = true
        return false
    }

    override fun onScroll(
        motionEvent: MotionEvent,
        motionEvent1: MotionEvent,
        v: Float,
        v1: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(motionEvent: MotionEvent) {

    }

    override fun onFling(
        motionEvent: MotionEvent,
        motionEvent1: MotionEvent,
        v: Float,
        v1: Float
    ): Boolean {
        // 画面から指が加速度を付いた状態で話されると呼び出される。
        customBottomSheetCallback.isFling = true
        customBottomSheetCallback.isTapped = false
        return false
    }

    internal inner class CustomBottomSheetCallback<T : View>(private val bottomSheetBehavior: CustomBottomSheetBehavior<T>, private val listener: BottomSheetStateChangeListener) :
        BottomSheetBehavior.BottomSheetCallback() {

        // タップのイベントを検知するためのフラグ
        // 初期はfalse
        var isTapped = false

        // フリックのイベントを検知するためのフラグ
        // 初期はfalse
        var isFling = false

        private var offset: Float = 0.toFloat()

        override fun onStateChanged(view: View, state: Int) {
            if (state == STATE_COLLAPSED) {
                if (isTapped && !isFling && offset < 0.1) {
                    bottomSheetBehavior.state = STATE_EXPANDED
                    isTapped = false
                    return
                }
                listener.changeBottomSheetState(STATE_COLLAPSED)
            }
            if(state == STATE_EXPANDED) {
                listener.changeBottomSheetState(STATE_EXPANDED)
            }
        }

        override fun onSlide(view: View, offset: Float) {
            this.offset = offset
        }
    }
}
