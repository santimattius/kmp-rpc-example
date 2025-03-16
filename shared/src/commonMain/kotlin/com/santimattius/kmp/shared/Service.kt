package com.santimattius.kmp.shared

import kotlinx.coroutines.flow.Flow
import kotlinx.rpc.RemoteService
import kotlinx.rpc.annotations.Rpc

@Rpc
interface LayoutServices : RemoteService {
    suspend fun subscribeToLayout(): Flow<Layout>
}