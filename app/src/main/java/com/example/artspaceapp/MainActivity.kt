package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
    var showMore by remember { mutableStateOf(false)}
    var expand by remember { mutableStateOf(false)}

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
        .fillMaxSize()
    ){
        Card(
            shape= MaterialTheme.shapes.large,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
            painter = painterResource(drawableResourceId),
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
        )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.titleLarge ,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(artist),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center
                )
            }

        }

        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary,
        )
        Card(
            onClick = {},
            shape= MaterialTheme.shapes.large,
            modifier = Modifier.padding(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = stringResource(description),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = {
                        if (it.hasVisualOverflow) {
                            showMore = true
                        }
                    }
                )
                if (showMore){
                    Button(onClick = {  }) {
                        Text(
                            text =  "More",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }

}

@Composable
 fun showExpanded(){
    Surface(
        shape = RoundedCornerShape(12.dp)
    ) {
        Text("Text on Surface")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceLayout()
    }
}