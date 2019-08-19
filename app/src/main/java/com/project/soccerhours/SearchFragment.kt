package com.project.soccerhours

import android.content.SharedPreferences
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
import kotlinx.android.synthetic.main.home_fragment.view.*
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*
import kotlinx.android.synthetic.main.search_fragment.view.szip_edit_text
import kotlinx.android.synthetic.main.signup_fragment.*
import org.json.JSONArray
import org.json.JSONException
import java.net.URL
import java.util.ArrayList


/**
 * Fragment representing the login screen.
 */
class SearchFragment : Fragment() {
    val gApp = GlobalApp()
    var hosturl = gApp.globalUrl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (Build.VERSION.SDK_INT >= 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        // Set an error if the password is less than 8 characters.

        view.ssearch_button.setOnClickListener {
            // Validate Zip Code
            (activity as NavigationHost).navigateTo(EventListFragment.newInstance(szip_edit_text.text.toString().toInt()), true)

        }

        return view
    }




}
