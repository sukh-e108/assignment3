package com.example.todolistapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAddTask = findViewById<Button>(R.id.buttonAddTask)

        buttonAddTask.setOnClickListener {
            val task = editTextTask.text.toString()
            val intent = Intent(this, TaskDetailsActivity::class.java).apply {
                putExtra("EXTRA_TASK", task)
            }
            startActivity(intent)
        }
    }
}
