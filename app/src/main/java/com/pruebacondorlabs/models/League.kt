package com.pruebacondorlabs.models

import com.google.gson.annotations.SerializedName

 class League {

    @SerializedName("teams")
    var teams = ArrayList<Teams>()

}