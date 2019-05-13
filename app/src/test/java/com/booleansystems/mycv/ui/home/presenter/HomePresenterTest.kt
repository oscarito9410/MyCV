package com.booleansystems.mycv.ui.home.presenter

import androidx.lifecycle.Lifecycle
import com.booleansystems.data.common.IBaseResultListener
import com.booleansystems.data.repository.ResumeRepository
import com.booleansystems.domain.entities.*
import com.booleansystems.mycv.ui.home.contract.HomeContract
import com.booleansystems.usecase.ExtractResumeInformationUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.validateMockitoUsage
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*


/**
 * Created by oscar on 11/05/19
 * operez@na-at.com.mx
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class HomePresenterTest {

    val mMockView: HomeContract.View = mock()

    val mMockLifecycle: Lifecycle = mock()

    var mEnabledSuccessResponse: Boolean = false


    val mMockDataSource: ResumeRepository.ResumeDataSource = mock() {
        on {
            getResumeInformation(anyString(), any())
        }.thenAnswer(Answer {
            val response = it.arguments[1] as IBaseResultListener<Resume>
            if (mEnabledSuccessResponse) {
                response.onSuccess(
                    Resume(
                        Basics(
                            "oscar", "developer", "...", "some@gmail.com",
                            "554929340", "somesite.com", "developer with five years of experience",
                            listOf(
                                Profiles("Linkedin", "operez", "someUrl.com"),
                                Profiles("GitHub", "operez94", "someUrl.com")
                            )
                        ), Collections.singletonList(
                            Work(
                                "na-at", "android developer", "some web page", "12/10/17", "current",
                                "develop android apps", emptyList()
                            )
                        ),
                        Collections.singletonList(
                            Skills(
                                "android development",
                                "advance", Collections.singletonList("kotlin")
                            )
                        ), Collections.singletonList(Languages("english", "middle"))
                    )
                )
            } else {
                response.onError(Throwable("An unhandled error occurred while consulting information"))
            }
        })
    }


    private var mRepository: ResumeRepository? = null
    private var mUseCase: ExtractResumeInformationUseCase? = null
    private var mPresenter: HomePresenter? = null


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mRepository = Mockito.spy<ResumeRepository>(ResumeRepository(mMockDataSource))
        mUseCase = Mockito.spy<ExtractResumeInformationUseCase>(ExtractResumeInformationUseCase(mRepository!!))
        mPresenter = Mockito.spy<HomePresenter>(HomePresenter(mUseCase!!))
        mPresenter!!.attachView(mMockView, mMockLifecycle)
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }


    @Test
    fun testANotSuccessInternetConnection() {
        mPresenter!!.getResumeInfo(false)
        verify(mMockView).onNoInternetAvailable()
    }

    @Test
    fun testBSuccessInternetConnection() {
        mEnabledSuccessResponse = true
        mPresenter!!.getResumeInfo(true)
        verify(mUseCase)!!.invoke(anyString(), any())
    }

    @Test
    fun testCSuccessResumeResponse() {
        mEnabledSuccessResponse = true
        mPresenter!!.getResumeInfo(true)
        verify(mMockView).onSuccessInfoBasicsLoaded(any())
    }

    @Test
    fun testDSuccessHandledLinkedIn() {
        mEnabledSuccessResponse = true
        mPresenter!!.getResumeInfo(true)
        verify(mMockView).onSuccessInfoBasicsLoaded(any())
        Assert.assertNotNull(mPresenter!!.handleGitHubUrl())
    }

    @Test
    fun testESuccessHandledGitHub() {
        mEnabledSuccessResponse = true
        mPresenter!!.getResumeInfo(true)
        verify(mMockView).onSuccessInfoBasicsLoaded(any())
        Assert.assertNotNull(mPresenter!!.handleLinkedinUrl())
    }


    @Test
    fun testFNotSuccesResumeResponse() {
        mEnabledSuccessResponse = false
        mPresenter!!.getResumeInfo(true)
        verify(mMockView).onGeneralError(any())
    }

}