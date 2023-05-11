import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import pmn.dev.pigs.MainActivity
import pmn.dev.pigs.R
import java.time.LocalDate
import com.jakewharton.threetenabp.AndroidThreeTen
import java.time.LocalTime

class FilterPagerAdapter(private val context: Context, private val layouts: List<Int>) :
    PagerAdapter() {
    private val views = mutableListOf<View>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(layouts[position], container, false)
        container.addView(layout)

        when (position) {
            0 -> {
                (context as MainActivity).locationFilter = layout.findViewById(R.id.et_location_filter)
            }
            1 -> {
                (context as MainActivity).timePickerFilter = layout.findViewById(R.id.time_picker_filter)
                initializeTimePicker((context as MainActivity).timePickerFilter)
            }
            2 -> {
                (context as MainActivity).datePickerFilter = layout.findViewById(R.id.date_picker_filter)
                initializeDatePicker((context as MainActivity).datePickerFilter)
            }
        }

        views.add(layout) // Agrega la vista a la lista views
        return layout
    }

    private fun initializeTimePicker(timePicker: TimePicker) {
        val currentTime = LocalTime.now()
        timePicker.hour = currentTime.hour
        timePicker.minute = currentTime.minute
    }

    private fun initializeDatePicker(datePicker: DatePicker) {
        val today = LocalDate.now()
        datePicker.init(today.year, today.monthValue - 1, today.dayOfMonth, null)
    }


    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun getFilterViewAtPosition(position: Int): View? {
        return views[position]
    }

}
