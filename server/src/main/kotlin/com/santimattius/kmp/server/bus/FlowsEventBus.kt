package com.santimattius.kmp.server.bus

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll

internal object FlowsEventBus : EventBus {

    private val _eventFlow = MutableSharedFlow<Event>()

    override val events: Flow<Event>
        get() = _eventFlow

    override suspend fun emit(event: Event) {
        _eventFlow.emit(event)
    }

    override suspend fun emit(event: List<Event>) {
        _eventFlow.emitAll(event.asFlow())
    }
}