package com.myfawwaz.android.jawa.widget.presentation.diary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.myfawwaz.android.jawa.widget.domain.model.DiaryEntry
import com.myfawwaz.app.mybrain.util.date.DateDay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.DiaryEntryItem(
    modifier: Modifier = Modifier,
    entry: DiaryEntry,
    onClick: (DiaryEntry) -> Unit
) {
    Card(
        modifier = modifier
            .animateItemPlacement(),
        shape = RoundedCornerShape(20.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .clickable { onClick(entry) }
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painterResource(entry.mood.icon),
                    stringResource(entry.mood.title),
                    tint = entry.mood.color,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    entry.title,
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (entry.content.isNotBlank()){
                Spacer(Modifier.height(8.dp))
                Text(
                    entry.content,
                    style = MaterialTheme.typography.body2,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = entry.createdDate.DateDay(),  //fullDate(),
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Thin),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}