package com.abeltarazona.poketinder.presentation.ui.fragments.intro

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.Navigation
import com.abeltarazona.poketinder.databinding.FragmentWebIntroBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseFragment

class WebIntroFragment : BaseFragment<FragmentWebIntroBinding>(FragmentWebIntroBinding::inflate) {

    var webview: WebView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://pokemongolive.com/es/"

        webview = binding.wvMain

        webview!!.settings.javaScriptEnabled = true

        webview!!.webViewClient = PokemonWebClient()

        webview!!.loadUrl(url)

        binding.btnClose.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

    }

    inner class PokemonWebClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            webview!!.loadUrl("javascript:(function() { " +
                    "document.getElementsByClassName('navbar top')[0].style.display='none'; })()");
        }
    }
}