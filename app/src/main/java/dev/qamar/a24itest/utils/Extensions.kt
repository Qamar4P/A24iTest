package dev.qamar.a24itest.utils

import android.arch.lifecycle.MutableLiveData

/**
 * Created by Qamar4P on 22/4/2019.
 * Islamabad, Pakistan.
 */

operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: List<T>) {
    val value = this.value ?: arrayListOf()
    value.addAll(values)
    this.value = value
}