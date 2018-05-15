package edu.washington.sseera.awty

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById(R.id.button) as Button

        val messageInput = findViewById<EditText>(R.id.messageInput) as EditText
        val phoneNumberInput = findViewById<EditText>(R.id.phoneNumberInput) as EditText
        val minuteInput = findViewById<EditText>(R.id.minuteInput) as EditText

        val alarmMan = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)


        //Validates the values entered are correct
        fun validateEntries(): Boolean {
            val mins = minuteInput.text;
            if (messageInput.text.length == 0) {
                Toast.makeText(this, "Message cannot be blank",
                        Toast.LENGTH_SHORT).show()
                return false;
            } else if (phoneNumberInput.text.length == 0 || phoneNumberInput.text.length != 10) {
                Toast.makeText(this, "Need to enter 10 digits for Phone Number Entry",
                        Toast.LENGTH_SHORT).show()
                return false
            } else if (minuteInput.text.isEmpty() || mins.equals("0") || mins.contains('-')) {
                Toast.makeText(this,
                        "The Interval cannot be blank or equal to zero or less than zero",
                        Toast.LENGTH_LONG).show()
                return false
            }
            return true;
        }

        //Formats the phone number
         fun phoneFormat(phoneNumber: String): String {
            return "(" + phoneNumber.substring(0, 3) +")" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6)
        }


        button.setOnClickListener {

            if (validateEntries()) {
                if(button.text == "Start") {

                    val toastMessage = "${phoneFormat(phoneNumberInput.text.toString())}: $messageInput"
                    intent.putExtra("messageInput", toastMessage)

                    val pendingUpdate = PendingIntent.getBroadcast(this, 0,
                            intent, PendingIntent.FLAG_UPDATE_CURRENT)

                    val minIntervalLong = minuteInput.text.toString().toLong() * 1000 * 60

                    Log.i("MAINACTIVTY", "Sending Intent")

                    alarmMan.setRepeating(AlarmManager.RTC_WAKEUP,
                            5000, minIntervalLong, pendingUpdate)

                    button.text = "Stop"

                }else{
                    button.text = "Start"

                    val pendingCancel = PendingIntent.getBroadcast(this, 0,
                            intent, PendingIntent.FLAG_CANCEL_CURRENT)

                    alarmMan.cancel(pendingCancel)
                }
            }

        }
    }
}
