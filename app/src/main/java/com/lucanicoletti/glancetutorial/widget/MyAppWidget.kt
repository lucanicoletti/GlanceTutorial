package com.lucanicoletti.glancetutorial.widget

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.text.Text
import com.lucanicoletti.glancetutorial.R

class MyAppWidget : GlanceAppWidget() {


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val repo = GlanceTutorialWidgetRepository.get(context)
        provideContent {
            GlanceTheme {
                Scaffold(titleBar = {
                    TitleBar(
                        startIcon = ImageProvider(R.drawable.ic_launcher_foreground),
                        title = "Glance tutorial"
                    )
                }) {
                    val notes = repo.loadNotes().collectAsState(initial = emptyList())
                    Column {
                        notes.value.forEach {
                            Text(
                                modifier = GlanceModifier.padding(vertical = 4.dp),
                                text = "• ${it.title}",
                                maxLines = 1,
                            )
                        }
                    }
                }
            }
        }
    }
}