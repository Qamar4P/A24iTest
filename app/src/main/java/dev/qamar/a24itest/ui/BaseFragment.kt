package dev.qamar.a24itest.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
abstract class BaseFragment : Fragment() {

    abstract val layoutId : Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }
}
