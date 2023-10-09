package com.mnhyim.home.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mnhyim.domain.model.articles.ArticleModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCardItem(
    item: ArticleModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick(item.url) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier,
    ) {

        Text(
            text = item.title,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(8.dp)
        )
//        Text(
//            text = "${item.author} [${item.source}]",
//            style = MaterialTheme.typography.bodySmall,
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f))
//                .padding(8.dp, 4.dp)
//        )
        Text(
            text = item.publishedAt,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f))
                .padding(8.dp, 4.dp)
        )
        Text(
            text = item.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(12.dp)
        )
    }
}