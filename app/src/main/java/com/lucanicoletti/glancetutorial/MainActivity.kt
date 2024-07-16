package com.lucanicoletti.glancetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.lucanicoletti.glancetutorial.db.dao.NotesDao
import com.lucanicoletti.glancetutorial.ui.theme.GlanceTutorialTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    private lateinit var notesDao: NotesDao

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val notes = rememberSaveable {
                mutableStateListOf<String>()
            }
            LaunchedEffect(Unit) {
                notesDao.getNotes().collectLatest {
                    notes.clear()
                    notes.addAll(it.map { note -> note.title })
                }
            }
            GlanceTutorialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        SmallFloatingActionButton(
                            onClick = {},
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
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        item(notes) {

                        }
                    }
                }
            }
        }
    }
}
