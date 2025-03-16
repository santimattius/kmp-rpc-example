package com.santimattius.kmp.server

import com.santimattius.kmp.server.bus.EventBus
import com.santimattius.kmp.server.bus.LayoutChanged
import com.santimattius.kmp.shared.Layout
import com.santimattius.kmp.shared.LayoutServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class ServerLayoutService(
    override val coroutineContext: CoroutineContext
) : LayoutServices {

    private val eventBus = EventBus.get()

    override suspend fun subscribeToLayout(): Flow<Layout> {
        return eventBus.events
            .filterIsInstance<LayoutChanged>()
            .map { it.layout }
    }
}