import com.ildango.capstone.BottomSheetClickListener
import com.ildango.capstone.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ildango.capstone.databinding.FragmentSortingBottomSheetBinding


interface SortingSheetClickListener {
    fun onButtonClicked(id: Int)
}

class SortingSheetFragment() : BottomSheetDialogFragment() {

    private var _binding: FragmentSortingBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetClickListener: BottomSheetClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSortingBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "SortingSheet"
    }



    // 글자 클릭시 변화주기 추가

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
