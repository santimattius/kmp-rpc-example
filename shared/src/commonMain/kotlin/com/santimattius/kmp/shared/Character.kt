package com.santimattius.kmp.shared

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Long,
    val name: String,
    val image: String,
    val description: String,
)