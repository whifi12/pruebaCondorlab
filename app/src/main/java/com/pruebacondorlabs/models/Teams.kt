package com.pruebacondorlabs.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Teams : Serializable {

    @SerializedName("idTeam")
    val idTeam : String = ""

    @SerializedName("strTeam")
    val name : String = ""

    @SerializedName("strStadiumLocation")
    val stadium : String = ""

    @SerializedName("strTeamBadge")
    val badge : String = ""

    @SerializedName("strTeamJersey")
    val jersey : String = ""

    @SerializedName("strWebsite")
    val website : String = ""

    @SerializedName("strFacebook")
    val facebook : String = ""

    @SerializedName("strTwitter")
    val twitter : String = ""

    @SerializedName("strInstagram")
    val instagram : String = ""

    @SerializedName("strDescriptionEN")
    val description : String = ""

    @SerializedName("intFormedYear")
    val year : String = ""

}