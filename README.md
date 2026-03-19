# 💸 DiviShare - KMP Expense Manager

*[🇪🇸 Leer en Español abajo](#-divishare---gestor-de-gastos-kmp-español)*

DiviShare is a modern, cross-platform application for group expense tracking and splitting (similar to Tricount or Splitwise). It is built 100% in Kotlin using **Kotlin Multiplatform (KMP)** to share all business logic, and **Compose Multiplatform** for a unified user interface across Android, iOS, and Desktop.

## ✨ Key Features

* **True Multiplatform:** A single codebase (Logic + UI) running natively on Android, iOS, and JVM (Desktop).
* **Offline-First:** Works seamlessly without an internet connection using local caching with Room KMP.
* **Cloud Synchronization:** Integrated with Supabase REST API (PostgreSQL) to keep expenses synced across all group members.
* **Reactive Architecture:** Built on the MVI (Model-View-Intent) pattern powered by StateFlow and Channels.

## 🛠️ Tech Stack

This project leverages the most modern libraries in the Kotlin Multiplatform ecosystem:

* **UI:** Jetpack Compose & Compose Multiplatform
* **Architecture:** Clean Architecture + MVI
* **Navigation:** JetBrains navigation-compose (Type-safe routing)
* **Dependency Injection:** Koin
* **Network & API:** Ktor Client + kotlinx.serialization
* **Local Database:** Room KMP (SQLite)
* **Backend:** Supabase

## 🏗️ Project Architecture

The project follows Clean Architecture principles, structured into the following layers within the commonMain source set:

* **domain/**: Data models, Result wrappers, and repository contracts. Independent of external libraries.
* **data/**: 
    * remote/: Ktor client and Supabase REST API calls.
    * local/: Room entities, DAOs, and DB Factories (offline support).
    * repository/: Repository implementations unifying local cache and network.
* **presentation/**: Compose UI components, BaseViewModel, BaseContract, and unidirectional data flow (MVI).
* **di/**: Koin modules for dependency injection.

## 🚀 How to run the project

1. **Clone the repository:**
    git clone https://github.com/YOUR_USERNAME/DiviShare.git

2. **Open the project in Android Studio** (Latest stable or Ladybug version recommended).
3. **Sync Gradle** to download multiplatform dependencies.
4. **Run the app:**
   * Desktop (Recommended for rapid UI dev): Select the Gradle task `composeApp -> Tasks -> compose desktop -> desktopRun`.
   * Android: Select the `composeApp` module and an Android emulator/device, then hit Run.
   * iOS: Select the `iosApp` module and an iPhone simulator (requires macOS and Xcode).

## 🚧 Project Status

Currently under active development.
- [x] Initial Setup (KMP, Theme, Navigation).
- [x] Local DB (Room) and Remote DB (Supabase) configuration.
- [x] Base MVI Architecture and DI (Koin) setup.
- [ ] UI
- [ ] Deployment and testing

---

# 💸 DiviShare - Gestor de Gastos KMP (Español)

DiviShare es una aplicación moderna y multiplataforma para la gestión y división de gastos en grupo (al estilo Tricount o Splitwise). Está desarrollada 100% en Kotlin utilizando **Kotlin Multiplatform (KMP)** para compartir toda la lógica de negocio y **Compose Multiplatform** para una interfaz de usuario unificada en Android, iOS y Desktop.

## ✨ Características Principales

* **Multiplataforma Real:** Un solo código base (lógica y UI) que corre en Android, iOS y JVM (Desktop).
* **Offline-First:** Funciona sin conexión a internet gracias a la caché local con Room KMP.
* **Sincronización en la Nube:** Integración con la API REST de Supabase (PostgreSQL) para mantener los gastos sincronizados entre todos los miembros del grupo.
* **Arquitectura Reactiva:** Basada en el patrón MVI (Model-View-Intent) apoyado por StateFlow y Channels.

## 🛠️ Tech Stack

El proyecto utiliza las librerías más modernas del ecosistema Kotlin Multiplatform:

* **UI:** Jetpack Compose & Compose Multiplatform
* **Arquitectura:** Clean Architecture + MVI
* **Navegación:** navigation-compose de JetBrains (Type-safe routing)
* **Inyección de Dependencias:** Koin
* **Red & API:** Ktor Client + kotlinx.serialization
* **Base de Datos Local:** Room KMP (SQLite)
* **Backend:** Supabase

## 🏗️ Arquitectura del Proyecto

El proyecto sigue los principios de Clean Architecture estructurado en las siguientes capas:

* **domain/**: Modelos de datos, Result wrappers y los contratos de los repositorios. Es independiente de cualquier librería externa.
* **data/**: 
    * remote/: Cliente Ktor y llamadas a la REST API de Supabase.
    * local/: Entidades de Room, DAOs y DB Factories (soporte offline).
    * repository/: Implementación de los repositorios unificando caché local y red.
* **presentation/**: Componentes de Compose, BaseViewModel, BaseContract y la gestión unidireccional del estado (MVI).
* **di/**: Módulos de Koin para la inyección de dependencias.

## 🚀 Cómo ejecutar el proyecto

1. **Clona el repositorio:**
    git clone https://github.com/TU_USUARIO/DiviShare.git

2. **Abre el proyecto en Android Studio** (Se recomienda la última versión estable o Ladybug).
3. **Sincroniza Gradle** para descargar las dependencias multiplataforma.
4. **Ejecuta la app:**
   * Desktop (Recomendado para desarrollo rápido): Selecciona la tarea Gradle `composeApp -> Tasks -> compose desktop -> desktopRun`.
   * Android: Selecciona el módulo `composeApp` y un emulador/dispositivo Android y dale a Run.
   * iOS: Selecciona el módulo `iosApp` y un simulador de iPhone (requiere macOS y Xcode instalado).

---
