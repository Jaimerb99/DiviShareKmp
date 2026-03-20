package com.jrb.divishare.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/*import com.jrb.divishare.util.parseDate
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.WheelPickerDefaults*/

@Composable
fun DateDialog(
    showDatePicker: Boolean,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
   /* WheelDatePickerView(
        title = "Birth Day",
        doneLabel = "OK",
        modifier = Modifier.padding(top = 18.dp, bottom = 10.dp).fillMaxWidth(),
        showDatePicker = showDatePicker,
        titleStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        ),
        doneLabelStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
        ),
        selectorProperties = WheelPickerDefaults.selectorProperties(
            borderColor = Color.Transparent,
        ),
        dateTextColor = MaterialTheme.colorScheme.primary,
        rowCount = 5,
        height = 170.dp,
        dateTextStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
        ),
        onDoneClick = {
            onDateSelected(parseDate(it.toString()))
        },
        dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
        onDismiss = onDismiss
    )*/
}