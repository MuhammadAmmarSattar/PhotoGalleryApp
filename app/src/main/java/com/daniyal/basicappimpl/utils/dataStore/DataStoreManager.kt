package com.example.basearchitecture.common.Utils.dataStore


import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import com.daniyal.basicappimpl.BuildConfig
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(context: Context) {

    // Create a Preferences DataStore
    private val dataStore: DataStore<Preferences> by lazy {
        context.createDataStore(
            name = BuildConfig.DATA_STORE_NAME
        )
    }

    suspend fun <T> setData(key: Preferences.Key<T>, data: T) {
        dataStore.edit {preference->
            preference[key] = data
        }
    }

    suspend fun <T> getData(key: Preferences.Key<T>): T? {
        return dataStore.data.map {preferences->
            preferences[key]
        }.firstOrNull()
    }

    suspend fun <T> getData(key: Preferences.Key<T>, defaultValue: T): T {
        return when (val x = dataStore.data.map {preferences->
            preferences[key]
        }.firstOrNull()) {
            null -> defaultValue
            else -> x
        }
    }

}
//https://proandroiddev.com/lets-explore-jetpack-datastore-in-android-621f3564b57