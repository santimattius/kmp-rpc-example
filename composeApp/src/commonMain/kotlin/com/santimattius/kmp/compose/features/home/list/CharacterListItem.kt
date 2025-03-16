package com.santimattius.kmp.compose.features.home.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.santimattius.kmp.compose.core.ui.components.NetworkImage
import com.santimattius.kmp.shared.Character

private const val IMAGE_ASPECT_RATIO = 0.67f

@Composable
fun CharacterListItem(
    modifier: Modifier = Modifier,
    item: Character,
) {

    ListItem(
        modifier = modifier.wrapContentHeight(Alignment.Top),
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.background),
        leadingContent = {
            NetworkImage(
                imageUrl = item.image,
                contentDescription = item.description,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(100.dp)
                    .background(Color.LightGray.copy(0.5f), RoundedCornerShape(size = 8.dp))
                    .aspectRatio(ratio = IMAGE_ASPECT_RATIO),
            )
        },
        headlineContent = {
            Text(
                text = item.name,
            )
        },
        supportingContent = {
            Text(
                text = item.description,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
            )
        }
    )

}