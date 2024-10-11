package io.ssttkkl.maib

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cheonjaeung.compose.grid.SimpleGridCells
import com.cheonjaeung.compose.grid.VerticalGrid
import io.ssttkkl.maib.models.MaiRecord
import io.ssttkkl.maib.models.QueryPlayerResp
import io.ssttkkl.maib.models.repo.PublicMaiProberRepo
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MaiBestGenerator() {
    var username by remember { mutableStateOf("ssttkkl") }
    var data by remember { mutableStateOf<QueryPlayerResp?>(null) }
    val coroutineContext = rememberCoroutineScope()

    Column(Modifier.fillMaxWidth()) {
        Row {
            TextField(username, { username = it }, label = { Text("username") })

            Button(onClick = {
                coroutineContext.launch {
                    runCatching {
                        data = PublicMaiProberRepo.queryPlayerRecords(username)
                    }.onFailure {
                        it.printStackTrace()
                    }
                }
            }) {
                Text("Click")
            }
        }
        data?.let { MaiBest(it) }
    }
}

@Composable
@Preview
fun MaiBest(resp: QueryPlayerResp) {
    val horizontalScrollState = rememberScrollState()

    Box(Modifier.horizontalScroll(horizontalScrollState)) {
        Column(Modifier.width(800.dp)) {
            MaiBestRecordGrid(resp.charts.dx)
            MaiBestRecordGrid(resp.charts.sd)
        }
    }
}

@Composable
fun MaiBestRecordGrid(records: List<MaiRecord>, itemPerRow: Int = 5) {
    VerticalGrid(SimpleGridCells.Fixed(itemPerRow), Modifier.fillMaxWidth()) {
        for ((index, record) in records.withIndex()) {
            MaiBestRecord(index, record)
        }
    }
}

@Composable
fun MaiBestRecord(index: Int, record: MaiRecord) {
    Card(Modifier.height(240.dp)) {
        Row {
            Box(Modifier.size(72.dp))
            Text(record.title)
            Text(record.rate)
        }
    }
}