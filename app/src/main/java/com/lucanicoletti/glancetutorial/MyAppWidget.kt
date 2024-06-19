package com.lucanicoletti.glancetutorial

import android.content.Context
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
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text

class MyAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        val myRepo = FakeGlanceDataProviderRepository.get(context)
        provideContent {
            val userName = myRepo.getUsersName()
            val notes = myRepo.getUsersNotes()

            // this is not the theme your define with MaterialTheme
            GlanceTheme {
                Scaffold(
                    titleBar = {
                        TitleBar(
                            startIcon = ImageProvider(R.drawable.ic_launcher_foreground),
                            title = userName
                        )
                    }
                ) {
                    Column(
                        modifier = GlanceModifier.fillMaxSize().padding(16.dp),
                    ) {
                        for (note in notes) {
                            Text(text = "- $note")
                        }
                    }
                }
            }
        }
    }

}