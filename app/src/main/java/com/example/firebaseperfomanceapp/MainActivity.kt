package com.example.firebaseperfomanceapp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.AddTrace
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit



class MainActivity : AppCompatActivity() {


    var test = Test()
    var handler = Handler()

    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        val myTrace = FirebasePerformance.getInstance().newTrace("test_trace")
        myTrace.start()
        for (i in 1..10) {
            Thread(Runnable {
                for (i in 1..100000) {
                    test.d++
                    TimeUnit.SECONDS.sleep(1)
                    if(test.d%10 == 0L){
                        myTrace.incrementMetric("divided by 10", 1);
                    } else {
                        myTrace.incrementMetric("not divided by 10", 1);
                    }
                    Log.d("count", test.d.toString() + Thread.currentThread().toString())
                    count.post(Runnable {
                        count.text = test.d.toString()
                    })
                }
            }).start()
        }
        myTrace.stop()
    }

    class Test {
        var d = 3L;
        var d1 = 3L;
        var d2 = 3L;
        var d3 = 3L;
        var d4 = 3L;
        var d5 = 3L;
        var d6 = 3L;
        var d7 = 3L;
        var d8 = 3L;
        var s = "ddddddddddddddddddddddddddddddddddddd"
        var s2 = "ddddddddddddddddddddddddddddddddddddd"
        var s3 = "ddddddddddddddddddddddddddddddddddddd"
        var s4 = "ddddddddddddddddddddddddddddddddddddd"
        var s5 = "ddddddddddddddddddddddddddddddddddddd"
        var s6 = "ddddddddddddddddddddddddddddddddddddd"
        var s7 = "ddddddddddddddddddddddddddddddddddddd"
        var s8 = "ddddddddddddddddddddddddddddddddddddd"
        var s9 = "ddddddddddddddddddddddddddddddddddddd"
        var s10 = "ddddddddddddddddddddddddddddddddddddd"
        var s11 = "ddddddddddddddddddddddddddddddddddddd"

    }
}
