package com.rahulad.creaturemon.view.allcreatures

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulad.creaturemon.R
import com.rahulad.creaturemon.model.room.RoomRepository
import com.rahulad.creaturemon.view.creature.CreatureActivity
import com.rahulad.creaturemon.viewmodel.AllCreaturesViewModel
import com.rahulad.creaturemon.viewmodel.AllCreaturesViewModelFactory
import kotlinx.android.synthetic.main.activity_all_creatures.*
import kotlinx.android.synthetic.main.content_all_creatures.*

/**
 * Created by Rahul Lad on 7/25/2020.
 */
class AllCreaturesActivity : AppCompatActivity() {

    private lateinit var viewModel: AllCreaturesViewModel
    private lateinit var viewModelFactory: AllCreaturesViewModelFactory

    private val adapter = CreatureAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_creatures)
        setSupportActionBar(toolbar)

        viewModelFactory = AllCreaturesViewModelFactory(RoomRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(AllCreaturesViewModel::class.java)

        creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
        creaturesRecyclerView.adapter = adapter

        viewModel.getAllCreaturesLiveData().observe(this, Observer { creatures ->
            creatures?.let {
                adapter.updateCreatures(creatures)
            }
        })

        fab.setOnClickListener {
            startActivity(Intent(this, CreatureActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear_all -> {
               viewModel.clearAllCreatures()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

