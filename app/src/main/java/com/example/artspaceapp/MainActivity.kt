package com.example.artspaceapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.compose.ArtSpaceAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold



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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceLayout() {
    val artworkImgs = listOf(
        R.drawable.thewaterlilypond,
        R.drawable.womanwithaparsol,
        R.drawable.impressionsunriseclaudemonet,
        )

    val titles = listOf(
        R.string.TheWaterLilyPond,
        R.string.WomanWithAParasol,
        R.string.ImpressionSunrise
    )

    val artists = listOf(
        R.string.TWLP_Artist_Year,
        R.string.WWAPMMHS_Artist_Year,
        R.string.IS_Artist_Year
    )

    val descriptions = listOf(
        R.string.TWLP_description,
        R.string.WWAPMMHS_description,
        R.string.IS_description
    )

    var index by remember { mutableStateOf(0)}

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(
                        onClick = {
                            if (index == 0){
                                index = 0
                            }
                            else{
                                index--
                            }
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
                    }
                    IconButton(
                        onClick = {
                            if (index == titles.size - 1){
                                index = 0
                            }
                            else{
                                index++
                            }
                        }
                    ) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Next")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )
        }
    ) {
        ArtworkShown(
            drawableResourceId = artworkImgs[index],
            title = titles[index],
            artist = artists[index],
            description = descriptions[index],
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkShown(drawableResourceId: Int, title: Int, artist: Int, description: Int){
    var showMore by remember { mutableStateOf(false)}
    var expand by remember { mutableStateOf(false)}

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
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
            modifier = Modifier.width(124.dp),
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
                modifier = Modifier
                    .padding(24.dp)
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
                    },
                    modifier = Modifier.clickable { expand= true }
                )
                if (showMore){
                    Button(
                        onClick = { expand = true },
                    ) {
                        Text(
                            text =  "More",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
                if (expand){
                    Popup(
                        onDismissRequest = {
                            expand = false
                        }
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.large,
                            color= MaterialTheme.colorScheme.surface,
                            shadowElevation = 24.dp,
                            tonalElevation = 4.dp,
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(24.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.SpaceAround,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(24.dp)
                            ) {
                                Text(
                                    text = stringResource(description),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )
                                Button(onClick = { expand = false }) {
                                    Text(
                                        text =  "Close",
                                        style = MaterialTheme.typography.labelMedium,
                                    )
                                }
                            }

                        }

                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(24.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceLayout()
    }
}