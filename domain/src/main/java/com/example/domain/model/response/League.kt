package com.example.domain.model.response

import com.google.gson.annotations.SerializedName

 class League {

    @SerializedName("teams")
    var teams = ArrayList<Teams>()

}