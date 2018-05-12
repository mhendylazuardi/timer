package com.example.xback.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_timer.*

class TimerActivity : AppCompatActivity() {

    enum class TimerState(
            Stopped, Pause, Running
    )

    private lateinit var timer : CountDownTimer
    private timerLengthSeconds = 0L
    private timerState =TimerState.Stopped

    private var secondRemaining = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.ic_timer)
        supportActionBar?.title = "       Timer"

        fab_start.setOnClickListener{v ->
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }
        fab_pause.setOnClickListener { v ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }
        fab_stop.setOnClickListener { v ->
            timer.cancel()
            onTimerFinished()

        }
    }

    override fun onResume() {
        super.onResume()

        initTimer()

//        remove background timer, hide notification
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_timer, menu)
        return true
    }

    override fun onPause() {
        super.onPause()

        if(timerState == TimerState.Running) {
            timer.cancel()
//            start background timer and show notificatin
        }else if(timerState == TimerState.Paused)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
