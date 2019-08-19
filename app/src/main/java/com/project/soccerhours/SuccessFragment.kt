package com.project.soccerhours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.success_fragment.view.*


class SuccessFragment : Fragment() {
    companion object {
        fun newInstance(message: String): SuccessFragment {
            val fragment = SuccessFragment()
            val args = Bundle()
            args.putString("message", message)
            fragment.setArguments(args)

            return fragment
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.success_fragment, container, false)
        view.message_field.setText(arguments!!.getString("message"))

        view.gohome_button.setOnClickListener({
            (activity as NavigationHost).navigateTo(HomeFragment(), false)
        })


        return view;
    }



}
