package com.santimattius.kmp.server.bus

import kotlinx.coroutines.flow.Flow


internal interface EventBus {

    val events: Flow<Event>

    suspend fun emit(event: Event)

    suspend fun emit(event: List<Event>)

    companion object {
        fun get(): EventBus = FlowsEventBus
    }
}