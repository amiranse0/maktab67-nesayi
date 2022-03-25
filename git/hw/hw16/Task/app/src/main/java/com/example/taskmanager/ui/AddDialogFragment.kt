import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.AddFragmentDialogBinding
import com.example.taskmanager.ui.DatePickerDialog
import com.example.taskmanager.ui.TimePickerDialog
import com.example.taskmanager.ui.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.viewmodel.SharedViewModel

class AddDialogFragment : DialogFragment(R.layout.add_fragment_dialog) {
    private lateinit var binding: AddFragmentDialogBinding

    private lateinit var getTask: GetTask
    private var timePicked = ""
    private var datePicked = ""

    private val viewModel: SharedViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddFragmentDialogBinding.bind(view)

        createTask()
        pickDate()
        pickTime()
    }

    private fun createTask() {

        val username = viewModel.username

        binding.saveTaskButton.setOnClickListener {
            getTask.getTask(
                Task(
                    title = binding.titleInputTextDialog.text.toString(),
                    description = binding.descriptionInputTextDialog.text.toString(),
                    date = datePicked,
                    time = timePicked,
                    situationOfTask = if (binding.isDoneCheckButton.isChecked) SituationOfTask.DONE else SituationOfTask.TODO,
                    userUserName = username
                )
            )

            dismiss()
        }
    }

    private fun pickTime() {
        binding.timePickerButton.setOnClickListener {
            val timeDialog = TimePickerDialog()
            timeDialog.show(parentFragmentManager, "Time")

            timeDialog.setGetTime(object : TimePickerDialog.GetTime {
                override fun getTimeFromDialog(time: String) {
                    timePicked = time
                }
            })
        }
    }

    private fun pickDate(){

        binding.datePickerButton.setOnClickListener {
            val dateDialog = DatePickerDialog()
            dateDialog.show(parentFragmentManager, "Date")

            dateDialog.setGetDate(object : DatePickerDialog.GetDate {
                override fun getDateFromDialog(date: String) {
                    datePicked = date
                }
            })
        }
    }

    interface GetTask {
        fun getTask(task: Task)
    }

    fun setGetTask(getTask: GetTask) {
        this.getTask = getTask
    }
}