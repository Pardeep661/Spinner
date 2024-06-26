package com.pardeep.spinner

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pardeep.spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var new_item : String? =null
    var binding : ActivityMainBinding? = null
    lateinit var arrrayAdp : ArrayAdapter<String>
    var spinnervalue = mutableListOf("First","Second","Third")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding?.addButton?.setOnClickListener {
            Dialog(this).apply{
                setContentView(R.layout.custom_dialog)
                var edit_text = findViewById<EditText>(R.id.editText)
                var add_button = findViewById<Button>(R.id.add_button)

                add_button.setOnClickListener {
                    if (edit_text.text.trim().isNullOrEmpty()) {
                        edit_text.error = "Enter name"
                    } else {
                        spinnervalue += edit_text.text.toString()
                        dismiss()
                    }
                }

            }.show()
        }



        arrrayAdp = ArrayAdapter(this,android.R.layout.simple_list_item_1,spinnervalue)

        binding?.dynamicSpinner?.adapter = arrrayAdp

        binding?.staticSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var selectedItem = binding?.staticSpinner?.selectedItem as String
                var selectedItemPosition = binding?.staticSpinner?.selectedItemPosition

                // Toast on click
                Toast.makeText(this@MainActivity, "Selected Item is ${selectedItem} and position is ${selectedItemPosition}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}