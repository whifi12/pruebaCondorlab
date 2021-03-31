package com.example.domain.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Teams : Serializable {

    @SerializedName("idTeam")
    var idTeam : String = ""

    @SerializedName("strTeam")
    var name : String = ""

    @SerializedName("strStadiumLocation")
    var stadium : String = ""

    @SerializedName("strTeamBadge")
    var badge : String? = null

    @SerializedName("strTeamJersey")
    var jersey : String? = null

    @SerializedName("strWebsite")
    var website : String = ""

    @SerializedName("strFacebook")
    var facebook : String = ""

    @SerializedName("strTwitter")
    var twitter : String = ""

    @SerializedName("strInstagram")
    var instagram : String = ""

    @SerializedName("strDescriptionEN")
    var description : String = ""

    @SerializedName("intFormedYear")
    var year : String = ""

}