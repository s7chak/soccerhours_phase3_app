package com.project.soccerhours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.logout_fragment.view.*

/**
 * Fragment representing the login screen for Shrine.
 */
class LogoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.logout_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.logout_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(LoginFragment(), false)
        })

        return view
    }


}
