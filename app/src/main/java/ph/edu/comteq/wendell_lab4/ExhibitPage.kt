package ph.edu.comteq.wendell_lab4

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ph.edu.comteq.wendell_lab4.ui.theme.WendellLAB4Theme


data class Artwork(
    val title: String,
    val years: String,
    val born_at: String,
    val comment: String
)

class ExhibitPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WendellLAB4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExhibitPage(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ExhibitPage(name: String, modifier: Modifier = Modifier) {
    val artworks = remember { mutableStateListOf<Artwork>() }
    val context = LocalContext.current

    // Load JSON
    LaunchedEffect(Unit) {
        val jsonString = context.assets.open("artworks.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<Artwork>>() {}.type
        val data: List<Artwork> = Gson().fromJson(jsonString, type)
        artworks.addAll(data)
    }

    // it will display lady ermine
    if (artworks.isNotEmpty()) {
        val artwork = artworks[1] // Lady Ermine
        ExhibitContent(artwork)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}


@Composable
fun ExhibitContent(artwork: Artwork, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Title: ${artwork.title}")
        Text(text = "Years: ${artwork.years}")
        Text(text = "Born At: ${artwork.born_at}")
        Text(text = "Comment: ${artwork.comment}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    WendellLAB4Theme {
        ExhibitPage("Android")
    }
}