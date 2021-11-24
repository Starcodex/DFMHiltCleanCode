package com.mfcc.hilt.core.di




/*
@InstallIn(ViewModelComponent::class)
@Module
interface ActivityViewModelModule {

    @Provides
    fun provideSavedStateViewModelFactory(
        application: Application,
        activity: Activity,
        viewModelFactories: @JvmSuppressWildcards Map<String, Provider<ViewModelAssistedFactory<out ViewModel>>>,
    ): DFMSavedStateViewModelFactory {
        val owner = activity as SavedStateRegistryOwner
        val defaultArgs = activity.intent?.extras
        val delegate = SavedStateViewModelFactory(application, owner, defaultArgs)
        return DFMSavedStateViewModelFactory(owner, defaultArgs, delegate, viewModelFactories)
    }
}
*/