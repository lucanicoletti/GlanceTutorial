package com.lucanicoletti.glancetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucanicoletti.glancetutorial.ui.theme.GlanceTutorialTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var notesRepository: NotesRepository

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val notes = remember {
                mutableStateListOf<String>()
            }
            LaunchedEffect(Unit) {
                notesRepository.getNotes().collectLatest {
                    notes.clear()
                    notes.addAll(it.map { note -> note.title })
                }
            }
            GlanceTutorialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        SmallFloatingActionButton(
                            onClick = {
                                coroutineScope.launch(Dispatchers.IO) {
                                    notesRepository.addRandomNote()
                                }
                            },
                            shape = CircleShape,
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
                    },
                    topBar = {
                        TopAppBar(title = { Text("Glance Tutorial") })
                    }
                ) { paddingValues ->
                    LazyColumn(
                        modifier = Modifier.padding(paddingValues).padding(16.dp)
                    ) {
                        items(notes) { note ->
                            Text(text = "• $note")
                        }
                    }
                }
            }
        }
    }
}
