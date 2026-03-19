package com.jrb.divishare.di

import com.jrb.divishare.data.datasource.local.db.AppDatabase
import com.jrb.divishare.db.DbFactoryIos
import org.koin.dsl.module

val dbModuleiOS = module {

    // Ensure that the database is initialized before using it
    single<AppDatabase> {
        DbFactoryIos.initialize()
    }

}