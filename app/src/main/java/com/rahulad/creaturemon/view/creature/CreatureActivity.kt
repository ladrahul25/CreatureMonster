package com.rahulad.creaturemon.view.creature


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rahulad.creaturemon.R
import com.rahulad.creaturemon.model.AttributeStore
import com.rahulad.creaturemon.model.AttributeType
import com.rahulad.creaturemon.model.Avatar
import com.rahulad.creaturemon.model.CreatureGenerator
import com.rahulad.creaturemon.model.room.RoomRepository
import com.rahulad.creaturemon.view.avatars.AvatarAdapter
import com.rahulad.creaturemon.view.avatars.AvatarBottomDialogFragment
import com.rahulad.creaturemon.viewmodel.CreatureViewModel
import com.rahulad.creaturemon.viewmodel.CreatureViewModelFactory
import kotlinx.android.synthetic.main.activity_creature.*




class CreatureActivity : AppCompatActivity(), AvatarAdapter.AvatarListener {

    private lateinit var viewModel : CreatureViewModel
    private lateinit var viewModelFactory: CreatureViewModelFactory
    private lateinit var bottomDialogFragment: AvatarBottomDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creature)

        viewModelFactory = CreatureViewModelFactory(CreatureGenerator(), RoomRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(CreatureViewModel::class.java)

        configureUI()
        configureSpinnerAdapters()
        configureSpinnerListeners()
        configureEditText()
        configureClickListeners()
        configureLivedataObservers()
    }

    private fun configureUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.add_creature)
        if (viewModel.drawable != 0) hideTapLabel()
    }

    private fun configureSpinnerAdapters() {
        intelligence.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, AttributeStore.INTELLIGENCE)
        strength.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, AttributeStore.STRENGTH)
        endurance.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, AttributeStore.ENDURANCE)
    }

    private fun configureSpinnerListeners() {
        intelligence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.attributeSelected(AttributeType.INTELLIGENCE, position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.attributeSelected(AttributeType.STRENGTH, position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.attributeSelected(AttributeType.ENDURANCE, position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun configureEditText() {
        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.name = s.toString()
            }
        })
    }

    private fun configureClickListeners() {
        avatarImageView.setOnClickListener {
            bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
            bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
        }

        saveButton.setOnClickListener {
            if (viewModel.saveCreature()){
                Toast.makeText(this, getString(R.string.creature_saved), Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, getString(R.string.error_saving_creature), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun configureLivedataObservers(){
        viewModel.getCreatureLiveData().observe(this, Observer { creature ->
            creature.let {
                hitPoints.text = creature.hitPoints.toString()
                avatarImageView.setImageResource(creature.drawables)
                nameEditText.setText(creature.name)
            }
        })
    }

    override fun avatarClicked(avatar: Avatar) {
        viewModel.drawableSelected(avatar.drawable)
        hideTapLabel()
        bottomDialogFragment.dismiss()
    }

    private fun hideTapLabel() {
        tapLabel.visibility = View.INVISIBLE
    }
}