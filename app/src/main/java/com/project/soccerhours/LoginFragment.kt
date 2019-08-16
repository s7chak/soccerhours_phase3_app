package com.project.soccerhours

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




/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (Build.VERSION.SDK_INT >= 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.next_button.setOnClickListener {
            if (!isPasswordValid(password_edit_text.text!!)) {
                password_text_input.error = getString(R.string.error_password)
            } else {

                password_text_input.error = null

                if (authLoginInfo()) {
                    (activity as NavigationHost).navigateTo(ListingFragment(), false)
                }

            }
        }

        return view
    }


    private fun authLoginInfo():Boolean {

        val username=user_edit_text.text!!.toString()
        val password=password_edit_text.text!!.toString()
        val url = "https://footyhours.appspot.com/applogin/"+username+"/"+password
//        val url = "http://127.0.0.1:8080/applogin/"+username+"/"+password

        Log.i("USERID",username)

        Log.e("URL",url)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Android")
            .build()
        Log.i("REQUEST",request.toString())
        val response = client.newCall(request).execute()
        val jsonString = response?.body()?.string()
        val json = JSONObject(jsonString)
        if (json.get("loggedin") != 0){
            return true
        } else {
            return false
        }


//        client.newCall(request).enqueue(object: Callback {
//
//            override fun onResponse(response: Response?) {
//                val body = response?.body()?.string()
//                println(body)
//                Log.e("RESPONSE",body)
//                val json = JSONObject(body)
//
//                Log.e("JSON",body)
//                if (json.get("loggedin") == 1) {
//                    Log.e("JSON","Logged IN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
//                }
//
//            }
//
//            override fun onFailure(request: Request?, e: IOException?) {
//                Log.e("ERROR","===============================FAILED")
//                println("Failed to get response")
//            }
//        })
    }


    // "isPasswordValid"  method goes here
    // Currently checks for 8 characters but we could perform
    // an actual validation with a remote service like the Web version below
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 3
    }

    private fun isPasswordValidWeb(text: Editable?): Boolean {
        return true
    }

}
