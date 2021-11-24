package com.mfcc.hilt.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.BaseDeepLinkDelegate
import com.airbnb.deeplinkdispatch.BaseRegistry

private val deepLinkRegistryClassMap = mutableMapOf<String, Class<*>>()

class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val delegate = BaseDeepLinkDelegate(
            listOfNotNull(
                loadDeepLinkRegistry("com.mfcc.hilt.splash.deeplink.SplashModuleRegistry"),
                loadDeepLinkRegistry("com.mfcc.hilt.feature_movies.deeplink.MoviesModuleRegistry")
            )
        )
        delegate.dispatchFrom(this)

        finish()
    }

    private fun loadDeepLinkRegistry(name: String): BaseRegistry? {
        return try {
            val clazz = deepLinkRegistryClassMap[name] ?: Class.forName(name).also {
                deepLinkRegistryClassMap[name] = it
            }
            clazz.newInstance() as? BaseRegistry
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}