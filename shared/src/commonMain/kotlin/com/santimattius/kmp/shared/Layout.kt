package com.santimattius.kmp.shared

import kotlinx.serialization.Serializable

@Serializable
data class Layout(
    val type: LayoutType,
    val items: List<Character>
)


enum class LayoutType {
    List, Grid
}