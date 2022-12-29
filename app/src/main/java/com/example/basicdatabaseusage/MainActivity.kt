package com.example.basicdatabaseusage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicdatabaseusage.databases.WordEntity
import com.example.basicdatabaseusage.databinding.ActivityMainBinding
import com.example.basicdatabaseusage.recycler.WordListAdapter
import com.example.basicdatabaseusage.recycler.WordListAdapter.WordDiff
import com.example.basicdatabaseusage.recycler.WordViewModel


class MainActivity : AppCompatActivity() {
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    private lateinit var binding: ActivityMainBinding

    private lateinit var mWordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordListAdapter(WordDiff())
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        mWordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        mWordViewModel.getAllWords().observe(this) { words: List<WordEntity?>? ->
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words)
        }

        binding.fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            mWordViewModel.insert(WordEntity(data!!.getStringExtra(NewWordActivity.EXTRA_REPLY)!!))
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}