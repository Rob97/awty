package edu.washington.sseera.awty

import android.content.BroadcastReceiver;
import android.content.ContentValues.TAG
import android.content.Context;
import android.content.Intent;
import android.util.Log
import android.widget.Toast;

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {


        val messageDisplayed = intent?.getStringExtra("messageInput")
        Log.i("ALARM", "onReceive called")

                Toast.makeText(context, messageDisplayed, Toast.LENGTH_LONG).show()



    }

}