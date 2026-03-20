package com.jrb.divishare.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jrb.divishare.presentation.theme.DiviShareTheme
import com.jrb.divishare.ui.components.EmptyScreen
import com.jrb.divishare.ui.components.ProjectItem
import com.jrb.divishare.ui.components.ToolBar
import divishare.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToProject: (Int) -> Unit,
    onNavigateToNewProject: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val state by viewModel.stateStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                is HomeUiEffect.NavigateToProject -> onNavigateToProject(effect.groupId)
                is HomeUiEffect.NavigateToNewProject -> onNavigateToNewProject()
                is HomeUiEffect.NavigateToProfile -> onNavigateToProfile()
                else -> {}
            }
        }
    }

    HomeContent(
        state = state,
        onIntent = { viewModel.sendIntent(it) }
    )
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onIntent: (HomeUiIntent) -> Unit
) {
    Scaffold(
        topBar = {
            ToolBar(
                onAddNewProject = { onIntent(HomeUiIntent.ClickAddProject) },
                onProfileClick = { onIntent(HomeUiIntent.ClickProfile) }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Contenedor principal (Lista o Pantalla Vacía)
            Box(modifier = Modifier.weight(1f)) {
                when {
                    state.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    state.groups.isEmpty() -> {
                        EmptyScreen(
                            title = stringResource(Res.string.no_shared_expenses_title),
                            description = stringResource(Res.string.no_shared_expenses_desc),
                            image = Res.drawable.ic_empty_inbox // Aquí irían tus suricatos
                        )
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(state.groups) { group ->
                                ProjectItem(
                                    projectName = group.name,
                                    icon = group.icon,
                                    onProjectClick = { onIntent(HomeUiIntent.ClickGroup(group.id)) }
                                )
                            }
                        }
                    }
                }
            }

            // Footer: Texto informativo que solo aparece si hay elementos
            if (state.groups.isNotEmpty()) {
                Text(
                    text = stringResource(Res.string.no_shared_expenses_desc),
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 20.dp),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeEmptyPreview() {
    DiviShareTheme {
        HomeContent(state = HomeUiState(), onIntent = {})
    }
}