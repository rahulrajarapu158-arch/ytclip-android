package com.junkfood.seal.ui.page.downloadv2

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.junkfood.seal.util.FileUtil
import com.junkfood.seal.util.makeToast

@Composable
fun ClipDialog(
    onDismissRequest: () -> Unit,
    onDownloadClip: (url: String, startSec: Int, endSec: Int) -> Unit = { _, _, _ -> },
) {
    val context = LocalContext.current
    var url by remember { mutableStateOf("") }
    var fromMin by remember { mutableStateOf("0") }
    var fromSec by remember { mutableStateOf("0") }
    var toMin by remember { mutableStateOf("0") }
    var toSec by remember { mutableStateOf("0") }
    var error by remember { mutableStateOf(false) }

    fun onDone() {
        val startMin = fromMin.toIntOrNull() ?: 0
        val startSec = fromSec.toIntOrNull() ?: 0
        val endMin = toMin.toIntOrNull() ?: 0
        val endSec = toSec.toIntOrNull() ?: 0
        val startTime = startMin * 60 + startSec
        val endTime = endMin * 60 + endSec

        if (startTime < endTime && endTime > 0) {
            onDownloadClip(url, startTime, endTime)
            onDismissRequest()
        } else {
            error = true
        }
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Clip Video") },
        icon = { Icon(Icons.Outlined.ContentCut, null) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = url,
                    onValueChange = { url = it },
                    label = { Text("YouTube URL") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = error && url.isBlank(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Set start and end time",
                    style = MaterialTheme.typography.labelLarge,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("From", modifier = Modifier.width(40.dp))
                    OutlinedTextField(
                        value = fromMin,
                        onValueChange = { if (it.all { c -> c.isDigit() }) fromMin = it },
                        label = { Text("Min") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                    )
                    Text(":", modifier = Modifier.padding(horizontal = 4.dp))
                    OutlinedTextField(
                        value = fromSec,
                        onValueChange = { if (it.all { c -> c.isDigit() }) fromSec = it },
                        label = { Text("Sec") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("To", modifier = Modifier.width(40.dp))
                    OutlinedTextField(
                        value = toMin,
                        onValueChange = { if (it.all { c -> c.isDigit() }) toMin = it },
                        label = { Text("Min") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                    )
                    Text(":", modifier = Modifier.padding(horizontal = 4.dp))
                    OutlinedTextField(
                        value = toSec,
                        onValueChange = { if (it.all { c -> c.isDigit() }) toSec = it },
                        label = { Text("Sec") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                    )
                }
                if (error) {
                    Text(
                        "Invalid time range. End must be after start.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = { onDone() }) {
                Text("Download Clip")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        },
    )
}
