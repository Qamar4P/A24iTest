package dev.qamar.a24itest.data.model

import com.squareup.moshi.JsonClass

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
@JsonClass(generateAdapter = true)
class ApiResponse<T> {
    var page: Int? = null
    var total_pages: Int? = null
    var results: T? = null
        internal set
}
