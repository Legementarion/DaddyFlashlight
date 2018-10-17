package com.yalantis.daddyflashlight

import android.content.BroadcastReceiver
import android.widget.Toast
import android.content.ComponentName
import android.appwidget.AppWidgetManager
import android.content.Context
import android.widget.RemoteViews
import android.content.Intent
import android.hardware.Camera

class FlashlightWidgetReceiver: BroadcastReceiver() {

    private var isLightOn = false
    private var camera: Camera? = null

    override fun onReceive(context: Context, intent: Intent) {
        val views = RemoteViews(context.packageName, R.layout.new_app_widget)

//        if (isLightOn) {
//            views.setImageViewResource(R.id.button, R.drawable.off)
//        } else {
//            views.setImageViewResource(R.id.button, R.drawable.on)
//        }

        val appWidgetManager = AppWidgetManager.getInstance(context)
        appWidgetManager.updateAppWidget(
            ComponentName(context, NewAppWidget::class.java),
            views
        )

        if (isLightOn) {
            if (camera != null) {
                camera!!.stopPreview()
                camera!!.release()
                camera = null
                isLightOn = false
            }

        } else {
            // Open the default i.e. the first rear facing camera.
            camera = Camera.open()

            if (camera == null) {
                Toast.makeText(context, R.string.no_camera, Toast.LENGTH_SHORT).show()
            } else {
                // Set the torch flash mode
                val param = camera!!.parameters
                param.flashMode = Camera.Parameters.FLASH_MODE_TORCH
                try {
                    camera!!.parameters = param
                    camera!!.startPreview()
                    isLightOn = true
                } catch (e: Exception) {
                    Toast.makeText(context, R.string.no_flash, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}