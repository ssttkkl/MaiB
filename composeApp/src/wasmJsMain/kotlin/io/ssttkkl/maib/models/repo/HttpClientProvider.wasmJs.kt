package io.ssttkkl.maib.models.repo

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.js.Js

actual val HttpClientProvider.factory: HttpClientEngineFactory<*>
    get() = Js