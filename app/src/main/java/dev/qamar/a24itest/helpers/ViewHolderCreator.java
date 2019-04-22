package dev.qamar.a24itest.helpers;

import android.view.ViewGroup;

/**
 * @author Qamar4P
 */
public interface ViewHolderCreator<VH>{
        VH create(ViewGroup parent);
    }