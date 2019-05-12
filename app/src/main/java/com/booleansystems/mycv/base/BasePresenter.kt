package com.booleansystems.mycv.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel

/**

Created by oscar on 10/05/19
operez@na-at.com.mx
 */
/**
 * Presenter extending ViewModel might seem weird, but let’s not confuse it with ViewModel
 * from MVVM - ViewModel in AC is “designed to store and manage UI-related data in a lifecycle conscious way”,
 * which is exactly what we want to achieve
 *
 * By extending ViewModel the Presenter instance will survive configuration changes
 *
 * By passing and observing viewLifecycle every Presenter will automatically set it’s view reference to null before the view gets destroyed
 */
abstract class BasePresenter<View : IBaseView> : ViewModel(), LifecycleObserver {

    private var view: View? = null
    private var viewLifecycle: Lifecycle? = null


    fun attachView(view: View, viewLifecycle: Lifecycle) {
        this.view = view
        this.viewLifecycle = viewLifecycle

        viewLifecycle.addObserver(this)
    }

    protected fun view(): View? {
        return view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        view = null
        viewLifecycle = null
    }
}