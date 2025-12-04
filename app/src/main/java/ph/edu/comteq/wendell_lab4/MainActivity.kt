package ph.edu.comteq.wendell_lab4

import ph.edu.comteq.wendell_lab4.ui.theme.WendellLAB4Theme
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
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
import kotlinx.coroutines.delay


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

    // Animation visibility states
    var showMuseum by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }
    var showIntro by remember { mutableStateOf(false) }

    val museumOffset by animateFloatAsState(
        targetValue = if (showMuseum) 0f else -150f,  // slide down
        animationSpec = tween(
            durationMillis = 1200,
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            showTitle = true   // start typing title next
        }
    )

    val museumAlpha by animateFloatAsState(
        targetValue = if (showMuseum) 1f else 0f,
        animationSpec = tween(1200)
    )

    LaunchedEffect(Unit) { showMuseum = true }

    val fullTitle = "Experience Art"
    var typedTitle by remember { mutableStateOf("") }

    LaunchedEffect(showTitle) {
        if (showTitle) {
            fullTitle.forEachIndexed { i, _ ->
                typedTitle = fullTitle.substring(0, i + 1)
                delay(55) // timing
            }
            showIntro = true
        }
    }

    val fullIntro = "We are thrilled to invite you to join us for an extraordinary event that will immerse you in the world of art."

    var typedIntro by remember { mutableStateOf("") }

    LaunchedEffect(showIntro) {
        if (showIntro) {
            fullIntro.forEachIndexed { i, _ ->
                typedIntro = fullIntro.substring(0, i + 1)
                delay(22)  // EXACT TIMING
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(18.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(125.dp)
                .width(125.dp)
                .padding(bottom = 30.dp)
        )

        Box(
            modifier = Modifier
                .offset(y = museumOffset.dp)
                .alpha(museumAlpha)
        ) {
            Image(
                painter = painterResource(id = R.drawable.louvre),
                contentDescription = "Museum Image",
                contentScale = ContentScale.Crop
            )

            Canvas(modifier = Modifier.matchParentSize()) {
                drawArc(
                    color = Color(0xFFF4AF37),
                    startAngle = 300f,
                    sweepAngle = 200f,
                    useCenter = false,
                    style = Stroke(
                        width = 5.dp.toPx()
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = typedTitle,
            fontFamily = playfairdisplayregular,
            fontSize = 32.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = typedIntro,
            fontFamily = playfairdisplayregular,
            fontSize = 15.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Button
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
