package com.example.storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storage.adapter.FilesAdapter
import com.example.storage.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        FilesAdapter(onDeleteClick = {
            it.delete()
            updateList()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateList()

        binding.filesListView.layoutManager = LinearLayoutManager(this)
        binding.filesListView.adapter = adapter;

        binding.targetFolder.setOnCheckedChangeListener { radioGroup, i ->
            updateList()
        }

        binding.createBt.setOnClickListener {
            val fileName = binding.fileNameTxt.text.toString()
            val content = binding.fileContentTxt.text.toString()

            if(fileName.isNotEmpty() && content.isNotEmpty()){
                val file = File(getDir(), "$fileName.txt")
                file.writeText(content)

                updateList()
            }
        }
    }

    fun getDir() : File? {
        return when(binding.targetFolder.checkedRadioButtonId){
            binding.radioInternal.id ->filesDir
            else -> getExternalFilesDir(null)
        }
    }

    fun updateList(){
        adapter.submitList(getDir()?.listFiles()?.toList())
    }
}