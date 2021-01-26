package com.example.melanomaclassifier.ui.splashscreen.view

import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.ui.base.view.BaseActivity
import com.example.melanomaclassifier.ui.login.view.LoginActivity
import com.example.melanomaclassifier.ui.main.view.MainActivity
import com.example.melanomaclassifier.ui.splashscreen.interactor.SplashScreenMvpInteractor
import com.example.melanomaclassifier.ui.splashscreen.presenter.SplashScreenPresenter
import com.github.ybq.android.spinkit.style.FoldingCube
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashScreenActivity : BaseActivity(), SplashScreenMvpView {

    @Inject
    lateinit var presenter: SplashScreenPresenter<SplashScreenMvpView, SplashScreenMvpInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        presenter.onAttach(this)

        val foldingCube = FoldingCube()
        progress.setIndeterminateDrawable(foldingCube)

        Handler().postDelayed({
            presenter.decidePage()
        }, 1000)
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun openMainPage() {
        startActivity<MainActivity>()
        finish()
    }

    override fun openLoginPage() {
        startActivity<LoginActivity>()
        finish()
    }
}