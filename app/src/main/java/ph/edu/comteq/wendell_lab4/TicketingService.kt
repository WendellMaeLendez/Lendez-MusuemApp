package ph.edu.comteq.wendell_lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.wendell_lab4.ui.theme.WendellLAB4Theme
import java.time.Duration
import java.time.Instant

class TicketingService : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WendellLAB4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Ticketing(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ticketing(name: String, modifier: Modifier = Modifier) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().
            plus(Duration.ofDays(2)).toEpochMilli(),
        selectableDates = object: SelectableDates{
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= Instant.now()
                    .plus(Duration.ofDays(1)).toEpochMilli()
            }

        }
    )

    var ticketCount by remember { mutableStateOf(0) }
    var freeTickets by remember { mutableStateOf(0) }
    val ticketPrice = 500
    val totalPrice = ticketCount * ticketPrice
    Column (
        modifier = modifier.background(Color.Black)
    ){
        Column (
            modifier = Modifier
                .weight(weight = 1f)
                .verticalScroll(rememberScrollState())
        ){
//          Header
            Box(
                modifier = Modifier.fillMaxWidth().height(230.dp),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = ph.edu.comteq.wendell_lab4.R.drawable.background_ticket),
                    contentDescription = "Museum",
                    modifier = Modifier.fillMaxWidth().height(230.dp),
                    contentScale = ContentScale.Crop
                )
//              Black overlay
                Box(
                    modifier = Modifier.fillMaxWidth().height(230.dp)
                        .background(Color.Black.copy(alpha = 0.7f))
                )
                Text(
                    "Official\nTicketing Service",
                    fontSize = 32.sp,
                    fontFamily = playfairdisplayregular,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 36.sp
                )
            }
//          inner container for date and ticket types
            Column (
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            ){
                DatePicker(
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    state = datePickerState,
                    title = null,
                    showModeToggle = false,
                    headline = {
                        Text(
                            "1. Date to Visit",
                            fontSize = 26.sp,
                            fontFamily = playfairdisplayregular
                        )
                    },
                    colors = DatePickerDefaults.colors(
                        titleContentColor = Color(color=0xFFd29f1b),
                        headlineContentColor = Color(color=0xFFd29f1b),
                        weekdayContentColor = Color(color=0xFFd29f1b),
                        containerColor = Color.Transparent,
                        dayContentColor = Color.White,
                        todayContentColor = Color(color=0xFFd29f1b),
                        todayDateBorderColor = Color(color=0xFFd29f1b),
                        selectedDayContentColor = Color.Black,
                        selectedYearContainerColor = Color(color=0xFFd29f1b),
                        disabledDayContentColor = Color.Gray,
                    )
                )

                Text(
                    "2. Number of Tickets",
                    fontSize = 26.sp,
                    fontFamily = playfairdisplayregular,
                    color = Color(color=0xFFd29f1b),
                    modifier = Modifier.padding(10.dp)
                )

//              general Admission
                Row (verticalAlignment = Alignment.CenterVertically){

                    Column {
                        Text(
                            "General Admission",
                            fontSize = 18.sp,
                            fontFamily = playfairdisplayregular,
                            color = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            "₱500",
                            fontSize = 20.sp,
                            fontFamily = playfairdisplayregular,
                            color = Color(color = 0xFFd29f1b)
                        )
                    }
                    Button(
                        onClick = { if (ticketCount > 0) ticketCount--},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(color = 0xFFd29f1b)
                        ),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                    ) {
                        Text(
                            "-",
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }
                    Text(
                        text = ticketCount.toString(),
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Button(
                        onClick = { ticketCount ++ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(color = 0xFFd29f1b)
                        ),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                    ) {
                        Text(
                            "+",
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }

                }

//              free tickets
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text(
                            "Under 18s, Under 26s residents of the EEA,\nMuseum Members,\nProfessional",
                            fontSize = 15.sp,
                            fontFamily = playfairdisplayregular,
                            color = Color.White
                        )
                        Text(
                            "FREE",
                            fontSize = 20.sp,
                            fontFamily = playfairdisplayregular,
                            color = Color(color = 0xFFd29f1b)
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(
                            onClick = { if (freeTickets > 0) freeTickets-- },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(color = 0xFFd29f1b)
                            ),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                        ) {
                            Text("-", fontSize = 20.sp, color = Color.Black)
                        }

                        Text(
                            text = freeTickets.toString(),
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )

                        Button(
                            onClick = { freeTickets++ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(color = 0xFFd29f1b)
                            ),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                        ) {
                            Text("+", fontSize = 20.sp, color = Color.Black)
                        }
                    }
                }
            }
        }

//        Bottom bar for total
        Row (
            modifier = Modifier.fillMaxWidth().height(80.dp)
                .background(Color(color=0xFFd29f1b))
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ){
            Text(
                "Total: ₱${totalPrice}",
                fontSize = 26.sp,
                fontFamily = playfairdisplayregular,
                color = Color.Black
            )
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                )
            ) {
                Text(
                    "Checkout",
                    fontSize = 20.sp,
                    fontFamily = playfairdisplayregular,
                    color = Color(color=0xFFd29f1b)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicketingPreview() {
    WendellLAB4Theme {
        Ticketing("Android")
    }
}