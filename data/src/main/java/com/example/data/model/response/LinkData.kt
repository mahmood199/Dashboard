package com.example.data.model.response

import com.google.gson.annotations.SerializedName

data class LinkData(
    @SerializedName("app") val app: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("domain_id") val domainId: String,
    @SerializedName("is_favourite") val isFavourite: Boolean,
    @SerializedName("original_image") val originalImage: String,
    @SerializedName("smart_link") val smartLink: String,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("times_ago") val timesAgo: String,
    @SerializedName("title") val title: String,
    @SerializedName("total_clicks") val totalClicks: Int,
    @SerializedName("url_id") val urlId: Int,
    @SerializedName("url_prefix") val urlPrefix: String,
    @SerializedName("url_suffix") val urlSuffix: String,
    @SerializedName("web_link") val webLink: String
) {
    companion object {
        fun default(): LinkData {
            return LinkData(
                app = "Instagram",
                createdAt = "1234259632",
                domainId = "Amazon.com",
                isFavourite = true,
                originalImage = "https://i.imgur.com/CzXTtJV.jpg",
                smartLink = "https://samplelink.oia.bio/ashd...",
                thumbnail = "https://i.imgur.com/CzXTtJV.jpg",
                timesAgo = "1 hour ago",
                title = "Sample Title",
                totalClicks = 2323,
                urlId = 1234565,
                urlPrefix = "Some prefix",
                urlSuffix = "Some suffix",
                webLink = "https://github.com/microsoft/onnxjs-demo/blob/master/src/data/sample-image-urls.ts"
            )
        }
    }
}