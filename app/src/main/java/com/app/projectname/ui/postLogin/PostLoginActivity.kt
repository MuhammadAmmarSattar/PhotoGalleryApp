package com.app.projectname.ui.postLogin

//import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.app.projectname.R
import com.app.projectname.ui.base.BaseAuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostLoginActivity : BaseAuthenticationActivity() , View.OnClickListener{

    private val mainViewModel: MainViewModel by viewModels()

    override fun baseOnCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_post_login)
        subscribeUiEvents(mainViewModel)

    }

//        runWithPermissions(Manifest.permission.CAMERA) {
//            //todo
//        }
    override fun onClick(v: View?) {
//        when(v?.id){
//        R.id.btn_eng->{
//            setLanguage(Locale.ENGLISH)
//        }
//            R.id.btn_ar->{
//                setLanguage(Locale("ar"))
//            }
//        }
    }

}