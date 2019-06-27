package jp.androidbook.bottomsheetsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomSheetRecyclerViewAdapter.ListTappedListener {

    private lateinit var bottomSheetBehavior: CustomBottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BottomSheet (ModalView) を設定
        // CoordinatorLayoutのChildViewとして設定しているものを設定する
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout) as CustomBottomSheetBehavior

        sampleButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "ボタンタップ", Toast.LENGTH_SHORT).show()
        }

        val list = ArrayList<String>()
        for (i in 0 .. 180) {
            list.add("テキスト $i")
        }
        recyclerView.adapter = BottomSheetRecyclerViewAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        bottomSheetBehavior.setupCallback(object : CustomBottomSheetBehavior.BottomSheetStateChangeListener {
            override fun changeBottomSheetState(state: Int) {
                when(state) {
                    BottomSheetBehavior.STATE_COLLAPSED -> headerTextView.text = "Collapsed"
                    BottomSheetBehavior.STATE_EXPANDED -> headerTextView.text = "Expanded"
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> headerTextView.text = "Half_Expanded"
                }
            }
        })
    }

    override fun onClickList(text: String) {
        Toast.makeText(this, "りすとたっぷ $text", Toast.LENGTH_SHORT).show()
    }
}
