package com.project.soccerhours

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
//import kotlinx.android.synthetic.main.listing_fragment.view.done_button
import org.json.JSONArray
import org.json.JSONException
import java.net.URL
import java.util.ArrayList

import android.widget.*
import kotlinx.android.synthetic.main.eventlist_fragment.view.*
import android.widget.AdapterView.OnItemClickListener
import kotlinx.android.synthetic.main.search_fragment.*


/**
 * Orignial author: Subhayu Chakravarty
 */
class EventListFragment : Fragment() {
    companion object {
        fun newInstance(zipcode: Int): EventListFragment {
            val fragment = EventListFragment()
            val args = Bundle()
            args.putInt("zipcode", zipcode)
            fragment.setArguments(args)

            return fragment
        }
    }

    var gApp = GlobalApp()
    var hosturl = gApp.globalUrl
    private var eventlistview: ListView? = null
    private var eventModelArrayList: ArrayList<Event_Model>? = null
    private var customAdapter: CustomEventAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(com.project.soccerhours.R.layout.eventlist_fragment, container, false)
        val eventList = zipSearchEvents(zipCode = arguments!!.getInt("zipcode"))
        eventlistview = view.eventlist

        eventModelArrayList = eventList

        // Create a Custom Adapter that gives us a way to "view" each user in the ArrayList
        val customAdapter = CustomEventAdapter(view.context, eventModelArrayList!!)
        // set the custom adapter for the userlist viewing
        eventlistview!!.adapter = customAdapter


        view.eventlist.setOnItemClickListener { parent, view, position, id ->
            val element:Event_Model= customAdapter.getItem(position) // The item that was clicked
            println("Item Clicked:::::::::::::position-"+position+" ID-"+element.eventId)

            (activity as NavigationHost).navigateTo(EventDetailFragment.newInstance(element), true)
        }

        return view;
    }



    fun onItemClick(l: AdapterView<*>, v: View, position: Int, eventid: Long) {
        Log.i("EventListView", "You clicked Item: $eventid at position:$position")
        (activity as NavigationHost).navigateTo(EventDetailFragment(), false)

    }


    private fun zipSearchEvents(zipCode:Int):ArrayList<Event_Model> {

        val url = URL(hosturl+"appzipsearch/"+zipCode)


        Log.i("ZIPCODE",zipCode.toString())

        Log.e("URL",url.toString())
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Android")
            .build()
        Log.i("REQUEST",request.toString())
        val response = client.newCall(request).execute()
        val jsonString = response.body().string()
        println(jsonString)
        return getModels(jsonString)

    }

    fun getModels(response: String): ArrayList<Event_Model> {
        val eventModelList = ArrayList<Event_Model>()
        if (response!=null) {
            try {
                val dataArray = JSONArray(response)
                for (i in 0 until dataArray.length()) {
                    val event = dataArray.getJSONObject(i)
                    val eventModel = Event_Model()
                    eventModel.eventId = (event.getString("eventid"))
                    eventModel.eventName = (event.getString("eventname"))
                    eventModel.eventDate = (event.getString("eventdate"))
                    eventModel.venue = (event.getString("venue"))
                    eventModel.startTime = (event.getString("starttime"))
                    eventModel.endTime = (event.getString("endtime"))
                    eventModel.spots = (event.getString("spots"))
                    eventModelList.add(eventModel)

                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } else {
            var event = Event_Model()
            var list = arrayListOf<Event_Model>(event)
            return list
        }
        return eventModelList
    }

}
