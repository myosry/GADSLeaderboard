package com.mustafayosri.gadsleaderboard.ui.leaderdashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mustafayosri.gadsleaderboard.R
import com.mustafayosri.gadsleaderboard.adapter.SectionsTabsPagerAdapter
import com.mustafayosri.gadsleaderboard.ui.submit.SubmitActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Tabs setup
        val tabs: TabLayout = findViewById(R.id.tabs)

        //View pager setup
        val sectionsPagerAdapter = SectionsTabsPagerAdapter( supportFragmentManager,2,this)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        tabs.setupWithViewPager(viewPager)


        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, SubmitActivity::class.java)
            startActivity(intent)
        }
    }
}
