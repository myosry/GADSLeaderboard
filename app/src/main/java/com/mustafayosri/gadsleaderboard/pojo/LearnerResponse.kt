package com.mustafayosri.gadsleaderboard.pojo

import com.google.gson.annotations.SerializedName

data class LearnerResponse(
    val name: String,
    @SerializedName(value = "number", alternate = ["hours","score"])
    val number: Int,
    val country: String,
    val badgeUrl: String
)