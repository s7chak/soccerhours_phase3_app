package com.project.soccerhours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.home_fragment.view.*
import java.util.ArrayList

class HomeFragment : Fragment() {
    private val jsoncode = 1
    // Uncomment below if response is hardcoded instead of coming from a file asset

    private var response: String? = null
    private var userlist: ListView? = null
    private var userArrayList: ArrayList<String>? = null
    private var userModelArrayList: ArrayList<User_Model>? = null
    private var customAdapter: CustomEventAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.home_fragment, container, false)


        view.search_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(SearchFragment(), true)
        })

        view.start_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(StartGameFragment(), true)
        })

        view.joined_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(GameListFragment(), true)
        })

        return view;
    }



}
