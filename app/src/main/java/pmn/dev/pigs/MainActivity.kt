package pmn.dev.pigs

import FilterPagerAdapter
import TripAdapter
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import pmn.dev.pigs.model.Trip
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.abs
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tripAdapter: TripAdapter
    private lateinit var filterButton: Button
    internal lateinit var locationFilter: EditText
    internal lateinit var timePickerFilter: TimePicker
    internal lateinit var datePickerFilter: DatePicker
    private lateinit var applyFilterButton: Button
    private lateinit var filterContainer: ConstraintLayout
    private var tripsList: List<Trip> = ArrayList()
    private lateinit var filterPagerAdapter: FilterPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.trips_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        filterButton = findViewById(R.id.btn_show_filter)
        filterContainer = findViewById(R.id.filter_container)

        // Crear una lista de viajes de ejemplo
        tripsList = createSampleTrips()

        // Establecer el adaptador personalizado
        tripAdapter = TripAdapter(tripsList)
        recyclerView.adapter = tripAdapter

        filterButton.setOnClickListener {
            showFilter()
        }
    }

    private fun createSampleTrips(): List<Trip> {
        return listOf(
            Trip("John Doe", "Universidad", "Centro de la ciudad", "08:00", "12-05-2023"),
            Trip(
                "Jane Smith",
                "Estación de autobuses",
                "Facultad de Ciencias",
                "09:00",
                "12-05-2023"
            ),
            Trip(
                "Andres Rodriguez",
                "Santa Catalina",
                "Facultad de Informática",
                "10:00",
                "12-05-2023"
            ),
            Trip("Nestor Maneiro", "Facultad de Ciencias", "Santa Catalina", "11:00", "12-05-2023"),
            Trip(
                "Juan Garcia",
                "Facultad de Informática",
                "Estación de autobuses",
                "12:00",
                "12-05-2023"
            ),
            Trip("Ana Fernandez", "Centro de la ciudad", "Universidad", "13:00", "12-05-2023"),
            Trip("Pedro Perez", "Facultad de Informática", "Santa Catalina", "14:00", "12-05-2023"),
            Trip(
                "Maria Lopez",
                "Estación de autobuses",
                "Facultad de Ciencias",
                "15:00",
                "12-05-2023"
            ),
            Trip("Jose Gonzalez", "Santa Catalina", "Centro de la ciudad", "16:00", "12-05-2023"),
            Trip(
                "Laura Sanchez",
                "Facultad de Ciencias",
                "Estación de autobuses",
                "17:00",
                "12-05-2023"
            ),
        )
    }

    private fun showFilter() {
        if (filterContainer.childCount == 0) {
            val filterView = layoutInflater.inflate(R.layout.filter_layout, filterContainer, false)
            filterContainer.addView(filterView)

            val tabLayout = filterView.findViewById<TabLayout>(R.id.tab_layout)
            val viewPager = filterView.findViewById<ViewPager>(R.id.view_pager)
            val applyFilterButton = filterView.findViewById<Button>(R.id.btn_apply_filter)
            val resetFilterButton = filterView.findViewById<Button>(R.id.btn_reset_filter)

            val layouts = listOf(
                R.layout.location_filter,
                R.layout.time_picker_filter,
                R.layout.date_picker_filter
            )

            filterPagerAdapter = FilterPagerAdapter(this, layouts)
            viewPager.adapter = filterPagerAdapter

            tabLayout.setupWithViewPager(viewPager)
            tabLayout.getTabAt(0)?.text = "Ubicación"
            tabLayout.getTabAt(1)?.text = "Hora"
            tabLayout.getTabAt(2)?.text = "Fecha"

            applyFilterButton.setOnClickListener {
                applyFilter()
            }

            resetFilterButton.setOnClickListener {
                resetFilter()
            }
        }
        filterContainer.visibility = View.VISIBLE
    }


    private fun resetFilter() {
        locationFilter.setText("")
        val today = LocalDate.now()
        datePickerFilter.updateDate(today.year, today.monthValue - 1, today.dayOfMonth)
        timePickerFilter.hour = 0
        timePickerFilter.minute = 0

        tripAdapter = TripAdapter(tripsList)
        recyclerView.adapter = tripAdapter
    }

    private fun applyFilter() {
        val location = locationFilter.text.toString().lowercase().trim()
        val year = datePickerFilter.year
        val month = datePickerFilter.month
        val day = datePickerFilter.dayOfMonth
        val hour = timePickerFilter.hour
        val minute = timePickerFilter.minute

        val selectedDateTime = LocalDateTime.of(year, month + 1, day, hour, minute)

        val filteredTrips = tripsList.filter {
            val tripDateTime = LocalDateTime.parse(
                it.departureTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            )

            val locationFilterApplied =
                location.isNotBlank() && it.destinationLocation.lowercase().contains(location)
            val timeFilterApplied =
                Duration.between(selectedDateTime, tripDateTime).toMinutes().absoluteValue <= 120

            if (location.isNotBlank() && (hour != 0 || minute != 0)) {
                locationFilterApplied && timeFilterApplied
            } else if (location.isNotBlank()) {
                locationFilterApplied
            } else if (hour != 0 || minute != 0) {
                timeFilterApplied
            } else {
                true
            }
        }

        if (filteredTrips.isEmpty()) {
            Toast.makeText(
                this,
                "No se encontraron viajes con los filtros aplicados, mostrando todos los viajes",
                Toast.LENGTH_SHORT
            ).show()
            tripAdapter = TripAdapter(tripsList)
        } else {
            tripAdapter = TripAdapter(filteredTrips)
        }

        recyclerView.adapter = tripAdapter
        filterContainer.visibility = View.GONE // Cambia esta línea para ocultar el filter_container
    }


}
