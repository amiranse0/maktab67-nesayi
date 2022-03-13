import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.taskmanager.R
import com.example.taskmanager.databinding.AddFragmentDialogBinding
import com.example.taskmanager.ui.DatePickerDialog
import com.example.taskmanager.ui.TimePickerDialog

class AddDialogFragment:DialogFragment(R.layout.add_fragment_dialog) {
    private lateinit var binding :AddFragmentDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddFragmentDialogBinding.bind(view)

        pickDate()
        pickTime()
    }

    private fun pickTime() {
        binding.timePickerButton.setOnClickListener {
            val timeDialog = TimePickerDialog()
            timeDialog.show(parentFragmentManager, "Time")
        }
    }

    private fun pickDate() {
        binding.datePickerButton.setOnClickListener {
            val dateDialog = DatePickerDialog()
            dateDialog.show(parentFragmentManager, "Date")
        }
    }
}