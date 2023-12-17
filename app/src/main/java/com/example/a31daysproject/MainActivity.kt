package com.example.a31daysproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a31daysproject.data.CardData
import com.example.a31daysproject.model.Information
import com.example.a31daysproject.ui.theme._31DaysProjectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _31DaysProjectTheme {
                nintendoapp()
                }
            }
        }
}

@Composable
fun nintendolist(nintendolist: List<Information>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(nintendolist) { information ->
            ExpandableCard(
                information = information,
            )
        }
    }
}

@Composable
fun nintendoapp() {
    Column {
        Text(
            text = "30 Days of Nintendo",
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp),
        style = TextStyle(color = Color.White)
        )
        nintendolist(
            nintendolist = CardData().loadCards(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(information: Information) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        onClick = { expanded = !expanded },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = LocalContext.current.getString(information.stringId),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
            )
            Image(
                painter = painterResource(information.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .animateContentSize()
            )
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Text(
                    text = LocalContext.current.getString(information.stringId2),
                    modifier = Modifier.padding(9.dp)
                )
            }
        }
    }
}
