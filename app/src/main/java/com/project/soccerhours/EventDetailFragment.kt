package com.project.soccerhours

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request

import org.json.JSONArray
import org.json.JSONException
import java.net.URL
import java.util.ArrayList
import android.R
import android.widget.*
import kotlinx.android.synthetic.main.event.view.*
import kotlinx.android.synthetic.main.eventdetail_fragment.view.*
import kotlinx.android.synthetic.main.eventdetail_fragment.view.endtime
import kotlinx.android.synthetic.main.eventdetail_fragment.view.eventdate
import kotlinx.android.synthetic.main.eventdetail_fragment.view.eventname
import kotlinx.android.synthetic.main.eventdetail_fragment.view.starttime
import kotlinx.android.synthetic.main.eventdetail_fragment.view.venue
import kotlinx.android.synthetic.main.eventdetail_fragment.view.spots
import org.json.JSONObject


class EventDetailFragment : Fragment() {
    companion object {
        fun newInstance(eventDetail: Event_Model): EventDetailFragment {
            val fragment = EventDetailFragment()
            val args = Bundle()
            args.putString("eventId", eventDetail.eventId)
            args.putString("eventName", eventDetail.eventName)
            args.putString("eventDate", eventDetail.eventDate)
            args.putString("venue", eventDetail.venue)
            args.putString("startTime", eventDetail.startTime)
            args.putString("endTime", eventDetail.endTime)
            args.putString("spots", eventDetail.spots)

            fragment.setArguments(args)

            return fragment
        }
    }

    var gApp = GlobalApp()
    var hosturl = gApp.globalUrl
    private var eventModelArrayList: ArrayList<Event_Model>? = null
    private var customAdapter: CustomEventAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(com.project.soccerhours.R.layout.eventdetail_fragment, container, false)

        view.eventname.setText("Event Name:"+arguments!!.getString("eventName"))
        view.eventdate.setText("Date:"+arguments!!.getString("eventDate"))
        view.venue.setText("Venue:"+arguments!!.getString("venue"))
        view.starttime.setText("From:"+arguments!!.getString("startTime")+":00")
        view.endtime.setText("To:"+arguments!!.getString("endTime")+":00")
        view.spots.setText("Spots Left:"+arguments!!.getString("spots"))
        view.spots.setFadingEdgeLength(2)

        view.join_button.setOnClickListener({
            //Join event
            val message = joinEvent(arguments!!.getString("eventId")!!.toInt())
            (activity as NavigationHost).navigateTo(SuccessFragment.newInstance(message), false)
        })

        return view;
    }



    private fun joinEvent(eventId:Int):String {

        val url = URL(hosturl+"appjoinevent/"+eventId+"/"+GlobalVars.userId)
        println("------------------------->>>>>"+url.toString())

        Log.i("EVENTID",eventId.toString())

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

        val result = JSONObject(jsonString)
        if(result.getString("message") != null) {
            return result.getString("message")
        }

        return "Failure to join event"

    }

}
