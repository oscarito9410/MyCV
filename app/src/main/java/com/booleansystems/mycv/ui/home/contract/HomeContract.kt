package com.booleansystems.mycv.ui.home.contract

import com.booleansystems.mycv.base.IBaseView
import com.booleansystems.mycv.ui.home.mapper.Basics
import com.booleansystems.mycv.ui.home.mapper.Skills
import com.booleansystems.mycv.ui.home.mapper.Work

/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */
interface HomeContract {

    interface Presenter {
        /**
         * Receives param from view, so presenter is doesn't kwnon anything
         * about context or any framework component
         */
        fun getResumeInfo(hasInternetConnection: Boolean)

        /**
         * Return urlImage url from Resume presentation model
         */
        fun handlerWebSiteUrl(): String

        /**
         * Return linkedin url from Resume presentation model
         */
        fun handleLinkedinUrl(): String

        /**
         * Return github url from Resume presentation model
         */
        fun handleGitHubUrl(): String
    }

    interface View : IBaseView {
        /**
         * Notify when internet is not available
         */
        fun onNoInternetAvailable()

        /**
         * Notify when an  http error happen. For example 404, 500
         */
        fun onHttpError()

        /**
         * Notify about  timeout error
         */
        fun onTimeoutError()

        /**
         * Notify when an unhandled error happen
         */
        fun onGeneralError(message: String)

        /**
         * Send basic information from Resume
         */
        fun onSuccessInfoBasicsLoaded(basic: Basics)

        /**
         * Send skills infromation from Resume
         */
        fun onSuccessinfoSkillsLoaded(skills: Skills)

        /**
         * Send work experience from Resume
         */
        fun onSuccessInfoWorkLoaded(workItems: List<Work>)
    }
}