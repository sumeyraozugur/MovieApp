package com.sumeyra.data.model

import com.google.gson.annotations.SerializedName

data class DatesDto(
    @SerializedName("maximum") val maximum : String ?= null,
    @SerializedName("minimum") val minimum:String ?= null,
)
