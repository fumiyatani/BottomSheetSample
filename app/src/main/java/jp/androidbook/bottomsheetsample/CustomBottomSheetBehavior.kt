package jp.androidbook.bottomsheetsample

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

import com.google.android.material.bottomsheet.BottomSheetBehavior

class CustomBottomSheetBehavior<T : View>(context: Context, attrs: AttributeSet) :
    BottomSheetBehavior<T>(context, attrs), GestureDetector.OnGestureListener {

    private val customBottomSheetCallback: CustomBottomSheetCallback<T>

    private val gestureDetector: GestureDetector

    init {
        customBottomSheetCallback = CustomBottomSheetCallback(this)
        this.setBottomSheetCallback(customBottomSheetCallback)
        gestureDetector = GestureDetector(context, this)
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: T, event: MotionEvent): Boolean {
        // フリック操作かタップ操作を判定するためのGestureDetectorにeventを渡す。
        gestureDetector.onTouchEvent(event)
        // 画面上のタッチ発生位置
        val touchEventY = event.y

        // BottomSheetBehavior の位置
        val bottomSheetY = child.y

        // BottomSheetBehavior の位置に HeaderView の高さの 56dp を足すことによって
        // HeaderViewの高さとしている
        val headerViewHeight = bottomSheetY + Util.convertDp2Px(56.0f, parent.context)

        // BottomSheet 内の HeaderView をタッチしているかを判定している
        // もし HeaderView の範囲内であれば BottomSheet にタッチイベントを渡すようにしている
        return touchEventY >  bottomSheetY && touchEventY < headerViewHeight
    }

    override fun onDown(motionEvent: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(motionEvent: MotionEvent) {

    }

    override fun onSingleTapUp(motionEvent: MotionEvent): Boolean {
        // 指が画面に押下され、画面から指が離れると呼び出される
        customBottomSheetCallback.isFling = false
        customBottomSheetCallback.isTouch = true
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
        customBottomSheetCallback.isTouch = false
        return false
    }

    internal inner class CustomBottomSheetCallback<T : View>(private val bottomSheetBehavior: CustomBottomSheetBehavior<T>) :
        BottomSheetBehavior.BottomSheetCallback() {

        // Touchのイベントを検知するためのフラグ
        // 初期はfalse
        var isTouch = false

        // Touchのイベントを検知するためのフラグ
        // 初期はfalse
        var isFling = false

        private var offset: Float = 0.toFloat()

        override fun onStateChanged(view: View, state: Int) {
            if (state == STATE_COLLAPSED) {
                if (isTouch && !isFling && offset < 0.1) {
                    bottomSheetBehavior.state = STATE_EXPANDED
                    isTouch = false
                }
            }
        }

        override fun onSlide(view: View, offset: Float) {
            this.offset = offset
        }
    }
}
