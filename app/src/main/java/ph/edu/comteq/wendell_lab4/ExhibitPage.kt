package ph.edu.comteq.wendell_lab4

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ph.edu.comteq.wendell_lab4.ui.theme.WendellLAB4Theme


data class Artwork(
    val title: String,
    val years: String,
    val born_at: String,
    val comment: String,
    val imageRes: Int
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

    LaunchedEffect(Unit) {
        val jsonString = context.assets.open("artworks.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<Artwork>>() {}.type
        val data: List<Artwork> = Gson().fromJson(jsonString, type)
        artworks.addAll(data)
    }

    if (artworks.isNotEmpty()) {
        ExhibitPager(artworks)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExhibitPager(artworks: List<Artwork>) {

    val pagerState = rememberPagerState(
        pageCount = { artworks.size }  // <-- lambda, required
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->

        ExhibitContent(
            artwork = artworks[page],
            modifier = Modifier.fillMaxSize()
        )
    }
}




@Composable
fun ExhibitContent(artwork: Artwork, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // yellow card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF0A500),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = artwork.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )

                Text(
                    text = "${artwork.years}, ${artwork.born_at}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF3A3A3A)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        val imageRes = when (artwork.title) {
            "Mona Lisa" -> R.drawable.mona_lisa
            "Lady Ermine" -> R.drawable.lady_ermine
            "Litta Madonna" -> R.drawable.litta_madonna
            else -> R.drawable.mona_lisa
        }

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = artwork.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.quote),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = artwork.comment,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    WendellLAB4Theme {
        ExhibitPage("Android")
    }
}