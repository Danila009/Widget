package com.example.widget.widgets

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.*
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.layout.*
import androidx.glance.text.Text
import com.example.widget.MainActivity
import com.example.widget.widgets.TestWidget.Companion.MUSIC_WIDGET_PREFS_KEY
import com.example.widget.widgets.TestWidget.Companion.RECOMMENDED_DAILY_GLASSES
import com.example.widget.widgets.TestWidget.Companion.TEST_WIDGET_PREFS_KEY

@Composable
fun TestWidgetCounter(
    glassesOfTest:Int,
    modifier:GlanceModifier
) {
    Text(
        text = "Tes_$glassesOfTest",
        modifier = modifier
    )
}

@Composable
fun TestWidgetButtonLayout(
    musicTitle:String,
    modifier: GlanceModifier,
    context:Context
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Vertical.CenterVertically
    ) {

        Text(
            text = musicTitle,
            modifier = GlanceModifier
                .clickable(
                    onClick = actionRunCallback<DaggerTest>()
                )
                .defaultWeight()
        )

        Text(
            text = "Clear",
            modifier = GlanceModifier
                .clickable(
                    onClick = actionRunCallback<ClearTestClickAction>()
                )
                .defaultWeight()
        )

        Text(
            text = "Add",
            modifier = GlanceModifier
                .clickable(
                    onClick = actionRunCallback<AddTestClickAction>()
                )
                .defaultWeight()
        )

        Text(
            text = "Open",
            modifier = GlanceModifier
                .clickable(
                    onClick = actionStartActivity(Intent(context,MainActivity::class.java))
                )
                .defaultWeight()
        )
    }
}

@Composable
fun TestWidgetContent(
    modifier: GlanceModifier = GlanceModifier
) {
    val prefs = currentState<Preferences>()
    val glassesOfTest = prefs[intPreferencesKey(TEST_WIDGET_PREFS_KEY)] ?: 0
    val musicTitle = prefs[stringPreferencesKey(MUSIC_WIDGET_PREFS_KEY)] ?: "null"
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TestWidgetCounter(
            modifier = modifier
                .fillMaxWidth()
                .defaultWeight(),
            glassesOfTest = glassesOfTest
        )

        TestWidgetButtonLayout(
            context = context,
            musicTitle = musicTitle,
            modifier = modifier
                .fillMaxSize()
                .defaultWeight()
        )
    }
}