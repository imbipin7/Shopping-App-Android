package com.bipin.shopy.networkcalls

import retrofit2.Response
import retrofit2.http.*


interface RetrofitApi {

    @FormUrlEncoded
    @POST(LOGIN_API)
    suspend fun signUp(
        @Field("phoneNumber") phoneNumber: String?,
        @Field("countryCode") countryCode: String?,
    ): Response<BaseResponse<Any>>


    @GET(GET_API)
    suspend fun getShooterList(
        @Header(AUTH_PARAM) authToken: String?,
        @Query("per_page") perPage: String = "20",
        @Query("page") page: String?,
        @Query("search_by") searchBy: String?,
        @Query("search_value") search_value: String?,
    ): Response<BaseResponse<Any>>


}