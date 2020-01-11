package com.maleev.learning.a50androidhacks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(HackListFragment.newInstance())
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, null)
            .addToBackStack(null)
            .commit()
    }
}