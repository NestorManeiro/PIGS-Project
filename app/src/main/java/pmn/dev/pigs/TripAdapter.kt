import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pmn.dev.pigs.R
import pmn.dev.pigs.model.Trip

class TripAdapter(private val context: Context, private val tripList: List<Trip>) : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {
    companion object {
        val tripArray = ArrayList<Trip>()
    }

    fun getTripArray(): ArrayList<Trip> {
        return tripArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.trip_item, parent, false)
        return TripViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val currentItem = tripList[position]

        holder.driverName.text = "" + currentItem.driverName
        holder.departureLocation.text = "Departure: " + currentItem.departureLocation
        holder.destinationLocation.text = "Destination: " + currentItem.destinationLocation
        holder.departureTime.text = "Time: " + currentItem.departureTime
        holder.departureDate.text = "Date: " + currentItem.departureDate
        holder.itemView.setOnClickListener {
            // Llama al método showPopup() para mostrar el popup cuando se hace clic en el elemento
            val actualTrip = Trip(
                driverName = currentItem.driverName,
                departureLocation = currentItem.departureLocation,
                destinationLocation = currentItem.destinationLocation,
                departureTime = currentItem.departureTime,
                departureDate = currentItem.departureDate
            )
            showPopup(actualTrip)
        }
    }

    override fun getItemCount(): Int {
        return tripList.size
    }

    class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val driverName: TextView = itemView.findViewById(R.id.tv_driver_name)
        val departureLocation: TextView = itemView.findViewById(R.id.tv_departure_location)
        val destinationLocation: TextView = itemView.findViewById(R.id.tv_destination_location)
        val departureTime: TextView = itemView.findViewById(R.id.tv_departure_time)
        val departureDate: TextView = itemView.findViewById(R.id.tv_departure_date)
    }
    private var tripAddedListener: OnTripAddedListener? = null
    private fun showPopup(newTrip: Trip) {
        val inflater = LayoutInflater.from(context)
        val popupView = inflater.inflate(R.layout.popup_layout, null)

        // Configura los elementos dentro del popup
        val imageView = popupView.findViewById<ImageView>(R.id.popup_image)
        val button = popupView.findViewById<Button>(R.id.popup_button)

        // Establece la imagen y el botón según sea necesario

        // Crea el PopupWindow
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, 1000, true)


        // Establece un fondo para el popup
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Configura el comportamiento del botón en el popup
        button.setOnClickListener {
            tripArray.add(newTrip)
            notifyDataSetChanged()
            popupWindow.dismiss()
        }


        // Muestra el popup en una posición relativa al elemento clicado
        popupWindow.showAsDropDown(button)
    }


    interface OnTripAddedListener {
        fun onTripAdded(trip: Trip)
    }
    fun setOnTripAddedListener(listener: OnTripAddedListener) {
        tripAddedListener = listener
    }

}
