package com.lucanicoletti.glancetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lucanicoletti.glancetutorial.ui.theme.GlanceTutorialTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
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
            val notes = notesRepository.getNotes().collectAsState(initial = emptyList())
            GlanceTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
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
                }, topBar = {
                    TopAppBar(title = { Text("Glance Tutorial") })
                }) { paddingValues ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(16.dp),
                        contentPadding = PaddingValues(vertical = 4.dp)
                    ) {
                        items(notes.value) { note ->
                            fun deleteNote() = coroutineScope.launch(Dispatchers.IO) {
                                notesRepository.deleteNote(note)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(36.dp)
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(.7f),
                                    text = "• ${note.title}",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                IconButton(
                                    modifier = Modifier
                                        .border(2.dp, Color.Red, CircleShape)
                                        .aspectRatio(1f)
                                        .size(24.dp)
                                        .padding(8.dp),
                                    onClick = { deleteNote() }) {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "delete",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> Color.Red
        SwipeToDismissBoxValue.EndToStart -> Color(0xFFCC3300)
        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            Icons.Default.Delete, contentDescription = "delete", tint = Color.White
        )
        Spacer(modifier = Modifier)
        Icon(
            Icons.Default.Delete, contentDescription = "delete", tint = Color.White
        )
    }
}
