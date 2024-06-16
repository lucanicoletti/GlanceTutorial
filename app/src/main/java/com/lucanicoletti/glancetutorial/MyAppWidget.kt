package com.lucanicoletti.glancetutorial

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.CheckBox
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

        val myRepo = FakeGlanceDataProviderRepository.get(context)
        provideContent {
            // this is not the theme your define with MaterialTheme
            var userName by remember {
                mutableStateOf("")
            }
            var tasks by remember {
                mutableStateOf(listOf<String>())
            }
            LaunchedEffect(Unit) {
                userName = myRepo.getUsersName()
                tasks = myRepo.getUsersReminders()
            }
            GlanceTheme {
                Column(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(GlanceTheme.colors.background),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = userName, modifier = GlanceModifier.padding(12.dp))
                    Column {
                        tasks.forEach { task ->
                            Row {
                                CheckBox(checked = false, onCheckedChange = {})
                                Text(text = task)
                            }
                        }
                    }
                }
            }
        }
    }

}