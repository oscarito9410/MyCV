package com.booleansystems.mycv.ui.home.view

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.booleansystems.codechallenge.base.BaseActivity
import com.booleansystems.codechallenge.utils.extentions.isConnected
import com.booleansystems.codechallenge.utils.extentions.isPortrait
import com.booleansystems.codechallenge.utils.extentions.openUrlAction
import com.booleansystems.mycv.R
import com.booleansystems.mycv.ui.home.contract.HomeContract
import com.booleansystems.mycv.ui.home.mapper.Basics
import com.booleansystems.mycv.ui.home.mapper.Skills
import com.booleansystems.mycv.ui.home.mapper.Work
import com.booleansystems.mycv.ui.home.presenter.HomePresenter
import com.booleansystems.mycv.ui.home.view.adapter.SkillsAdapter
import com.booleansystems.mycv.ui.home.view.adapter.WorkAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject


class HomeActivity : BaseActivity(), HomeContract.View {

    val mPresenter: HomePresenter  by inject()

    var mAdapterSkills: SkillsAdapter? = null

    var mAdapterWork: WorkAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mPresenter.attachView(this, lifecycle)
        mPresenter.getResumeInfo(this.isConnected)
        initListManager()
    }


    fun initListManager() {
        if (mAdapterSkills == null) mAdapterSkills = SkillsAdapter(this, emptyList<String>().toMutableList())
        if (mAdapterWork == null) mAdapterWork = WorkAdapter(this, emptyList<Work>().toMutableList())
        rv_skills.layoutManager =
            GridLayoutManager(this, if (isPortrait()) 4 else 2, GridLayoutManager.HORIZONTAL, false)
        rv_skills.adapter = mAdapterSkills
        rv_work_experience.layoutManager = LinearLayoutManager(this)
        rv_work_experience.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_work_experience.adapter = mAdapterWork
    }

    override fun onSuccessInfoBasicsLoaded(basics: Basics) {
        tv_profile_name.text = basics.name
        tv_profile_description.text = basics.summary
        Glide.with(this).load(basics.picture)
            .override(60, 60)
            .fitCenter()
            .thumbnail(0.5f)
            .into(iv_profile_image)
    }

    override fun onSuccessinfoSkillsLoaded(skills: Skills) {
        tv_profile_main_skill.text = skills.name
        mAdapterSkills!!.notifyDataSetChanged(skills.keywordswords!!)
    }

    override fun onSuccessInfoWorkLoaded(workItems: List<Work>) {
        mAdapterWork!!.notifyDataSetChanged(workItems)
        runSlideAnimation()
    }


    fun runSlideAnimation() {
        cl_container_info.visibility = VISIBLE
        val anim = AnimationUtils.loadAnimation(this, R.anim.push_left_in)
        cl_container_info.startAnimation(anim)
    }

    fun onWebSiteClick(v: View) {
        openUrlAction(mPresenter.handlerWebSiteUrl())
    }

    fun onLinkedinClick(v: View) {
        openUrlAction(mPresenter.handleLinkedinUrl())
    }

    fun onGitHubClick(v: View) {
        openUrlAction(mPresenter.handleGitHubUrl())
    }


    override fun showProgressDialog() {
        lottie_loading.visibility = VISIBLE
    }

    override fun hideProgressDialog() {
        lottie_loading.visibility = GONE
    }

    override fun onNoInternetAvailable() {
        showSnackBar(sv_container_parent, R.string.error_no_internet_connection_found)
    }

    override fun onHttpError() {
        showSnackBar(sv_container_parent, R.string.error_http_connection)
    }

    override fun onTimeoutError() {
        showSnackBar(sv_container_parent, R.string.error_timeout_connection)
    }

    override fun onGeneralError(message: String) {
        showSnackBar(sv_container_parent, message)
    }
}
