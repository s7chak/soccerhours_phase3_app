package com.project.soccerhours

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Orignial author: Subhayu Chakravarty
 */
object GlobalVars {

    var userId:Int? = 0
    var userName:String? = ""



    fun setUserId(userid:Int) {
        this.userId = userid
    }

    fun setUsername(username:String) {
        this.userName = username
    }

}