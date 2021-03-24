package com.example.domain.model

import com.google.gson.annotations.SerializedName

class Events {

    @SerializedName("results")
    var events = ArrayList<Match>()
}