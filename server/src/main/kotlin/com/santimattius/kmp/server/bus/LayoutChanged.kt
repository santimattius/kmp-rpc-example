package com.santimattius.kmp.server.bus

import com.santimattius.kmp.shared.Layout
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class LayoutChanged(
    val layout: Layout
) : Event {

    @OptIn(ExperimentalUuidApi::class)
    override val id: String
        get() = Uuid.random().toString()
}