package io.ssttkkl.maib.models.repo

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual val HttpClientProvider.factory: HttpClientEngineFactory<*>
    get() = OkHttp