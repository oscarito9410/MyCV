package com.booleansystems.mycv.ui.home.presenter

import com.booleansystems.codechallenge.utils.Constants
import com.booleansystems.data.common.IBaseResultListener
import com.booleansystems.mycv.base.BasePresenter
import com.booleansystems.mycv.ui.home.contract.HomeContract
import com.booleansystems.mycv.ui.home.mapper.Resume
import com.booleansystems.mycv.ui.home.mapper.toPresentationModel
import com.booleansystems.usecase.ExtractResumeInformationUseCase
import com.booleansystems.domain.entities.Resume as DomainResume

/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */
/**
 * Home presenter implementation. This class is injected to HomeActivity by Koin framework
 */
open class HomePresenter(private val extractResumeinformationUseCase: ExtractResumeInformationUseCase) :
    BasePresenter<HomeContract.View>(), HomeContract.Presenter, IBaseResultListener<DomainResume> {


    lateinit var mResumeInfo: Resume

    override fun getResumeInfo(hasInternetConnection: Boolean) {
        if (hasInternetConnection) {
            view()!!.showProgressDialog()
            extractResumeinformationUseCase.invoke(Constants.HttpConfig.DEFAULT_ALT, this)
        } else notifyNoInternetAvailable()

    }


    override fun onSuccess(response: DomainResume) {
        view()!!.hideProgressDialog()
        mResumeInfo = response.toPresentationModel()
        setInformationSuccess()
    }

    fun notifyNoInternetAvailable() {
        view()!!.onNoInternetAvailable()
    }

    fun setInformationSuccess() {
        view()!!.onSuccessInfoBasicsLoaded(mResumeInfo.basics!!)
        view()!!.onSuccessinfoSkillsLoaded(mResumeInfo.skills!!.first())
        view()!!.onSuccessInfoWorkLoaded(mResumeInfo.work!!)
    }

    override fun handlerWebSiteUrl(): String {
        return mResumeInfo.basics!!.website!!
    }

    override fun handleLinkedinUrl(): String {
        return mResumeInfo.basics!!.linkedIn!!
    }

    override fun handleGitHubUrl(): String {
        return mResumeInfo.basics!!.gitHub!!
    }

    override fun onError(error: Throwable) {
        view()!!.hideProgressDialog()
        view()!!.onHttpError(error.message!!)
    }

}