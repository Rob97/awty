package edu.washington.sseera.awty

import android.content.BroadcastReceiver;
import android.content.ContentValues.TAG
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast;
import edu.washington.sseera.awty.R.styleable.AlertDialog
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {


        val messageDisplayed = intent?.getStringExtra("messageInput")
        val message = intent?.getStringExtra("message")
        val phoneNumber = intent?.getStringExtra("phoneNumber")
        val testingNumber1 = 5556
        val testingNumber2 = 5554
        Log.i("ALARM", "onReceive called")

                Toast.makeText(context, messageDisplayed, Toast.LENGTH_LONG).show()

        try {
            SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, null, null);
            //Testing with emulator
            //SmsManager.getDefault().sendTextMessage("15555215554", null, message, null, null);


        } catch (e:Exception) {
          Log.e("AlarmReviever", "SMS IS NOT WORKING")
        }



    }

}