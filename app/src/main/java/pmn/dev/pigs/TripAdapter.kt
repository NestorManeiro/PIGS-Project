import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pmn.dev.pigs.R
import pmn.dev.pigs.model.Trip


class TripAdapter(private val tripList: List<Trip>) : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.trip_item, parent, false)
        return TripViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val currentItem = tripList[position]

        holder.driverName.text = "" + currentItem.driverName
        holder.departureLocation.text = "Departure: " + currentItem.departureLocation
        holder.destinationLocation.text = "Destination: " + currentItem.destinationLocation
        holder.departureTime.text = "Time: " + currentItem.departureTime
        holder.departureDate.text = "Date: " + currentItem.departureDate

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
}
