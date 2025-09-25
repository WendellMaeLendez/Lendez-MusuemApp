package ph.edu.comteq.wendell_lab4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.wendell_lab4.ui.theme.WendellLAB4Theme

val playfairdisplayregular = FontFamily(
    Font(R.font.playfairdisplayregular, weight = FontWeight.Normal)
)



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WendellLAB4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Homepage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Homepage(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(18.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(125.dp)
                .width(125.dp)
                .padding(bottom = 30.dp)
        )

        Box(modifier = Modifier){
            Image(
                painter = painterResource(id = R.drawable.louvre),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Experience Art",
                color = Color.White,
                fontFamily = playfairdisplayregular,
                fontSize = 32.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "We are thrilled to invite you to join us for an extraordinary event that will immerse you in the world of art.",
            color = Color.White,
            fontFamily = playfairdisplayregular,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Explore Now Button
        Button(
            onClick = {
                val intent = Intent(context, ExploreActivity::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4AF37)
            ),
            modifier = Modifier.padding(10.dp)
        ) {
            Text("Explore Now", color = Color.Black)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    WendellLAB4Theme {
        Homepage()
    }
}