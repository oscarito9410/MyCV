package com.booleansystems.mycv.ui.home.usecase

import com.booleansystems.codechallenge.dependencies.rest.RestEndpoints
import com.booleansystems.data.common.IBaseResultListener
import com.booleansystems.data.repository.ResumeRepository
import com.booleansystems.domain.entities.Resume
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
Created by oscar on 11/05/19
operez@na-at.com.mx
 */
/**
 * The following class implements our remoteDataSource interface. The implementation use retrofit
 * in order to get resume information. This class is provide by using DI in our AppInjector module
 */
class ResumeDataSourceImpl(val endpoints: RestEndpoints) : ResumeRepository.ResumeDataSource {
    var mDisaposable: Disposable? = null

    override fun getResumeInformation(alt: String, result: IBaseResultListener<Resume>) {
        mDisaposable =
            endpoints.info(alt)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({
                    result.onSuccess(it)
                    mDisaposable!!.dispose()

                }, {
                    result.onError(it)
                    mDisaposable!!.dispose()
                })

    }

}