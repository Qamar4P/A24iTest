package dev.qamar.a24itest.utils

import android.util.Base64

import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
object Utils {
    fun decode(k: String): String {
        return String(Base64.decode(AppConst.K, Base64.DEFAULT), Charset.forName("UTF8"))
    }
}
