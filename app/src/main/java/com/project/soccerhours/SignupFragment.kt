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
import kotlinx.android.synthetic.main.signup_fragment.*
import kotlinx.android.synthetic.main.signup_fragment.view.*
import kotlinx.android.synthetic.main.signup_fragment.view.first_edit_text
import java.net.URL


/**
 * Orignial author: Subhayu Chakravarty
 */
class SignupFragment : Fragment() {
    var gApp = GlobalApp()
    var hosturl = gApp.globalUrl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.signup_fragment, container, false)

        view.submit_button.setOnClickListener {
            if (isPasswordValid(spassword_edit_text.text!!)) {
                if (spassword_edit_text.text.toString() == confirmpassword_edit_text.text.toString()) {
                    val zipcode = zip_edit_text.text.toString().toInt()
                    val result = signupUser(
                        first_edit_text.text.toString(), last_edit_text.text.toString(),
                        zipcode, email_edit_text.text.toString(), suser_edit_text.text.toString(), spassword_edit_text.text.toString()
                    )
                    println(result)
                    when (result) {
                        1 -> (activity as NavigationHost).navigateTo(LoginFragment(), false)
                        0 -> println("-------------------ERROR Signing Up")
                        9 -> println("No username provided")
                        else -> {
                            print("result is neither 0,1 nor 9")
                        }
                    }
                } else {
                    confirmpassword_text_input.error = "Entered passwords do not match"
                }
            }
        }

        view.scancel_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(LoginFragment(), false)
        }



        return view
    }


    private fun signupUser(firstName:String, lastName:String,zipCode:Int, email:String, userName:String, password:String): Any {

        val url = URL(hosturl+"appsignup")
        Log.e("URL",url.toString())
        val client = OkHttpClient()

//        Log.i("REQUEST",request.toString())
        val json = """
            {
                "firstName":"${firstName}",
                "lastName":"${lastName}",
                "zipCode":${zipCode},
                "email":"${email}",
                "userName":"${userName}",
                "password":"${password}"
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

        val integer = result.get("result") as Int
        return integer


    }


    // "isPasswordValid"  method goes here
    // Currently checks for 8 characters but we could perform
    // an actual validation with a remote service like the Web version below
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 6
    }


}
