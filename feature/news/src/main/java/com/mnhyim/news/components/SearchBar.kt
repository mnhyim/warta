package com.mnhyim.news.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale

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
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = checked,
                onCheckedChange = onChecked,
                modifier = Modifier.scale(0.75f)
            )
            Text(text = "Cari berdasarkan 'Sources'?")
        }
    }
}