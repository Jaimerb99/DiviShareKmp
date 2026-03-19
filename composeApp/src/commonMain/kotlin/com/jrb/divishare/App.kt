package com.jrb.divishare

import androidx.compose.runtime.Composable
import com.jrb.divishare.presentation.navigation.AppNavigation
import com.jrb.divishare.presentation.theme.DiviShareTheme

@Composable
fun App() {
    // Ya no inyectamos Koin aquí arriba.
    // Lo haremos pantalla por pantalla cuando haga falta.
    DiviShareTheme {
        AppNavigation()
    }
}