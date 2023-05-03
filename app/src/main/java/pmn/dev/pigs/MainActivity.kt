package pmn.dev.pigs

import TripAdapter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pmn.dev.pigs.model.Trip
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tripAdapter: TripAdapter
    private lateinit var filterButton: Button
    private lateinit var locationFilter: EditText
    private lateinit var timePickerFilter: TimePicker
    private lateinit var applyFilterButton: Button
    private lateinit var filterContainer: ConstraintLayout
    private var tripsList: List<Trip> = ArrayList()

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
            Trip("John Doe", "Universidad", "Centro de la ciudad", "08:00"),
            Trip("Jane Smith", "Estación de autobuses", "Facultad de Ciencias", "09:00"),
            Trip("Andres Rodriguez", "Santa Catalina", "Facultad de Informática", "10:00"),
            Trip("Nestor Maneiro", "Facultad de Ciencias", "Santa Catalina", "11:00"),
            Trip("Juan Garcia", "Facultad de Informática", "Estación de autobuses", "12:00"),
            Trip("Ana Fernandez", "Centro de la ciudad", "Universidad", "13:00"),
            Trip("Pedro Perez", "Facultad de Informática", "Santa Catalina", "14:00"),
            Trip("Maria Lopez", "Estación de autobuses", "Facultad de Ciencias", "15:00"),
            Trip("Jose Gonzalez", "Santa Catalina", "Centro de la ciudad", "16:00"),
            Trip("Laura Sanchez", "Facultad de Ciencias", "Estación de autobuses", "17:00")
        )
    }

    private fun showFilter() {
        if (filterContainer.childCount == 0) {
            val filterView = layoutInflater.inflate(R.layout.filter_layout, filterContainer, false)
            filterContainer.addView(filterView)

            locationFilter = findViewById(R.id.et_location_filter)
            timePickerFilter = findViewById(R.id.time_picker_filter)
            applyFilterButton = findViewById(R.id.btn_apply_filter)

            applyFilterButton.setOnClickListener {
                applyFilter()
            }
        }
        filterContainer.visibility = View.VISIBLE // Agrega esta línea para mostrar el filter_container
    }

    private fun applyFilter() {
        val location = locationFilter.text.toString().toLowerCase().trim()
        val hour = timePickerFilter.hour
        val minute = timePickerFilter.minute

        val filteredTrips = tripsList.filter {
            val tripHour = it.departureTime.split(":")[0].toInt()
            val tripMinute = it.departureTime.split(":")[1].toInt()
            val timeDifference = calculateTimeDifference(hour, minute, tripHour, tripMinute)

            it.destinationLocation.toLowerCase().contains(location) && timeDifference <= 120
        }

        if (filteredTrips.isEmpty()) {
            Toast.makeText(
                this,
                "No se encontraron viajes con los filtros aplicados, mostrando todos los viajes",
                Toast.LENGTH_SHORT
            ).show()
            tripAdapter = TripAdapter(tripsList) // Muestra todos los viajes disponibles
        } else {
            tripAdapter = TripAdapter(filteredTrips) // Muestra los viajes filtrados
        }

        recyclerView.adapter = tripAdapter

        // Eliminar la vista de filtro
        filterContainer.removeAllViews()
    }

    private fun calculateTimeDifference(hour1: Int, minute1: Int, hour2: Int, minute2: Int): Int {
        val time1 = hour1 * 60 + minute1
        val time2 = hour2 * 60 + minute2
        return abs(time1 - time2)
    }


}
