package com.pruebacondorlabs.models

import com.google.gson.annotations.SerializedName

class Events {

    @SerializedName("results")
    var events = ArrayList<Match>()
}