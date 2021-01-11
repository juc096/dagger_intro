package com.example.android.dagger.di

import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module

//This storage module file provides Dagger a list of what to do when required to create
//objects related to Storage

// in our example, we want Dagger to provide SharedpreferencesStorage when it needs to
// create a Storage object.

//As the app grows larger with more complexity, it's imperative we keep code organized like this

@Module
abstract class StorageModule {
    //provideStorage is an arbitrary name; dagger does not care about name, and only
    //cares about parameter/return type!
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage) : Storage
}