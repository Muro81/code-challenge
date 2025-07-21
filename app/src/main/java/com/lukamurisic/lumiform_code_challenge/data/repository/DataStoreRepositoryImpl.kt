package com.lukamurisic.lumiform_code_challenge.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import com.lukamurisic.lumiform_code_challenge.core.utils.Constants.DATASTORE_NAME
import com.lukamurisic.lumiform_code_challenge.domain.repository.DataStoreRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreImpl @Inject constructor(@ApplicationContext val context: Context) :
    DataStoreRepository {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    companion object {}


}