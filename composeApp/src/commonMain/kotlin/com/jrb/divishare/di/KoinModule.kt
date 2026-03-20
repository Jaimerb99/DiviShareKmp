package com.jrb.divishare.di

import com.jrb.divishare.data.datasource.local.preferences.PreferencesLocalDataSource
import com.jrb.divishare.data.remote.SupabaseClient
import com.jrb.divishare.data.repo.ExpenseRepositoryImpl
import com.jrb.divishare.data.repo.GroupRepositoryImpl
import com.jrb.divishare.data.repo.UserRepositoryImpl
import com.jrb.divishare.domain.repo.ExpenseRepository
import com.jrb.divishare.domain.repo.GroupRepository
import com.jrb.divishare.domain.repo.UserRepository
import com.jrb.divishare.domain.usecase.GetInitialDestinationUseCase
import com.jrb.divishare.domain.usecase.auth.CheckOnboardingStatusUseCase
import com.jrb.divishare.domain.usecase.auth.GetUserIdUseCase
import com.jrb.divishare.domain.usecase.auth.LoginUseCase
import com.jrb.divishare.domain.usecase.auth.SendRecoveryEmailUseCase
import com.jrb.divishare.domain.usecase.auth.SignUpUseCase
import com.jrb.divishare.domain.usecase.groups.GetMyGroupsUseCase
import com.jrb.divishare.presentation.screens.forgotpassword.ForgotPasswordViewModel
import com.jrb.divishare.presentation.screens.login.LoginViewModel
import com.jrb.divishare.presentation.screens.onboarding.OnboardingViewModel
import com.jrb.divishare.presentation.screens.registration.RegistrationViewModel
import com.jrb.divishare.presentation.screens.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule: Module = module {

    // 1. DATA SOURCES & CLIENTS
    single { SupabaseClient.httpClient }
    single { PreferencesLocalDataSource(get()) }

    // 2. REPOSITORIOS (Data Layer)
    single<GroupRepository> { GroupRepositoryImpl() }
    single<ExpenseRepository> { ExpenseRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl() }

    // 3. CASOS DE USO (Domain Layer)
    // Usamos 'factory' porque no necesitan mantener estado, se crean cuando se piden
    factory { GetInitialDestinationUseCase(get()) }
    factory { CheckOnboardingStatusUseCase(get()) }
    factory { LoginUseCase(get(), get()) }
    factory { SignUpUseCase(get(), get()) }
    factory { SendRecoveryEmailUseCase(get()) }
    factory { GetUserIdUseCase(get()) }
    factory { GetMyGroupsUseCase(get()) }

    // 4. VIEWMODELS (Presentation Layer)
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { OnboardingViewModel(get()) }
}

