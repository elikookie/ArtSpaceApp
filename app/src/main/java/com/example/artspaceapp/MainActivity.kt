package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {

        ArtworkShown(
            drawableResourceId = R.drawable.thewaterlilypond,
            title = R.string.TheWaterLilyPond ,
            artist = R.string.TWLP_Artist_Year,
            description = R.string.TWLP_description
        )
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkShown(drawableResourceId: Int, title: Int, artist: Int, description: Int){
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
        .fillMaxSize()

    ) {

        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = null,
            modifier = Modifier
                .border(
                    BorderStroke(10.dp, Color.White)
                )
                .padding(10.dp)
        )
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = stringResource(title)
            )
            Text(
                text = stringResource(artist)
            )
        }


        Text(
            text = stringResource(description),

            )
        }
    }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceLayout()
    }
}