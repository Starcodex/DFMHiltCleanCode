package com.mfcc.hilt.core.extensions

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mfcc.hilt.core.R
import com.mfcc.hilt.core.base.BaseActivity
import com.mfcc.hilt.core.base.BaseBindingFragment
import com.mfcc.hilt.core.base.BaseBindingFragmentActivity


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

fun Fragment.close() = fragmentManager?.popBackStack()

val Fragment.viewContainer:
        View get() = (activity as BaseActivity)
        .findViewById(R.id.fragmentContainer )

val Fragment.appContext: Context get() = activity?.applicationContext!!

val Fragment.progress :
View get() = (activity as BaseActivity)
.findViewById(R.id.progress )