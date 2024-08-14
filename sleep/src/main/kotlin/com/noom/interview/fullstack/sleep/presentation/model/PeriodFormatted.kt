package com.noom.interview.fullstack.sleep.presentation.model

import com.google.gson.annotations.SerializedName

data class PeriodFormatted (
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String
)