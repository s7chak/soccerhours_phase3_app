package com.project.soccerhours

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import org.json.JSONObject
import com.squareup.okhttp.*
import java.io.IOException
import android.os.StrictMode
import android.os.Build
import android.provider.Settings
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.event.*
import kotlinx.android.synthetic.main.signup_fragment.*
import kotlinx.android.synthetic.main.signup_fragment.view.*
import kotlinx.android.synthetic.main.signup_fragment.view.first_edit_text
import kotlinx.android.synthetic.main.startgame_fragment.*
import org.json.JSONArray
import org.json.JSONException
import java.net.URL
import java.util.ArrayList


/**
 * Fragment representing the Sign Up screen.
 */
class StartGameFragment : Fragment() {
    var gApp = GlobalApp()
    var hosturl = gApp.globalUrl


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.startgame_fragment, container, false)
        var venuelist:List<Venue_Model> = getVenues()
        var venuenamelist = mutableListOf<String>()
        venuelist.forEach() {
            venuenamelist.add(it.venueName!!)
        }


        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, venuenamelist)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        val venue_spinner: Spinner = view.findViewById(R.id.venue_spinner)
        venue_spinner.adapter = adapter

        venue_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = adapter.getItem(position)
            }
        }

        view.submit_button.setOnClickListener {
            val venue = venue_spinner.getSelectedItem().toString()
            var venueidnum = getVenueId(venue,venuelist)
            println("=============+VENU========="+venue)
            val result = startGame(venueidnum,
                ename_edit_text.text.toString(), edesc_edit_text.text.toString(), edate_edit_text.text.toString(),
                estart_edit_text.text.toString().toInt(), eend_edit_text.text.toString().toInt(),
                ecapacity_edit_text.text.toString().toInt()
            )
            val value = result.get("result").toString().toInt()
            when (value) {
                1 -> showAlert(result.get("message").toString())
                0 -> println("ERROR on Server side")
                9 -> println("Failure starting event")
                else -> {
                    print("result is neither 0,1 nor 9")
                }
            }
        }

        view.scancel_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(HomeFragment(), false)
        }



        return view
    }

    private fun showAlert(message:String) {
        val builder = AlertDialog.Builder(view!!.context)

        // Set the alert dialog title
        builder.setTitle("Event Started")

        // Display a message on alert dialog
        builder.setMessage(message)


        // Display a neutral button on alert dialog
        builder.setNeutralButton("OK"){_,_ ->
            Toast.makeText(context,"Thank you!",Toast.LENGTH_SHORT).show()
            (activity as NavigationHost).navigateTo(HomeFragment(), false)
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun getVenueId(venue: String, venuelist: List<Venue_Model>):Int {
        var venueId:Int = 0
        venuelist.forEach() {
            if (venue == it.venueName) {
                venueId= it!!.venueId!!
            }
        }
        return venueId
    }

    private fun startGame(venueId:Int, eventName:String, eventDesc:String, eventDate:String, start:Int, end:Int, capacity:Int): JSONObject {

        val url = URL(hosturl+"appstartevent")
        Log.e("URL",url.toString())
        val client = OkHttpClient()
        val username=GlobalVars.userName

        val json = """
            {
                "venue":${venueId},
                "username":"${username}",
                "eventname":"${eventName}",
                "eventdesc":"${eventDesc}",
                "eventdate":"${eventDate}",
                "starttime":${start},
                "endtime":${end},
                "eventcapacity":${capacity}
            }
            """.trimIndent()

        println(json)
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        val response = client.newCall(request).execute()
        val jsonString = response?.body()?.string()
        println(jsonString)
        val result = JSONObject(jsonString)
        Log.e("RESULT", result.toString())

        return result


    }


    private fun getVenues(): List<Venue_Model> {
        val url = URL(hosturl+"appgetvenues")


        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Android")
            .build()
        Log.i("REQUEST",request.toString())
        val response = client.newCall(request).execute()
        val jsonString = response.body().string()
        println(jsonString)
        val venueList = ArrayList<Venue_Model>()
        try {

            val dataArray = JSONArray(jsonString)
            for (i in 0 until dataArray.length()) {
                val venue = dataArray.getJSONObject(i)
                val venueModel = Venue_Model()
                venueModel.venueId = (venue.getInt("venueid"))
                venueModel.venueName = (venue.getString("venuename"))
                venueList.add(venueModel)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return venueList

    }


    // "isPasswordValid"  method goes here
    // Currently checks for 8 characters but we could perform
    // an actual validation with a remote service like the Web version below
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 6
    }


}
