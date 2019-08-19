package com.project.soccerhours

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList

/**
 * Orignial author: Subhayu Chakravarty
 */
class CustomEventAdapter(private val context: Context, private val eventModelArrayList: ArrayList<Event_Model>) :
    BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return eventModelArrayList.size
    }

    override fun getItem(position: Int): Event_Model {
        return eventModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.event, null, true)

            holder.eventid = convertView!!.findViewById(R.id.eventid) as TextView
            holder.eventname = convertView!!.findViewById(R.id.eventname) as TextView
            holder.eventdate = convertView!!.findViewById(R.id.eventdate) as TextView
            holder.venue = convertView!!.findViewById(R.id.venue) as TextView
            holder.starttime = convertView!!.findViewById(R.id.starttime) as TextView
            holder.endtime = convertView!!.findViewById(R.id.endtime) as TextView
            holder.spots = convertView!!.findViewById(R.id.spots) as TextView


            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.eventid!!.text = "Event ID: " + eventModelArrayList[position].eventId
        holder.eventname!!.text = "Event Name: " + eventModelArrayList[position].eventName
        holder.venue!!.text = "Venue: " + eventModelArrayList[position].venue
        holder.eventdate!!.text = "Date: " + eventModelArrayList[position].eventDate
        holder.starttime!!.text = "Start: " + eventModelArrayList[position].startTime
        holder.endtime!!.text = "End: " + eventModelArrayList[position].endTime
        holder.spots!!.text = "Spots Left: " + eventModelArrayList[position].spots

        return convertView
    }

    private inner class ViewHolder {

        var venue: TextView? = null
        var eventid: TextView? = null
        var eventname: TextView? = null
        var eventdate: TextView? = null
        var starttime: TextView? = null
        var endtime: TextView? = null
        var spots: TextView? = null
    }

}