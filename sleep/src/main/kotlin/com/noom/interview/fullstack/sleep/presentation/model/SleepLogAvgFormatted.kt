package com.noom.interview.fullstack.sleep.presentation.model

import com.google.gson.annotations.SerializedName

data class SleepLogAvgFormatted (
    @SerializedName("avgTime")
    val avgTime: String,
    @SerializedName("avgInterval")
    val avgInterval: PeriodFormatted,
    @SerializedName("period")
    val period: PeriodFormatted,
    @SerializedName("ratingsCount")
    val ratingsCount: Map<String, Int>,
)