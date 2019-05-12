package com.booleansystems.codechallenge.dependencies.rest

import com.booleansystems.codechallenge.utils.Constants
import com.booleansystems.domain.entities.Resume
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */
interface RestEndpoints {
    @GET(Constants.HttpConfig.MY_CV_ENDPOINT)
    fun info(@Query("alt") alt: String): Observable<Resume>
}
