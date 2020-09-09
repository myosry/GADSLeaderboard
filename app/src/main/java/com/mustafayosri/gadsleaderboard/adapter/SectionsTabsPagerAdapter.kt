package com.mustafayosri.gadsleaderboard.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mustafayosri.gadsleaderboard.R
import com.mustafayosri.gadsleaderboard.ui.leaderdashboard.LearningLeadersFragment
import com.mustafayosri.gadsleaderboard.ui.leaderdashboard.SkillsLeadersFragment

class SectionsTabsPagerAdapter(fm: FragmentManager , var totalTabs:Int , val context: Context) : FragmentPagerAdapter(fm) {
    private val TITLES = arrayOf(
        R.string.tab_learner,
        R.string.tab_skills
    )

    override fun getItem(position: Int): Fragment{
        var fragment: Fragment =LearningLeadersFragment()

        when (position) {
            0 -> fragment = LearningLeadersFragment()
            1 -> fragment = SkillsLeadersFragment()
        }

        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TITLES[position])
    }

    override fun getCount(): Int {
        return totalTabs
    }
}