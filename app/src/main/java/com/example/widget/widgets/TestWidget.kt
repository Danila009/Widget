package com.example.widget.widgets

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition

class TestWidget: GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    @Composable
    override fun Content() {
        TestWidgetContent()
    }

    companion object{
        const val TEST_WIDGET_PREFS_KEY = "TEST_WIDGET_PREFS_KEY"
        const val MUSIC_WIDGET_PREFS_KEY = "MUSIC_WIDGET_PREFS_KEY"
        const val RECOMMENDED_DAILY_GLASSES = 8
        const val MAX_GLASSES = 999
    }
}

class TestWidgetReceiver : GlanceAppWidgetReceiver(){
    override val glanceAppWidget: GlanceAppWidget
        get() = TestWidget()
}