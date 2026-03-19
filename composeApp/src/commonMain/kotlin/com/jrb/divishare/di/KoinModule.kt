package com.jrb.divishare.di

import com.jrb.divishare.data.remote.SupabaseClient
import com.jrb.divishare.data.repo.ExpenseRepositoryImpl
import com.jrb.divishare.data.repo.GroupRepositoryImpl
import com.jrb.divishare.domain.repo.ExpenseRepository
import com.jrb.divishare.domain.repo.GroupRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    // 1. RED (Nuestro cliente de Ktor configurado para Supabase)
    single { SupabaseClient.httpClient }

    // 2. REPOSITORIOS (La capa Data)
    // Usamos 'single' para que toda la app use la misma instancia del repositorio
    single<GroupRepository> { GroupRepositoryImpl() }
    single<ExpenseRepository> { ExpenseRepositoryImpl() }

    // 3. VIEWMODELS (La capa Presentation)
    // Aquí iremos añadiendo tus ViewModels usando viewModel { ... }
    // Por ejemplo, cuando creemos el de la pantalla principal:
    // viewModel { HomeViewModel(get()) }
}

/*val appModule: Module = module {

    // Provide MyTransactionDao
    single { get<AppDatabase>().transDao() }

    single<TransactionRepository> { TransactionRepositoryImpl(get()) }

    factory { TransactionGetAllUseCase(get()) }
    factory { TransactionUpsertUseCase(get()) }
    factory { TransactionDeleteUseCase(get()) }
    factory { TransactionSummaryUseCase(get()) }

    viewModel { CommonViewModel() }
    single<CommonContract> { get<CommonViewModel>() } // app-wide singleton

//    viewModel { TransactionViewModel(get(), get(), get()) }
//
//    factory<HomeContract> { get<TransactionViewModel>() } // while using no need koinViewModel<TransactionViewModel>() only use get()

    viewModel { TransactionViewModel(get(), get(), get(), get()) } bind HomeContract::class



}*/

