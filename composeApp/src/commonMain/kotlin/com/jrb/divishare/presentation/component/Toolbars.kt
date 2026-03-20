package com.jrb.divishare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import divishare.composeapp.generated.resources.Res
import divishare.composeapp.generated.resources.add_project_button_desc
import divishare.composeapp.generated.resources.back_desc
import divishare.composeapp.generated.resources.ic_add_project
import divishare.composeapp.generated.resources.ic_logo
import divishare.composeapp.generated.resources.ic_users
import divishare.composeapp.generated.resources.log_in
import divishare.composeapp.generated.resources.logo_header_desc
import divishare.composeapp.generated.resources.user_profile_desc
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ToolBar(
    onAddNewProject: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painter = painterResource(Res.drawable.ic_add_project),
            contentDescription = stringResource(Res.string.add_project_button_desc),
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable { onAddNewProject() }
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(Res.drawable.ic_logo),
            contentDescription = stringResource(Res.string.logo_header_desc),
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(Res.drawable.ic_users),
            contentDescription = stringResource(Res.string.user_profile_desc),
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable { onProfileClick() }
        )
        Spacer(modifier = Modifier.width(20.dp))
    }
}

@Composable
fun LoginToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = painterResource(Res.drawable.ic_logo),
                contentDescription = stringResource(Res.string.logo_header_desc),
            )
            Text(
                text = stringResource(Res.string.log_in),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackToolbar(
    title: String,
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 60.dp),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(Res.string.back_desc)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}