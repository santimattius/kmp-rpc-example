package com.santimattius.kmp.compose.features.home.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.santimattius.kmp.compose.core.ui.components.NetworkImage
import com.santimattius.kmp.shared.Character

private const val IMAGE_ASPECT_RATIO = 0.67f

@Composable
fun CharacterGridItem(
    character: Character,
    modifier: Modifier = Modifier,
    onClick: (Character) -> Unit = {},
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .clickable { onClick(character) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        NetworkImage(
            imageUrl = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .aspectRatio(ratio = IMAGE_ASPECT_RATIO),
        )
    }
}