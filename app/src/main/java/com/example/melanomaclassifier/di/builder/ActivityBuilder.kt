package com.example.melanomaclassifier.di.builder

import com.example.melanomaclassifier.ui.check_up.CheckUpActivityModule
import com.example.melanomaclassifier.ui.check_up.view.CheckUpActivity
import com.example.melanomaclassifier.ui.detail_periksa.DetailPeriksaActivityModule
import com.example.melanomaclassifier.ui.detail_periksa.view.DetailPeriksaActivity
import com.example.melanomaclassifier.ui.dokter_picker.DokterPickerActivityModule
import com.example.melanomaclassifier.ui.dokter_picker.ui.DokterPickerActivity
import com.example.melanomaclassifier.ui.edit_profile.EditProfileActivityModule
import com.example.melanomaclassifier.ui.edit_profile.view.EditProfileActivity
import com.example.melanomaclassifier.ui.login.LoginActivityModule
import com.example.melanomaclassifier.ui.login.view.LoginActivity
import com.example.melanomaclassifier.ui.main.MainActivityModule
import com.example.melanomaclassifier.ui.main.view.MainActivity
import com.example.melanomaclassifier.ui.main.view.fragment.FragmentsProvider
import com.example.melanomaclassifier.ui.main.view.fragment.home.HomeFragmentModule
import com.example.melanomaclassifier.ui.main.view.fragment.home.view.HomeFragment
import com.example.melanomaclassifier.ui.rate_dokter.RateDokterActivityModule
import com.example.melanomaclassifier.ui.rate_dokter.view.RateDokterActivity
import com.example.melanomaclassifier.ui.register.RegisterActivityModule
import com.example.melanomaclassifier.ui.register.view.RegisterActivity
import com.example.melanomaclassifier.ui.splashscreen.SplashScreenActivityModule
import com.example.melanomaclassifier.ui.splashscreen.view.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (FragmentsProvider::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(RegisterActivityModule::class)])
    abstract fun bindRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector(modules = [(CheckUpActivityModule::class)])
    abstract fun bindCheckUpActivity(): CheckUpActivity

    @ContributesAndroidInjector(modules = [(SplashScreenActivityModule::class)])
    abstract fun bindSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector(modules = [(DokterPickerActivityModule::class)])
    abstract fun bindDokterPickerActivity(): DokterPickerActivity

    @ContributesAndroidInjector(modules = [(DetailPeriksaActivityModule::class)])
    abstract fun bindDetailPeriksaActivity(): DetailPeriksaActivity

    @ContributesAndroidInjector(modules = [(EditProfileActivityModule::class)])
    abstract fun bindEditProfileActivity(): EditProfileActivity

    @ContributesAndroidInjector(modules = [(RateDokterActivityModule::class)])
    abstract fun bindRateDokterActivity(): RateDokterActivity
}