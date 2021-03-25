package com.example.domain.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Teams : Serializable {

    @SerializedName("idTeam")
    var idTeam : String = ""

    @SerializedName("strTeam")
    var name : String = ""

    @SerializedName("strStadiumLocation")
    val stadium : String = ""

    @SerializedName("strTeamBadge")
    var badge : String = ""

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
    var description : String = ""

    @SerializedName("intFormedYear")
    var year : String = ""

}