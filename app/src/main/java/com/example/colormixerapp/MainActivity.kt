package com.example.colormixerapp

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
//import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.example.colormixerapp.R


class MainActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchRed: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchGreen: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchBlue: Switch
    private lateinit var colorDisplay: TextView
    private lateinit var valueRed: EditText
    private lateinit var valueGreen: EditText
    private lateinit var valueBlue: EditText
    private lateinit var resetBtn: Button
    private lateinit var redSeek: SeekBar
    private lateinit var greenSeek: SeekBar
    private lateinit var blueSeek: SeekBar
    var lastProcessRed:Int=0
    var lastProcessGreen:Int=0
    var lastProcessBlue:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        this.connectViews()
        val progress=128
        redSeek.progress=progress
        blueSeek.progress=progress
        greenSeek.progress=progress
        setRGBcolor()
        val red: Float = 128 / 255f
        val formattedRed = "%.3f".format(red)
        valueRed.setText("$formattedRed")
        val green: Float = 128/ 255f
        val formattedGren = "%.3f".format(green)
        valueGreen.setText("$formattedGren")
        val blue: Float = 128/ 255f
        val formattedBlue = "%.3f".format(blue)
        valueBlue.setText("$formattedBlue")
        callbacks()
    }

    private fun connectViews() {
        this.switchRed = this.findViewById(R.id.switch1)
        this.switchGreen = this.findViewById(R.id.switch2)
        this.switchBlue = this.findViewById(R.id.switch3)
        this.colorDisplay = this.findViewById(R.id.textView)
        this.valueRed = this.findViewById(R.id.editTextText1)
        this.valueGreen = this.findViewById(R.id.editTextText2)
        this.valueBlue = this.findViewById(R.id.editTextText3)
        this.resetBtn = this.findViewById(R.id.reset)
        this.redSeek = this.findViewById(R.id.seekBar1)
        this.greenSeek = this.findViewById(R.id.seekBar2)
        this.blueSeek = this.findViewById(R.id.seekBar3)
    }

    private fun callbacks() {

        this.redSeek.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                val red: Float = (seek.progress) / 255f
                val formattedRed = "%.3f".format(red)
                valueRed.setText("$formattedRed")
                setRGBcolor()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {

            }

            override fun onStopTrackingTouch(seek: SeekBar) {


            }
        })
        this.greenSeek.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                val green: Float = (seek.progress) / 255f
                val formattedGreen = "%.3f".format(green)
                valueGreen.setText("$formattedGreen")
                setRGBcolor()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {

            }

            override fun onStopTrackingTouch(seek: SeekBar) {


            }
        })
        this.blueSeek.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                val blue: Float = (seek.progress) / 255f
                val formattedBlue = "%.3f".format(blue)
                valueBlue.setText("$formattedBlue")
                setRGBcolor()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {

            }

            override fun onStopTrackingTouch(seek: SeekBar) {


            }
        })
        this.valueRed.setOnKeyListener {_, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val text = valueRed.text.toString()

                    val value = text.toFloat()

                    val progress = (value * 255).toInt()
                    if(progress in 0..255){
                        redSeek.progress = progress
                    }
                    else{
                        Toast.makeText(this, "Enter value between 0 and 1", Toast.LENGTH_SHORT).show()
                    }


                } catch (e: NumberFormatException) {
//t.d
                }
                return@setOnKeyListener true
            }
            false
        }
        this.valueGreen.setOnKeyListener {_, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val text = valueGreen.text.toString()

                    val value = text.toFloat()

                    val progress = (value * 255).toInt()
                    if(progress in 0..255){
                        greenSeek.progress = progress
                    }
                    else{
                        Toast.makeText(this, "Enter value between 0 and 1", Toast.LENGTH_SHORT).show()
                    }


                } catch (e: NumberFormatException) {
//t.d
                }
                return@setOnKeyListener true
            }
            false
        }
        this.valueBlue.setOnKeyListener {_, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                try {
                    val text = valueBlue.text.toString()

                    val value = text.toFloat()

                    val progress = (value * 255).toInt()
                    if(progress in 0..255){
                        blueSeek.progress = progress
                    }
                    else{
                        Toast.makeText(this, "Enter value between 0 and 1", Toast.LENGTH_SHORT).show()
                    }


                } catch (e: NumberFormatException) {
//t.d
                }
                return@setOnKeyListener true
            }
            false
        }

        this.resetBtn.setOnClickListener(){
            val progress=128
            switchRed.isChecked = true
            switchGreen.isChecked = true
            switchBlue.isChecked = true
            redSeek.progress=progress
            blueSeek.progress=progress
            greenSeek.progress=progress
        }

        switchRed.setOnCheckedChangeListener{ _ , isChecked ->


            if (isChecked) {
                valueRed.isEnabled=true
                redSeek.setEnabled(true)
                if(lastProcessRed!=0){
                    redSeek.progress=lastProcessRed
                }
            }
            if(isChecked==false) {
                valueRed.isEnabled=false
                redSeek.setEnabled(false)
                lastProcessRed=redSeek.progress
                redSeek.progress=0
            }

        }
        switchGreen.setOnCheckedChangeListener{ _ , isChecked ->
            if (isChecked) {
                valueGreen.isEnabled=true
                greenSeek.setEnabled(true)
                if(lastProcessGreen!=0){
                    greenSeek.progress=lastProcessGreen
                }
            }
            if(!isChecked) {
                valueGreen.isEnabled=false
                greenSeek.setEnabled(false)
                lastProcessGreen=greenSeek.progress
                greenSeek.progress=0
            }

        }
        switchBlue.setOnCheckedChangeListener{ _ , isChecked ->
            if (isChecked) {
                valueBlue.isEnabled=true
                blueSeek.setEnabled(true)
                if(lastProcessBlue!=0){
                    blueSeek.progress=lastProcessBlue
                }
            }
            if(!isChecked) {
                valueBlue.isEnabled=false
                blueSeek.setEnabled(false)
                lastProcessBlue=blueSeek.progress
                blueSeek.progress=0
            }

        }
    }
    fun setRGBcolor(){
        val hex=String.format(
            "#%02x%02x%02x",
            redSeek.progress,
            greenSeek.progress,
            blueSeek.progress
        )
        colorDisplay.setBackgroundColor(Color.parseColor(hex))
    }

}