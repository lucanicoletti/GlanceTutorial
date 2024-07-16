package com.lucanicoletti.glancetutorial.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.text.Text
import com.lucanicoletti.glancetutorial.R

class MyAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                Scaffold(
                    titleBar = {
                        TitleBar(
                            startIcon = ImageProvider(R.drawable.ic_launcher_foreground),
                            title = "Glance tutorial"
                        )
                    }
                ) {
                    Column {
                        Text(text = "Your notes")
                        Text(text = "Your notes edited test")
                    }
                }
            }
        }
    }
}