package com.jrb.divishare.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jrb.divishare.presentation.theme.DiviShareTheme
import com.jrb.divishare.utils.isValidEmail
import divishare.composeapp.generated.resources.Res
import divishare.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    inverted: Boolean = false
) {
    val focusManager = LocalFocusManager.current
    val isError = value.isNotEmpty() && !isValidEmail(value)

    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        isError = isError,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = if (inverted) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = if (inverted) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surfaceVariant,
            errorContainerColor = if (inverted) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surfaceVariant,
            focusedTextColor = if (inverted) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTextColor = if (inverted) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        ),
        placeholder = { Text(stringResource(Res.string.email_placeholder)) },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    error: Boolean = false
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        isError = error,
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = if (passwordVisibility) vectorResource(Res.drawable.ic_open_eye)
                    else vectorResource(Res.drawable.ic_closed_eye),
                    contentDescription = stringResource(Res.string.password_visibility_toggle_desc),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = { Text(stringResource(Res.string.password_placeholder)) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            errorContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        ),
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}

@Composable
fun PhoneTextField(
    prefixValue: String,
    phoneValue: String,
    onPrefixChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier.fillMaxWidth().height(52.dp)
    ) {
        TextField(
            value = prefixValue,
            onValueChange = { newPrefix ->
                val filtered = newPrefix.filter { it.isDigit() || it == '+' }
                if (filtered.length <= 4) {
                    onPrefixChange(if (filtered.startsWith("+")) filtered else "+$filtered")
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = { Text(stringResource(Res.string.phone_prefix_placeholder)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
            modifier = Modifier.width(70.dp).height(52.dp)
        )

        TextField(
            value = phoneValue,
            onValueChange = { newValue ->
                val digitsOnly = newValue.filter { it.isDigit() }
                if (digitsOnly.length <= 15) {
                    val formatted = digitsOnly.chunked(3).joinToString(" ")
                    onPhoneNumberChange(formatted)
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = { Text(stringResource(Res.string.phone_number_placeholder)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp).height(52.dp)
        )
    }
}

@Composable
fun DateBox(
    dateText: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onClick() }
            .height(52.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        val isEmpty = dateText.isEmpty()
        Text(
            text = dateText.ifEmpty { stringResource(Res.string.select_date) },
            style = TextStyle(
                color = if (isEmpty) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                else MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun AmountTextField(
    value: String,
    onValueChange: (String) -> Unit,
    currencySymbol: String = "€"
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = { newValue ->
            // Solo permitimos números y un punto o coma decimal
            if (newValue.matches(Regex("^\\d*[.,]?\\d{0,2}\$"))) {
                onValueChange(newValue.replace(',', '.'))
            }
        },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(
                text = "0.00",
                style = TextStyle(fontSize = 32.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            )
        },
        leadingIcon = {
            Text(
                text = currencySymbol,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}

// =========================================================================
// PREVIEWS
// =========================================================================

@Preview(showBackground = true)
@Composable
private fun TextFieldsPreview() {
    DiviShareTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("EmailTextField", style = MaterialTheme.typography.labelMedium)
            EmailTextField(value = "", onValueChange = {})

            Text("PasswordTextField", style = MaterialTheme.typography.labelMedium)
            PasswordTextField(value = "mypassword", onValueChange = {})

            Text("NameTextField", style = MaterialTheme.typography.labelMedium)
            NameTextField(value = "", onValueChange = {}, placeholder = "Full Name")

            Text("PhoneTextField", style = MaterialTheme.typography.labelMedium)
            PhoneTextField(prefixValue = "+34", phoneValue = "", onPrefixChange = {}, onPhoneNumberChange = {})

            Text("DateBox", style = MaterialTheme.typography.labelMedium)
            DateBox(dateText = "", onClick = {})

            Text("AmountTextField (Nuevo)", style = MaterialTheme.typography.labelMedium)
            AmountTextField(value = "", onValueChange = {})
        }
    }
}