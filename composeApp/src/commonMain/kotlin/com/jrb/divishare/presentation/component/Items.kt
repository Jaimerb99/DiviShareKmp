package com.jrb.divishare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jrb.divishare.presentation.theme.DiviShareTheme
import divishare.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProjectItem(
    projectName: String,
    icon: DrawableResource,
    onProjectClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onProjectClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp))
                .size(55.dp)
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null, // Limpio
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }

        Text(
            text = projectName,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp)
        )

        Image(
            painter = painterResource(Res.drawable.ic_arrow),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
        )
    }
}

@Composable
fun EmptyScreen(
    title: String,
    description: String,
    image: DrawableResource
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 40.dp)
        )
        Spacer(modifier = Modifier.weight(4f))
    }
}

// =========================================================================
// PREVIEWS
// =========================================================================

@Preview(showBackground = true)
@Composable
private fun ItemsPreview() {
    DiviShareTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("ProjectItem", style = MaterialTheme.typography.labelSmall)
            ProjectItem(
                projectName = "Viaje a Japón",
                icon = Res.drawable.ic_home,
                onProjectClick = {}
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text("Empty State", style = MaterialTheme.typography.labelSmall)
            Box(modifier = Modifier.height(350.dp).fillMaxWidth()) {
                EmptyScreen(
                    title = stringResource(Res.string.empty_projects_title),
                    description = stringResource(Res.string.empty_projects_desc),
                    image = Res.drawable.no_results_ilustration
                )
            }
        }
    }
}