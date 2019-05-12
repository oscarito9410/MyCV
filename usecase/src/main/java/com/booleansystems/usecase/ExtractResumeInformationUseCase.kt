package com.booleansystems.usecase

import com.booleansystems.data.common.IBaseResultListener
import com.booleansystems.data.repository.ResumeRepository
import com.booleansystems.domain.entities.Resume

/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */
open class ExtractResumeInformationUseCase(private val resumeRepository: ResumeRepository) {
    operator open fun invoke(alt: String, result: IBaseResultListener<Resume>) =
        resumeRepository.getResumeInformation(alt, result)
}