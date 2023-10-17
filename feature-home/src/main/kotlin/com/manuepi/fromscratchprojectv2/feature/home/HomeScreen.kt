package com.manuepi.fromscratchprojectv2.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.manuepi.fromscratchprojectv2.common.Screens
import com.manuepi.fromscratchprojectv2.common.SharedNavigator
import com.manuepi.fromscratchprojectv2.common.formatText
import com.manuepi.fromscratchprojectv2.common.views.BoldInSentence
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsItemUiModel
import com.manuepi.fromscratchprojectv2.feature.home.model.HomeUiStateModel
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsUiModel

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("PrivateResource")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    sharedNavigator: SharedNavigator
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val state = viewModel.viewState.collectAsStateWithLifecycle().value

    var inputWord by remember { mutableStateOf("") }
    var finalWord by remember { mutableStateOf("") }

    // API call
    // Deprecated, now we make API call only if user type and validate something on input text
    // It will get result depending on what user typed
    /*LaunchedEffect(key1 = state) {
        viewModel.getNews()
    }*/

    Column(
        Modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            modifier = Modifier.padding(12.dp),
            value = inputWord,
            onValueChange = { inputWord = it },
            label = { Text("Veuillez renseigner un mot-clé") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone =
            {
                keyboardController?.hide()
                viewModel.getNews(word = inputWord)
                finalWord = inputWord
            }),
        )

        when (state) {
            HomeUiStateModel.State.Failure -> FailureState()
            HomeUiStateModel.State.Init -> InitState()
            is HomeUiStateModel.State.Success -> SuccessState(
                word = finalWord,
                model = state.model,
                viewModel = viewModel,
                sharedNavigator = sharedNavigator
            )
        }
    }
}

@Composable
fun InitState() {
    Text(
        text = "Faites une recherche cela vous affichera une liste d'articles", modifier = Modifier
            .padding(12.dp), fontSize = 20.sp, fontFamily = FontFamily.SansSerif
    )
}

@Composable
fun FailureState() {
    Text(
        text = "Echec du chargement des articles. Le nombre maximal d'appel à l'API à été atteint.",
        modifier = Modifier
            .padding(12.dp),
        fontSize = 20.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
fun SuccessState(
    word: String,
    model: NewsUiModel,
    viewModel: HomeViewModel,
    sharedNavigator: SharedNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        if (model.totalResults == null || model.totalResults == 0) {
            BoldInSentence(
                modifier = Modifier.padding(12.dp),
                sourceText = "Malheureusement aucun article n'a été trouvé pour ce mot ",
                endText = ", veuillez réessayer plus tard",
                boldText = word
            )
        } else {
            BoldInSentence(
                modifier = Modifier.padding(12.dp),
                sourceText = formatText(numberOfNews = model.totalResults),
                boldText = word
            )

            LazyColumn {
                items(model.articles) { modelUi ->
                    HomeItems(modelUi = modelUi, onItemClicked = {
                        viewModel.updateSelectedNews(modelUi = modelUi)
                        sharedNavigator.updateScreenState(screen = Screens.NewsDetail)
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeItems(modelUi: NewsItemUiModel, onItemClicked: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))
            .clickable { onItemClicked.invoke() }
            .fillMaxSize()
            .padding(12.dp),
    ) {
        GlideImage(
            model = modelUi.urlToImage,
            contentScale = ContentScale.Crop,
            contentDescription = modelUi.urlToImage,
            failure = placeholder(androidx.core.R.drawable.ic_call_answer),
            modifier = Modifier.size(60.dp)
        )
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(12.dp)) {
            Text(
                text = modelUi.title.toString(),
                maxLines = 2,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = modelUi.description.toString(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}