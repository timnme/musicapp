package com.sample.musicapp.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

/**
 * Base class for fragments to implement MVP pattern
 */
abstract class MvpFragment<V : MvpView, P : MvpPresenter<V>> : Fragment() {
    protected abstract val presenter: P

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            presenter.onViewAttached(this as V)
        } catch (e: ClassCastException) {
            throw ClassCastException("$this must implement ${MvpView::class.java.simpleName}")
        }
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetached()
    }
}