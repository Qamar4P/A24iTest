package dev.qamar.a24itest.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class MovieRef {
    var id: Int? = null
    var adult: Boolean? = null
}