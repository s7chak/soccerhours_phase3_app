package com.project.soccerhours

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList

/**
 * Orignial author: Parsania Hardik on 03-Jan-17.
 * Modified by Ramesh Yerraballi on 8/12/19
 */
@Suppress("NAME_SHADOWING")
class CustomAdapter(private val context: Context, private val usersModelArrayList: ArrayList<Usergames_model>) :
    BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return usersModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return usersModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

  /*
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.user, null, true)

            holder.name = convertView!!.findViewById(R.id.name) as TextView
            holder.email = convertView.findViewById(R.id.email) as TextView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.name!!.text = "Name: " + usersModelArrayList[position].getNames()
        holder.email!!.text = "Email: " + usersModelArrayList[position].getEmails()

        return convertView
    }


    private inner class ViewHolder {

        var name: TextView? = null
        var email: TextView? = null
    }
*/

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.usergames, null, true)

            holder.eventname = convertView!!.findViewById(R.id.eventname) as TextView
            holder.eventdate = convertView.findViewById(R.id.eventdate) as TextView
            holder.venuename = convertView.findViewById(R.id.venuename) as TextView
            holder.starttime = convertView.findViewById(R.id.starttime) as TextView
            holder.endtime = convertView.findViewById(R.id.endtime) as TextView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.eventname!!.text = "Event Name: " + usersModelArrayList[position].getEventnames()
        holder.eventdate!!.text = "Date: " + usersModelArrayList[position].getEventdates()
        holder.venuename!!.text = "Venue Name: " + usersModelArrayList[position].getVenuenames()
        holder.starttime!!.text = "Start Time: " + usersModelArrayList[position].getStarttimes()
        holder.endtime!!.text = "End Time: " + usersModelArrayList[position].getEndtimes()

        return convertView
    }


    private inner class ViewHolder {

        var eventname: TextView? = null
        var eventdate: TextView? = null
        var venuename: TextView? = null
        var starttime: TextView? = null
        var endtime: TextView? = null
    }



}