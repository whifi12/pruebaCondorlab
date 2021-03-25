package com.example.domain.model.response

import com.google.gson.annotations.SerializedName

class Events {

    @SerializedName("results")
    var events = ArrayList<Match>()
}