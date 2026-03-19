package com.jrb.divishare.di

import com.jrb.divishare.data.datasource.local.db.AppDatabase
import com.jrb.divishare.db.DbFactoryJvm
import org.koin.dsl.module

val dbModuleJvm = module {

    // Ensure that the database is initialized before using it
    single<AppDatabase> {
        DbFactoryJvm.initialize()
    }

}