package com.project.soccerhours

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import kotlinx.android.synthetic.main.listing_fragment.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.ArrayList

class GameListFragment : Fragment() {
    private val jsoncode = 1
    // Uncomment below if response is hardcoded instead of coming from a file asset

    private var response: String? = null
    private var userlist: ListView? = null
    private var userArrayList: ArrayList<String>? = null
    private var userModelArrayList: ArrayList<User_Model>? = null
    private var customAdapter: CustomAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.gamelist_fragment, container, false)

        userlist = view.userlist
//        userModelArrayList = getInfo(response)  // uncomment this and comment the next line if response is above
        response = loadJSONFromAssets();


        //Here we wan't to use a Custom Adapter that is tied to a custom Data Model
        // Call getInfo to parse the JSON Array and return as a UserModel ArrayList
        userModelArrayList = getInfo(response!!)
        // Create a Custom Adapter that gives us a way to "view" each user in the ArrayList
        customAdapter = CustomAdapter(view.context, userModelArrayList!!)
        // set the custom adapter for the userlist viewing
        userlist!!.adapter = customAdapter


        view.done_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(LogoutFragment(), false)
        })

        return view;
    }



    fun getInfo(response: String): ArrayList<User_Model> {
        val userModelArrayList = ArrayList<User_Model>()
        try {
                val dataArray = JSONArray(response)
                for (i in 0 until dataArray.length()) {
                    val usersModel = User_Model()
                    val dataobj = dataArray.getJSONObject(i)
                    usersModel.setNames(dataobj.getString("name"))
                    usersModel.setEmails(dataobj.getString("email"))
                    userModelArrayList.add(usersModel)
                }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return userModelArrayList
    }

    fun loadJSONFromAssets(): String? {
        var json: String? = null
        try {
            val inputStream = this.context?.assets?.open("users.json")
            val size = inputStream?.available()
            val buffer = ByteArray(size!!)
            inputStream.read(buffer)
            inputStream.close()

            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }

    fun getStrings(response: String): ArrayList<String> {
        val userArrayList = ArrayList<String>()
        try {
            val dataArray = JSONArray(response)
            for (i in 0 until dataArray.length()) {
                val dataobj = dataArray.getJSONObject(i)
                userArrayList.add(dataobj.toString())
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return userArrayList
    }


}