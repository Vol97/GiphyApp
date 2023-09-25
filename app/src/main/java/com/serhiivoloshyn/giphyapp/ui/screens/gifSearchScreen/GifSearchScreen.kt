package com.serhiivoloshyn.giphyapp.ui.screens.gifSearchScreen

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.wear.compose.material.ContentAlpha
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.serhiivoloshyn.giphyapp.navigation.Screen
import com.serhiivoloshyn.giphyapp.models.GifImageData
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GifSearchScreen(
    navController: NavHostController,
    viewModel: GiphyViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            SearchView(
                onTextChange = {
                    viewModel.updateSearchString(searchString = it)
                },
                onSearchClicked = {
                    viewModel.searchGifs(searchString = it)
                }
            )
        },
        content = {
            ListContent(
                navController = navController,
                paddingValues = it
            )
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchString by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .semantics {
                contentDescription = "SearchView"
            },
        color = MaterialTheme.colorScheme.background
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = "TextField"
                },
            value = searchString,
            onValueChange = {
                searchString = it
                onTextChange(searchString)
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    onClick = {
                        onSearchClicked(searchString)
                        keyboardController?.hide()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "SearchIcon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .semantics {
                            contentDescription = "ClearButton"
                        },
                    onClick = {
                        if (searchString.isNotEmpty()) {
                            searchString = ""
                            onTextChange(searchString)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "ClearIcon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(searchString)
                    keyboardController?.hide()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
fun ListContent(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: GiphyViewModel = hiltViewModel()
) {
    val lazyPagingItems = viewModel.searchedGifs.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            count = lazyPagingItems.itemCount,
        ) { index ->
            lazyPagingItems[index]?.let {
                LoadImage(
                    gifImageData = it.images,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun LoadImage(
    gifImageData: GifImageData,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                val encodedUrl = URLEncoder.encode(
                    gifImageData.original.url,
                    StandardCharsets.UTF_8.toString()
                )
                navController.navigate(Screen.Gif.withArgs(encodedUrl))
            }
    ) {
        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = gifImageData.downsized.downsizedUrl)
                    .apply(block = {
                        size(Size.ORIGINAL)
                    }).build(), imageLoader = imageLoader
            ),
            contentDescription = null,
        )
    }
}