package dev.qamar.a24itest.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class Movie {
    var id: Int? = null
    var title: String? = null
    var poster_path: String? = null
    var overview: String? = null
    var video: Boolean? = null
}