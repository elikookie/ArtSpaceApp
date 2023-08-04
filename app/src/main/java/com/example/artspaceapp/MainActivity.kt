package com.example.artspaceapp

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Scaffold
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

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

data class ArtPiece<T>(
    val artworkImg: T,
    val title: T,
    val artist: T,
    val description: T,
)
class ArtGallery  {
    val artPieces: MutableList<ArtPiece<*>> = mutableListOf()

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceLayout() {
    val gallery = ArtGallery()

    val artwork1 = ArtPiece(
        R.drawable.thewaterlilypond,
        R.string.TheWaterLilyPond,
        R.string.TWLP_Artist_Year,
        R.string.TWLP_description
    )

    val artwork2 = ArtPiece(
        R.drawable.womanwithaparsol,
        R.string.WomanWithAParasol,
        R.string.WWAPMMHS_Artist_Year,
        R.string.WWAPMMHS_description
    )
    val artwork3 = ArtPiece(
        R.drawable.impressionsunriseclaudemonet,
        R.string.ImpressionSunrise,
        R.string.IS_Artist_Year,
        R.string.IS_description
    )

    gallery.artPieces.add(artwork1)
    gallery.artPieces.add(artwork2)
    gallery.artPieces.add(artwork3)


    var index by remember { mutableStateOf(0)}

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )

    var showAddNewArtPopup by remember {mutableStateOf(false)}

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(
                        onClick = {
                            if (index == 0){
                               index = gallery.artPieces.size - 1
                            }
                            else{
                                index--
                            }
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
                    }
                    Spacer(modifier = Modifier.padding(58.dp))
                    FloatingActionButton(
                        onClick = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                            showAddNewArtPopup = true
                        },
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                    Spacer(modifier = Modifier.padding(58.dp))
                    IconButton(
                        onClick = {
                            if (index == gallery.artPieces.size - 1){
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
            )
        }

    ) {

        if (showAddNewArtPopup) {
            var userTitle by remember { mutableStateOf("")}
            var artist by remember { mutableStateOf("")}
            var description by remember { mutableStateOf("")}

            Popup(
                onDismissRequest = {showAddNewArtPopup = false},

                ){
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Card(
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .padding(16.dp),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(24.dp)
                        ) {
                            OutlinedTextField(
                                value = userTitle,
                                onValueChange = { userTitle = it },
                                label = { Text("Artwork Title") },
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                )
                            )
                            OutlinedTextField(
                                value = artist,
                                onValueChange = { artist = it },
                                label = { Text("Artist Name") },
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                )
                            )
                            OutlinedTextField(
                                value = description,
                                onValueChange = { description = it },
                                label = { Text("Description") },
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = true,
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Go
                                )
                            )
                        }
                    }
                }
                val userArt = ArtPiece(
                    selectedImageUri,
                    userTitle,
                    artist,
                    description
                )
                gallery.artPieces.add(userArt)
            }
        }
        ArtworkShown(gallery.artPieces[index])

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkShown(art: ArtPiece<*>){
    var showMore by remember { mutableStateOf(true)}
    var expand by remember { mutableStateOf(false)}

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        Card(
            shape= MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(16.dp)
                .width(364.dp)
        ) {
            Image(
                painter = painterResource(art.artworkImg as Int),
                contentDescription = null,
                contentScale = ContentScale.FillWidth ,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .aspectRatio(16f / 9f)
        )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = stringResource(art.title as Int),
                    style = MaterialTheme.typography.titleLarge ,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(art.artist as Int),
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
            onClick = {expand= true},
            shape= MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Text(
                    text = stringResource(art.description as Int),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = {
                        showMore = true
                        if (!it.hasVisualOverflow) {
                            showMore = false
                        }
                        expand = false
                    },
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
                                        text = stringResource(art.description),
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