package jp.androidbook.bottomsheetsample

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomSheetRecyclerViewAdapter.ListTappedListener {

    private lateinit var bottomSheetBehavior: CustomBottomSheetBehavior<LinearLayout>

    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BottomSheet (ModalView) を設定
        // CoordinatorLayoutのChildViewとして設定しているものを設定する
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout) as CustomBottomSheetBehavior

        val list = ArrayList<String>()
        for (i in 0 .. 30) {
            list.add("テキスト $i")
        }
        recyclerView.adapter = BottomSheetRecyclerViewAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun onClickList(text: String) {
        Toast.makeText(this, "りすとたっぷ $text", Toast.LENGTH_SHORT).show()
    }
}
