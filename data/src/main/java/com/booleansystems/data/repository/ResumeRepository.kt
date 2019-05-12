package com.booleansystems.data.repository

import com.booleansystems.data.common.IBaseResultListener
import com.booleansystems.domain.entities.Resume

/**

Created by oscar on 10/05/19
operez@na-at.com.mx
 */

/**
 * Repository pattern to get info about resume
 */
open class ResumeRepository(val resumeDataSource: ResumeDataSource) {

    fun getResumeInformation(alt: String, result: IBaseResultListener<Resume>) {
        resumeDataSource.getResumeInformation(alt, result)
    }

    /**
     * Resume remote data source. This interface must be implemented in presentation
     * layer and then make use of  retrofit or any other lib to handled http request
     */
    interface ResumeDataSource {
        fun getResumeInformation(
            alt: String,
            result: IBaseResultListener<Resume>
        )
    }

}