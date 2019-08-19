package com.project.soccerhours

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object GlobalVars {

    var userId:Int? = 0


    fun setUserId(userid:Int) {
        this.userId = userid
    }

}