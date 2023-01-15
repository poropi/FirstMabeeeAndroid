package jp.novars.firstmabeee

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.SeekBar
import android.widget.ToggleButton

import jp.novars.mabeee.sdk.App
import jp.novars.mabeee.sdk.ui.ScanActivity

class MainActivity : AppCompatActivity() {

    internal var seekValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.getInstance().initializeApp(applicationContext)

        val scanButton = findViewById(R.id.scanButton) as Button
        scanButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ScanActivity::class.java)
            startActivity(intent)
        }

        val seekBar = findViewById(R.id.seekBar) as SeekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                seekValue = seekBar.progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
        val button = findViewById(R.id.toggleButton) as ToggleButton
        button.setOnCheckedChangeListener { _, b ->
            val devices = App.getInstance().devices
            for (device in devices) {
                device.pwmDuty = if (b) seekValue else 0
            }
        }
    }
}
