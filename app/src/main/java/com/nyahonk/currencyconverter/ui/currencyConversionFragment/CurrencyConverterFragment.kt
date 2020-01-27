package com.nyahonk.currencyconverter.ui.currencyConversionFragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.nyahonk.currencyconverter.CurrenciesMap
import com.nyahonk.currencyconverter.R
import com.nyahonk.currencyconverter.data.persistance.DBUpdateCode
import com.nyahonk.currencyconverter.di.appComponent
import com.nyahonk.currencyconverter.presentation._base.viewModelProvider
import com.nyahonk.currencyconverter.ui._base.BaseFragment
import com.nyahonk.currencyconverter.presentation.currencyConverter.CurrencyConverterViewModel
import kotlinx.android.synthetic.main.currency_converter_layout.*

class CurrencyConverterFragment : BaseFragment() {

    override val layoutRes = R.layout.currency_converter_layout

    private val viewModel: CurrencyConverterViewModel by viewModelProvider {
        appComponent().currencyConverterViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        performActions()
        performRender()
        setViews()
    }

    private fun performRender() {
        viewModel.apply {
            targetValue
                .subscribe { editTextTargetValue.setText(it) }
                .connectToRenderDisposable()

            error.subscribe {
                handleError(it)
            }
                .connectToRenderDisposable()
        }
    }

    private fun setViews() {

        setTitle(getString(R.string.currency_converter_title))

        context?.let {
            currencySpinnerTarget.adapter =
                ArrayAdapter(
                    it,
                    R.layout.spinner_item,
                    CurrenciesMap.values()
                )

            currencySpinnerSource.adapter =
                ArrayAdapter(
                    it,
                    R.layout.spinner_item,
                    CurrenciesMap.values()
                )
        }
    }

    private fun performActions() {
        calculateButton.setOnClickListener {
            if (editTextSourceValue.text.toString().isEmpty()) editTextSourceValue.setText("1")
            viewModel.onCalculateButtonClick(
                currencySpinnerSource.selectedItem as CurrenciesMap,
                currencySpinnerTarget.selectedItem as CurrenciesMap,
                editTextSourceValue.text.toString()
            )
        }
    }

    private fun handleError(errorCode: DBUpdateCode) {
        when (errorCode) {
            DBUpdateCode.UPDATE_FAILED -> showBanner(getString(R.string.error_text_update_failed))
            DBUpdateCode.DATABASE_EMPTY -> showBanner(getString(R.string.error_text_database_empty))
            DBUpdateCode.NO_INTERNET -> showBanner(getString(R.string.error_text_no_internet))
            DBUpdateCode.UPDATE_STARTED -> showBanner(getString(R.string.error_text_update_in_progress))
            DBUpdateCode.SUCCESS -> hideBanner()
        }
    }

    private fun showBanner(message: String) {
        notifier_banner.visibility = View.VISIBLE
        notifier_banner_text.text = message
    }

    private fun hideBanner() {
        if (notifier_banner.visibility != View.GONE) notifier_banner.visibility = View.GONE
    }
}