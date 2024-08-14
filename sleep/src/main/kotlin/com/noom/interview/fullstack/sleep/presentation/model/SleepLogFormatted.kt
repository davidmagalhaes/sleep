package com.noom.interview.fullstack.sleep.presentation.model

import com.google.gson.annotations.SerializedName

data class SleepLogFormatted (
    @SerializedName("date")
    val date: String,
    @SerializedName("interval")
    val interval: PeriodFormatted,
    @SerializedName("duration")
    val totalTime: String,
    @SerializedName("rating")
    val rating: String
)