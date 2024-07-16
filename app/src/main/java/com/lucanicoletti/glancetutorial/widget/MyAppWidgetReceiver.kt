package com.lucanicoletti.glancetutorial.widget

import androidx.glance.appwidget.GlanceAppWidgetReceiver

class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: MyAppWidget
        get() = MyAppWidget()
}