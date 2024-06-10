package com.lucanicoletti.glancetutorial

import android.content.Context
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text

class MyAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            // this is not the theme your define with MaterialTheme
            GlanceTheme {
                Column(
                    modifier = GlanceModifier.fillMaxSize()
                        .background(GlanceTheme.colors.background),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "Test question?", modifier = GlanceModifier.padding(12.dp))
                    Row(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            text = "Answer 1", onClick = actionStartActivity<MainActivity>()
                        )
                        Button(
                            text = "Answer 2", onClick = actionStartActivity<MainActivity>()
                        )
                    }
                }
            }
        }
    }

}