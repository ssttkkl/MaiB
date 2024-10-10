package io.ssttkkl.maib.models.repo

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

actual val HttpClientProvider.factory: HttpClientEngineFactory<*>
    get() = CIO