package com.example.data.model.response

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("favourite_links") val favouriteLinks: List<LinkData>,
    @SerializedName("overall_url_chart") val overallUrlChart: Map<String, Int>,
    @SerializedName("recent_links") val recentLinks: List<LinkData>,
    @SerializedName("top_links") val topLinks: List<LinkData>
)