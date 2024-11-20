package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment2.ui.theme.Assignment2Theme

class Activity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // This is valid for ComponentActivity
        setContent {
            Assignment2Theme {
                Assignment2Activity2App()
            }
        }
    }
}

private val stringList = listOf(
    "Different Operating Systems",
    "Unstable Environments",
    "Rapid Changes in the Platform",
    "Users Involvement",
    "Maximizing App Users"
)

@Composable
fun Assignment2Activity2App() {
    BackToMainButton()
}
@Composable
fun StringList() {
    LazyColumn {
        items(stringList) { item ->
            Text(
                text = item,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
@Composable
fun BackToMainButton(modifier: Modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
    val context = LocalContext.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StringList()
        Button(onClick = {
            val expIntent = Intent(context, MainActivity::class.java) // Ensure MainActivity2 is correct
            context.startActivity(expIntent)
        }) {
            Text("MainActivity2") // Correct the button label if needed
        }
    }
}
