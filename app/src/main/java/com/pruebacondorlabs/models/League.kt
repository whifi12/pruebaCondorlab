package com.pruebacondorlabs.models

import com.google.gson.annotations.SerializedName

 class League {

    @SerializedName("teams")
    val teams = ArrayList<Teams>()

}