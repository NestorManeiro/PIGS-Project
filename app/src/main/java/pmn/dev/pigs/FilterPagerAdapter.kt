import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import pmn.dev.pigs.MainActivity
import pmn.dev.pigs.R

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
            }
            2 -> {
                (context as MainActivity).datePickerFilter = layout.findViewById(R.id.date_picker_filter)
            }
        }

        views.add(layout) // Agrega la vista a la lista views
        return layout
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
