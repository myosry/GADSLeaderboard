package com.mustafayosri.gadsleaderboard.repository

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mustafayosri.gadsleaderboard.network.RetrofitLeaderBoardClient
import com.mustafayosri.gadsleaderboard.ui.leaderdashboard.LearningLeadersFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HoursRepository(
    private val context: Fragment
) {

    init {

        GlobalScope.launch(Dispatchers.Main) {

            try {

                val response = RetrofitLeaderBoardClient.callApi.getLearningLeadershipAPI().await()

                if (response.isSuccessful) {

                    (context as LearningLeadersFragment).onGetLearningLeadersAPiCallBack(response)


                } else {

                    Toast.makeText(context.activity,"some thing went wrong", Toast.LENGTH_LONG).show()
                }

            } catch (e: Exception) {

            }
        }

    }

}