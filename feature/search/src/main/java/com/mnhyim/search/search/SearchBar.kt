package com.mnhyim.search.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String,
    checked: Boolean,
    onValueChange: (String) -> Unit,
    onChecked: (Boolean) -> Unit
) {
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            label = { Text("Search") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedLeadingIconColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = null)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = checked,
                onCheckedChange = onChecked,
                modifier = Modifier.scale(0.75f)
            )
            Text(
                text = "Cari berdasarkan \"Sources\"?",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}