package com.yalantis.daddyflashlight

import android.app.IntentService
import android.content.Intent
import android.hardware.Camera

class FlashlightService : IntentService(FLASHLIGHT_SERVICE) {

    companion object {
        const val FLASHLIGHT_SERVICE = "flashlight_service"
    }

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val action = intent.action
            if (FLASHLIGHT_SERVICE == action) {
                handleFlashlightAction()
            }
        }
    }

    private fun handleFlashlightAction() {
        val cam = Camera.open()
        val p = cam.parameters
        p.flashMode = Camera.Parameters.FLASH_MODE_TORCH
        cam.parameters = p
        cam.startPreview()
    }
}