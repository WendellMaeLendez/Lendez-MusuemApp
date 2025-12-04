package ph.edu.comteq.wendell_lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.wendell_lab4.ui.theme.WendellLAB4Theme


class ArtistsPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
//            val NavController = rememberNavController("ArtistsPage")
            WendellLAB4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtistsPage(
                        modifier = Modifier.padding(innerPadding)

                    )
                }
            }
        }
    }
}

@Composable
fun ArtistsPage(modifier: Modifier = Modifier) {

    val background = painterResource(id = R.drawable.background) // background image
    var selectedTab by remember { mutableStateOf("Artists") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background image
        Image(
            painter = background,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Foreground content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Spacer(Modifier.height(40.dp))

            Text(
                text = "Explore the art of",
                fontSize = 24.sp,
                color = Color.White
            )
            Text(
                text = "Renaissance",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD6A550)
            )

            Spacer(Modifier.height(16.dp))

            // Search box
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Type to search...") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFFD6A550),
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_search),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_camera),
                        contentDescription = null
                    )
                }
            )

            Spacer(Modifier.height(8.dp))

            // Tabs
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TabItem("Artists", selectedTab) { selectedTab = "Artists" }
                TabItem("Artworks", selectedTab) { selectedTab = "Artworks" }
            }

            Spacer(Modifier.height(16.dp))

            if (selectedTab == "Artists") {
                ArtistList()
            } else {
                Text(
                    text = "Artworks tab content coming soon...",
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Composable
fun TabItem(title: String, selected: String, onClick: () -> Unit) {
    val isSelected = title == selected
    TextButton(onClick = onClick) {
        Text(
            text = title,
            color = if (isSelected) Color(0xFFD6A550) else Color.White,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun ArtistList() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ArtistItem(
            name = "Leonardo da Vinci",
            years = "1452 - 1519",
            artworks = listOf(
                R.drawable.leonardo_da_vinci,
                R.drawable.mona_lisa,
                R.drawable.lady_ermine,
                R.drawable.litta_madonna
            )
        )
        ArtistItem(
            name = "Michelangelo",
            years = "1475 - 1564",
            artworks = listOf(
                R.drawable.michelangelo,
                R.drawable.david,
                R.drawable.torment_of_saint_anthony,
                R.drawable.delphic_sibyl
            )
        )
        ArtistItem(
            name = "Gustav Klimt",
            years = "1452 - 1519",
            artworks = listOf(
                R.drawable.gustav_klimt,
                R.drawable.adele_bloch_bauer,
                R.drawable.lady_with_fan,
                R.drawable.the_kiss
            )
        )

    }
}
@Composable
fun ArtistItem(name: String, years: String, artworks: List<Int>) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Avatars
            Image(
                painter = painterResource(id = artworks.first()),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(text = name, color = Color.Black, fontSize = 18.sp)
                Text(text = years, color = Color.LightGray, fontSize = 14.sp)
            }
        }

        Spacer(Modifier.height(8.dp))

        // Show artworks
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            artworks.drop(1).forEach { art ->
                Image(
                    painter = painterResource(id = art),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            // If this artwork is Mona Lisa, open ExhibitActivity
                            if (art == R.drawable.mona_lisa) {
                                val intent = android.content.Intent(context, ExhibitPage::class.java)
                                intent.putExtra("artworkName", "Mona Lisa")
                                context.startActivity(intent)
                            }
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WendellLAB4Theme {
        ArtistsPage()
    }
}