package bpawl.lochat.di;

import androidx.lifecycle.ViewModelProvider;

import bpawl.lochat.viewmodels.ViewModelProviderFactory;
import dagger.Binds;
import dagger.Module;


@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);
}