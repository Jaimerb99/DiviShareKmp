package com.jrb.divishare.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jrb.divishare.presentation.theme.DiviShareTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = 15.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun TextButtonBox(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    fontSize: Int = 20,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = enabled, onClick = onClick)
            .background(
                color = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = fontSize.sp,
            color = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// =========================================================================
// PREVIEWS
// =========================================================================

@Preview(showBackground = true)
@Composable
private fun ButtonsPreview() {
    DiviShareTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("TextButton", style = MaterialTheme.typography.labelMedium)
            TextButton(
                text = "Primary Button",
                onClick = {}
            )

            Text("TextButtonBox (Enabled)", style = MaterialTheme.typography.labelMedium)
            TextButtonBox(
                text = "Continue",
                enabled = true,
                onClick = {}
            )

            Text("TextButtonBox (Disabled)", style = MaterialTheme.typography.labelMedium)
            TextButtonBox(
                text = "Not ready yet",
                enabled = false,
                onClick = {}
            )
        }
    }
}