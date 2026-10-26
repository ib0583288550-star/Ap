package com.ybapps.melodyseparator

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AudioFile
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedUri by remember { mutableStateOf<Uri?>(null) }
            var selectedName by remember { mutableStateOf<String?>(null) }
            val picker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenDocument()
            ) { uri ->
                if (uri != null) {
                    selectedUri = uri
                    selectedName = uri.lastPathSegment?.substringAfterLast('/') ?: "קובץ שמע נבחר"
                }
            }
            MaterialTheme(colorScheme = darkColorScheme(
                primary = Color(0xFFB7F36B),
                secondary = Color(0xFF8EE8D0),
                background = Color(0xFF10131A),
                surface = Color(0xFF1B202B)
            )) {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF10131A)) {
                    Column(
                        modifier = Modifier.fillMaxSize().background(
                            Brush.verticalGradient(listOf(Color(0xFF18243A), Color(0xFF10131A), Color(0xFF19152B)))
                        ).padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(28.dp))
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            androidx.compose.material3.Icon(Icons.Rounded.GraphicEq, contentDescription = null, tint = Color(0xFFB7F36B))
                            Text("מנגינה", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                        }
                        Text("הפרדת שירה מהמוזיקה", color = Color(0xFFB8C2D5), fontSize = 16.sp)
                        Spacer(Modifier.height(28.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(26.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF202838))
                        ) {
                            Column(Modifier.padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                androidx.compose.material3.Icon(Icons.Rounded.MusicNote, contentDescription = null, tint = Color(0xFFB7F36B), modifier = Modifier.height(58.dp))
                                Spacer(Modifier.height(12.dp))
                                Text("השיר שלך. המנגינה שלך.", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                                Spacer(Modifier.height(8.dp))
                                Text("בחר קובץ שמע מהמכשיר כדי להתחיל.", color = Color(0xFFBAC4D5), textAlign = TextAlign.Center)
                                Spacer(Modifier.height(18.dp))
                                Button(onClick = { picker.launch(arrayOf("audio/*")) }, modifier = Modifier.fillMaxWidth()) {
                                    androidx.compose.material3.Icon(Icons.Rounded.UploadFile, contentDescription = null)
                                    Text("  בחירת שיר")
                                }
                            }
                        }
                        if (selectedUri != null) {
                            Spacer(Modifier.height(16.dp))
                            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF202838)), shape = RoundedCornerShape(18.dp)) {
                                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                    androidx.compose.material3.Icon(Icons.Rounded.AudioFile, contentDescription = null, tint = Color(0xFF8EE8D0))
                                    Text(selectedName ?: "קובץ נבחר", modifier = Modifier.padding(start = 12.dp), color = Color.White)
                                }
                            }
                            Spacer(Modifier.height(16.dp))
                            Button(onClick = {
                                Toast.makeText(this@MainActivity, "מנוע ההפרדה עדיין בפיתוח — הקובץ לא עובד ולא נשמר", Toast.LENGTH_LONG).show()
                            }, modifier = Modifier.fillMaxWidth()) {
                                Text("הפרד שירה מהמנגינה")
                            }
                        }
                        Spacer(Modifier.weight(1f))
                        Text("עיבוד מקומי • ללא העלאת השיר", color = Color(0xFF8995AA), fontSize = 12.sp)
                        Text("yb apps", color = Color(0xFF66738A), fontSize = 12.sp)
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}
