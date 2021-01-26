package com.example.melanomaclassifier.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.ui.base.view.BaseActivity
import com.example.melanomaclassifier.ui.check_up.view.CheckUpActivity
import com.example.melanomaclassifier.ui.main.interactor.MainMvpInteractor
import com.example.melanomaclassifier.ui.main.presenter.MainMvpPresenter
import com.example.melanomaclassifier.ui.main.view.fragment.history.view.HistoryFragment
import com.example.melanomaclassifier.ui.main.view.fragment.home.view.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView, HasSupportFragmentInjector {

    @Inject
    lateinit var presenter: MainMvpPresenter<MainMvpView, MainMvpInteractor>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val fragment = HomeFragment.newInstance()
                    addFragment(fragment)
                    img_check_up.visibility = View.VISIBLE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    val fragment = HistoryFragment.newInstance()
                    addFragment(fragment)
                    img_check_up.visibility = View.GONE
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = HomeFragment.newInstance()
        addFragment(fragment)

        img_check_up.setOnClickListener {
            startActivity<CheckUpActivity>()
        }

    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}
    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector


}
