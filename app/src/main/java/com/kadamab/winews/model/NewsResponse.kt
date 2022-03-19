package com.kadamab.winews.model

import com.google.gson.annotations.SerializedName
/**
 * Describes the response from news service API.
 */
data class NewsResponse(
    @SerializedName("title")
    val title: String,

    @SerializedName("rows")
    val rows: List<Rows>
)

/**
 * Describes the sub data
 */
data class Rows(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("imageHref")
    val imageHref: String,

    )