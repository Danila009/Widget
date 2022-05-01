package com.example.widget.widgets

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.withStateAtLeast
import com.example.widget.MainViewModel
import com.example.widget.api.repository.ApiRepository
import com.example.widget.di.DaggerAppComponent
import com.example.widget.widgets.TestWidget.Companion.MAX_GLASSES
import com.example.widget.widgets.TestWidget.Companion.MUSIC_WIDGET_PREFS_KEY
import com.example.widget.widgets.TestWidget.Companion.TEST_WIDGET_PREFS_KEY
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTestClickAction:ActionCallback {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId){
            it.toMutablePreferences()
                .apply {
                    val glassesOfTest = this[intPreferencesKey(TEST_WIDGET_PREFS_KEY)] ?: 0
                    if (glassesOfTest < MAX_GLASSES){
                        Log.e("titleMusic:",glassesOfTest.toString())
                        this[intPreferencesKey(TEST_WIDGET_PREFS_KEY)] = glassesOfTest + 1
                    }
                }
        }

        TestWidget().update(context, glanceId)
    }
}

class ClearTestClickAction:ActionCallback{
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId){
            it.toMutablePreferences()
                .apply {
                    this[intPreferencesKey(TEST_WIDGET_PREFS_KEY)] = 0
                }
        }
        TestWidget().update(context, glanceId)
    }
}

class DaggerTest:ActionCallback{

    private val daggerAppComponent = DaggerAppComponent.create()

    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId){
            it.toMutablePreferences()
                .apply {
                    Log.e("titleMusic:","//")
                    val titleMusic = daggerAppComponent.apiRepository()
                        .getMusic()
                        .body()?.title.toString()
                    Log.e("titleMusic:","/$titleMusic/")
                    this@apply[stringPreferencesKey(MUSIC_WIDGET_PREFS_KEY)] = titleMusic
                }
        }
        TestWidget().update(context, glanceId)
    }
}