package jp.androidbook.bottomsheetsample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class CustomBottomSheetBehavior<T extends View> extends BottomSheetBehavior<T> implements GestureDetector.OnGestureListener {

    private CustomBottomSheetCallback<T> customBottomSheetCallback;

    private GestureDetector gestureDetector;

    public CustomBottomSheetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        customBottomSheetCallback = new CustomBottomSheetCallback<>(this);
        this.setBottomSheetCallback(customBottomSheetCallback);
        gestureDetector = new GestureDetector(context, this);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, T child, MotionEvent event) {
        // 画面上のタッチ発生位置
        float touchEventScreenY = event.getY();

        // BottomSheetBehavior のタッチイベントの位置
        float touchEventBottomSheetY = child.getY();

        // BottomSheet は peekHeight の状態だと画面の下にいるので、
        // BottomSheet 以下の場合は背後の View にイベントを伝播させないようにtrueを返却する
        return touchEventScreenY > touchEventBottomSheetY;
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, T child, MotionEvent event) {
        // フリック操作かタップ操作を判定するためのGestureDetectorにeventを渡す。
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(parent, child, event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        // 指が画面に押下され、画面から指が離れると呼び出される
        customBottomSheetCallback.isFling = false;
        customBottomSheetCallback.isTouch = true;
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        // 画面から指が加速度を付いた状態で話されると呼び出される。
        customBottomSheetCallback.isFling = true;
        customBottomSheetCallback.isTouch = false;
        return false;
    }

    class CustomBottomSheetCallback<T extends View> extends BottomSheetCallback {

        private CustomBottomSheetBehavior<T> bottomSheetBehavior;

        // Touchのイベントを検知するためのフラグ
        // 初期はfalse
        private boolean isTouch = false;

        // Touchのイベントを検知するためのフラグ
        // 初期はfalse
        private boolean isFling = false;

        private float offset;

        CustomBottomSheetCallback(CustomBottomSheetBehavior<T> bottomSheetBehavior) {
            this.bottomSheetBehavior = bottomSheetBehavior;
        }

        @Override
        public void onStateChanged(@NonNull View view, int state) {
            if (state == STATE_COLLAPSED) {
                if (isTouch && !isFling && offset < 0.1) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    isTouch = false;
                }
            }
        }

        @Override
        public void onSlide(@NonNull View view, float offset) {
            this.offset = offset;
        }
    }
}
