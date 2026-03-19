package com.jrb.divishare.utils

import org.jetbrains.compose.ui.tooling.preview.Preview



@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(
    name = "1. Light Mode",
    showBackground = true,
    backgroundColor = 0xFFF8FAFC
)
@Preview(
    name = "2. Dark Mode",
    showBackground = true,
    backgroundColor = 0xFF0F172A,
)
annotation class PreviewLightDarkWithBackground