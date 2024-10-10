package io.ssttkkl.maib

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.ssttkkl.maib.models.MaiRecordsResp
import io.ssttkkl.maib.models.QueryPlayerResp
import io.ssttkkl.maib.models.repo.MaiProberRepo
import io.ssttkkl.maib.models.repo.PublicMaiProberRepo
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        var username by remember { mutableStateOf("ssttkkl") }

        var data by remember { mutableStateOf<QueryPlayerResp?>(null) }
        var thr by remember { mutableStateOf<Throwable?>(null) }
        val coroutineContext = rememberCoroutineScope()
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(username, { username = it }, label = { Text("username") })

            Button(onClick = {
                coroutineContext.launch {
                    runCatching {
                        data = PublicMaiProberRepo.queryPlayerRecords(username)
                    }.onFailure {
                        thr = it
                        it.printStackTrace()
                    }
                }
            }) {
                Text("Click")
            }
            Text(data?.toString() ?: thr?.stackTraceToString() ?: "")
        }
    }
}