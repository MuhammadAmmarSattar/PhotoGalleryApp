package com.daniyal.basicappimpl.ui.home

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.work.WorkInfo
import androidx.work.workDataOf
import com.bumptech.glide.RequestManager
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseAuthenticationActivity
import com.daniyal.basicappimpl.ui.home.viewmodels.MainViewModel
import com.daniyal.basicappimpl.utils.LocaleContainer
//import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseAuthenticationActivity() , View.OnClickListener{

    @Inject
    lateinit var glide: RequestManager

    private val mainViewModel: MainViewModel by viewModels()

    override fun baseOnCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        btn_eng.setOnClickListener(this)
        btn_ar.setOnClickListener(this)
        btn_wm.setOnClickListener (this)
        mainViewModel.outputWorkInfos.observe(this, workInfosObserver())
        subscribeUiEvents(mainViewModel)
    }

//        runWithPermissions(Manifest.permission.CAMERA) {
//            //todo
//        }
    override fun onClick(v: View?) {
        when(v?.id){
        R.id.btn_eng->{
            setLanguage(Locale.ENGLISH)
        }
            R.id.btn_ar->{
                setLanguage(Locale("ar"))
            }
            R.id.btn_wm->{
                mainViewModel.applyCompression()
            }
        }
    }

    private fun workInfosObserver():androidx.lifecycle.Observer<List<WorkInfo>>{
        return androidx.lifecycle.Observer{listOfWorkInfo->
            if(listOfWorkInfo.isNullOrEmpty()){return@Observer}
                if(listOfWorkInfo[0].state.isFinished){
                        mainViewModel.showToast("Work is Finished")
                }else{
                    mainViewModel.showToast("Work In Progress")
                }
        }

    }
}