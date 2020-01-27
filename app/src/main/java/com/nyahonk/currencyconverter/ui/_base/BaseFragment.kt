package com.nyahonk.currencyconverter.ui._base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {

    protected abstract val layoutRes : Int

    private val renderDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    override fun onDestroy() {
        renderDisposable.dispose()
        super.onDestroy()
    }

    fun Disposable.connectToRenderDisposable() {
        renderDisposable.add(this)
    }

    fun setTitle(title: String)  {
        getActionBar()?.title = title
    }

    protected fun getActionBar(): ActionBar? = (activity as AppCompatActivity).supportActionBar


}