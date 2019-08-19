package com.project.soccerhours


/**
 * Orignial author: Abhishek Kardak
 */
class Usergames_model {

    var eventname: String? = null
    var eventdate: String? = null
    var venuename: String? = null
    var starttime: String? = null
    var endtime: String? = null

    fun getEventnames(): String {
        return eventname.toString()
    }

    fun setEventnames(name: String) {
        this.eventname = name
    }

    fun getEventdates(): String {
        return eventdate.toString()
    }

    fun setEventdates(name: String) {
        this.eventdate = name
    }

    fun getVenuenames(): String {
        return venuename.toString()
    }

    fun setVenueNames(name: String) {
        this.venuename = name
    }

    fun getStarttimes(): String {
        return starttime.toString()
    }

    fun setStattimes(name: String) {
        this.starttime = name
    }

    fun getEndtimes(): String {
        return endtime.toString()
    }

    fun setEndtimes(name: String) {
        this.endtime = name
    }
}