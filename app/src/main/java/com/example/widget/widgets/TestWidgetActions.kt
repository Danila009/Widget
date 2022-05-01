package com.example.widget.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.example.widget.MainViewModel
import com.example.widget.widgets.TestWidget.Companion.MAX_GLASSES
import com.example.widget.widgets.TestWidget.Companion.TEST_WIDGET_PREFS_KEY
import javax.inject.Inject

class AddTestClickAction:ActionCallback {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId){
            it.toMutablePreferences()
                .apply {
                    Toast.makeText(context, "testing", Toast.LENGTH_SHORT).show()
                    val glassesOfTest = this[intPreferencesKey(TEST_WIDGET_PREFS_KEY)] ?: 0
                    if (glassesOfTest < MAX_GLASSES){
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

    @Inject lateinit var mainViewModel: MainViewModel

    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId){
            it.toMutablePreferences()
        }
        TestWidget().update(context, glanceId)
    }
}

class Number(
    private val number: MutableState<Int>
):ActionCallback{

    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId){
            it.toMutablePreferences()
                .apply {
                    number.value++
                }
        }
        TestWidget().update(context, glanceId)
    }
}