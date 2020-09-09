package com.mustafayosri.gadsleaderboard.repository

import androidx.lifecycle.MutableLiveData
import com.mustafayosri.gadsleaderboard.network.RetrofitGoogleSheet.submitAPI
import com.mustafayosri.gadsleaderboard.network.SubmitServicesAPIs
import com.mustafayosri.gadsleaderboard.pojo.ResultWrapperResponse
import com.mustafayosri.gadsleaderboard.pojo.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val userRepository by lazy {
    UserRepository(submitAPI)
}

class UserRepository(
    private val webService: SubmitServicesAPIs
) {
    fun submit(user: UserData, resultWrapper: MutableLiveData<ResultWrapperResponse<Any>>) {

        webService.submit(
            user.email, user.firstName, user.lastName, user.projectLink
        ).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                    resultWrapper.postValue(ResultWrapperResponse.Success(null))
                else
                    resultWrapper.postValue(ResultWrapperResponse.GenericError(response.code(), null))
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                resultWrapper.postValue(ResultWrapperResponse.NetworkError)
            }
        })

    }
}