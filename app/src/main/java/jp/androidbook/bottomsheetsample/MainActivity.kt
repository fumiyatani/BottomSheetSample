package jp.androidbook.bottomsheetsample

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: CustomBottomSheetBehavior<LinearLayout>

    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BottomSheet (ModalView) を設定
        // CoordinatorLayoutのChildViewとして設定しているものを設定する
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout) as CustomBottomSheetBehavior

    }

}
