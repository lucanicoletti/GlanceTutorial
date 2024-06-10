package com.lucanicoletti.glancetutorial

import androidx.glance.appwidget.GlanceAppWidgetReceiver

class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: MyAppWidget
        get() = MyAppWidget()
}