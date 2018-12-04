package com.yalantis.daddyflashlight

import android.hardware.Camera
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isLightOn = false
    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.text = getString(R.string.on)
        button.setOnClickListener {
            if (isLightOn) {
                button.text = getString(R.string.on)
                ledOff()
                isLightOn = false
            } else {
                button.text = getString(R.string.off)
                ledOn()
                isLightOn = true
            }
        }

    }

    private fun ledOn() {
        camera = Camera.open()
        val params = camera?.parameters
        params?.flashMode = Camera.Parameters.FLASH_MODE_TORCH
        camera?.parameters = params
        camera?.startPreview()
        camera?.autoFocus { _, _ -> }
    }

    private fun ledOff() {
        camera?.stopPreview()
        camera?.release()
    }

}
