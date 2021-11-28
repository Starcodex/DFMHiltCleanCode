package com.mfcc.hilt.core.base

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mfcc.hilt.core.R
import com.mfcc.hilt.core.extensions.appContext
import com.mfcc.hilt.core.extensions.progress
import com.mfcc.hilt.core.extensions.viewContainer

abstract class BaseFragment : Fragment() {

    abstract val layoutResource: Int

    open fun onBackPressed() {}

    protected fun showProgress() = progressStatus(View.VISIBLE)

    protected fun hideProgress() = progressStatus(View.GONE)

    // TODO replace with injected interface
    private fun progressStatus(viewStatus: Int) = with(activity) { if (this is BaseBindingFragmentActivity) progress.visibility = viewStatus }

    internal fun notify(@StringRes message: Int) = Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    protected fun notifyWithAction(
        @StringRes message: Int,
        @StringRes actionText: Int,
        action: () -> Any
    ) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { _ -> action.invoke() }
        snackBar.setActionTextColor(ContextCompat.getColor(appContext, R.color.colorTextPrimary))
        snackBar.show()
    }

}