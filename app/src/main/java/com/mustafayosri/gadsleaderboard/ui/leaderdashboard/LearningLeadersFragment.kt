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
import com.mustafayosri.gadsleaderboard.repository.HoursRepository
import kotlinx.android.synthetic.main.fragment_learning_leaders.*
import retrofit2.Response
import java.util.ArrayList

class LearningLeadersFragment : Fragment() {

    lateinit var rvLeaders: RecyclerView
    lateinit var mAdapter: LeaderBoardGeneralAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_learning_leaders, container, false)

        initViews(view)
        HoursRepository(this)
        return view
    }

    private fun initViews(view: View) {

        rvLeaders = view.findViewById(R.id.rvLearner)
        layoutManager = LinearLayoutManager(this.activity)
        rvLeaders.layoutManager = layoutManager
        rvLeaders.isNestedScrollingEnabled = false
        rvLeaders.itemAnimator = DefaultItemAnimator()

        mAdapter = LeaderBoardGeneralAdapter(this)

        rvLeaders.adapter = mAdapter
    }

    fun onGetLearningLeadersAPiCallBack(response: Response<ArrayList<LearnerResponse>>) {

        clLoading.visibility=View.GONE

        val sortedList = response.body()!!.sortedWith(compareBy { it.number })
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        mAdapter.renewItems(sortedList,"hours")
        rvLeaders.visibility=View.VISIBLE

    }


}
