package ph.edu.comteq.wendell_lab4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.wendell_lab4.ui.theme.WendellLAB4Theme

class ExploreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WendellLAB4Theme{
                ExplorePage()
            }
        }
    }
}

@Composable
fun ExplorePage() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Explore",
            color = Color.White,
            fontSize = 35.sp,
            fontFamily = playfairdisplayregular,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Spacer(modifier = Modifier.height(29.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Upcoming Event",
                color = Color.White,
                fontSize = 26.sp,
                fontFamily = playfairdisplayregular
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    onClick = {
                        val intent = Intent(context, TicketingService::class.java)
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF4AF37)
                    ),
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text("Tickets", color = Color.Black)
                }
                Image(
                    painter = painterResource(id = R.drawable.chevron_right),
                    contentDescription = "Arrow",
                    modifier = Modifier.size(24.dp)
                )
            }

        }



        Spacer(modifier = Modifier.height(16.dp))

        // Event Details
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.renaissance),
                    contentDescription = "Event",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column {
                        Text(
                            text = "10",
                            color = Color.White,
                            fontSize = 30.sp,
                            fontFamily = playfairdisplayregular,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "OCT",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontFamily = playfairdisplayregular,
                        )
                    }
                }
            }

            Column (modifier = Modifier){
                Text(
                    text = "Renaissance Exhibition",
                    color = Color.White,
                    fontFamily = playfairdisplayregular,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                )
                Text(
                    text = "9:00 AM - 6:00 PM",
                    color = Color.LightGray,
                    fontFamily = playfairdisplayregular,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
                Text(
                    text = "Indulge in the rich tapestry of Renaissance art",
                    color = Color(0xFFD4AF37),
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    fontFamily = playfairdisplayregular,
                    modifier = Modifier.padding(start = 16.dp,bottom = 8.dp)
                )
                Text(
                    text = "+33 (0)1 23 45 67 89",
                    color = Color.White,
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    fontFamily = playfairdisplayregular,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4AF37)),
            shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                "Visit Gallery",
                color = Color.Black,
                fontFamily = playfairdisplayregular,
                fontSize = 15.sp)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ExplorePagePreview() {
    WendellLAB4Theme {
        Homepage()
    }
}