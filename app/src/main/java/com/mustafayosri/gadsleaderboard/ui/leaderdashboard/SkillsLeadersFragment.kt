package com.mustafayosri.gadsleaderboard.ui.leaderdashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.mustafayosri.gadsleaderboard.R
import com.mustafayosri.gadsleaderboard.adapter.LeaderBoardGeneralAdapter
import com.mustafayosri.gadsleaderboard.pojo.LearnerResponse
import com.mustafayosri.gadsleaderboard.repository.SkillsRepository
import kotlinx.android.synthetic.main.fragment_learning_leaders.*
import retrofit2.Response
import java.util.ArrayList


class SkillsLeadersFragment : Fragment() {

    lateinit var rvSkills: RecyclerView
    lateinit var mAdapter: LeaderBoardGeneralAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_skills_leaders, container, false)
        initViews(view)
        SkillsRepository(this)

        return view
    }

    private fun initViews(view: View) {

        rvSkills = view.findViewById(R.id.rvSkills)
        layoutManager = LinearLayoutManager(this.activity)
        rvSkills.layoutManager =layoutManager
        rvSkills.isNestedScrollingEnabled = false
        rvSkills.itemAnimator = DefaultItemAnimator()

        mAdapter = LeaderBoardGeneralAdapter(this)

        rvSkills.adapter = mAdapter
    }

    fun onGetSkillsLeadersAPiCallBack(response: Response<ArrayList<LearnerResponse>>) {

        clLoading.visibility = View.GONE

        val sortedList = response.body()!!.sortedWith(compareBy { it.number })
        mAdapter.renewItems(sortedList, "skills")
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rvSkills.visibility = View.VISIBLE
    }

}
