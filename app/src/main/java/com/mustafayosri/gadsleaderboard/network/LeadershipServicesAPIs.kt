package com.noha.gadsleaderboard.network

import com.mustafayosri.gadsleaderboard.pojo.LearnerResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import java.util.*

interface LeadershipServicesAPIs {

    @GET("/api/skilliq")
    fun getSkillIQLeadershipAPI(): Deferred<Response<ArrayList<LearnerResponse>>>

    @GET("/api/hours")
    fun getLearningLeadershipAPI(): Deferred<Response<ArrayList<LearnerResponse>>>

}