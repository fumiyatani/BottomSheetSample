package jp.androidbook.bottomsheetsample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BottomSheet (ModalView) を設定
        // CoordinatorLayoutのChildViewとして設定しているものを設定する
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
        bottomSheetBehavior.setBottomSheetCallback(CustomBottomSheetCallback())
    }

    class CustomBottomSheetCallback: BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(view: View, state: Int) {
            when (state) {
                BottomSheetBehavior.STATE_DRAGGING -> Log.d("onStateChanged", "STATE_DRAGGING")
                BottomSheetBehavior.STATE_SETTLING -> Log.d("onStateChanged", "STATE_SETTLING")
                BottomSheetBehavior.STATE_EXPANDED -> Log.d("onStateChanged", "STATE_EXPANDED")
                BottomSheetBehavior.STATE_COLLAPSED -> Log.d("onStateChanged", "STATE_COLLAPSED")
                BottomSheetBehavior.STATE_HIDDEN -> Log.d("onStateChanged", "STATE_HIDDEN")
                BottomSheetBehavior.STATE_HALF_EXPANDED -> Log.d("onStateChanged", "STATE_HALF_EXPANDED")
            }
        }

        override fun onSlide(view: View, offset: Float) {

        }
    }
}
