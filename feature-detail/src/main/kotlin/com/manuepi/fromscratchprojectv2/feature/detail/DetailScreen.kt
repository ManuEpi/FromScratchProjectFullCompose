package com.manuepi.fromscratchprojectv2.feature.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.manuepi.fromscratchprojectv2.common.SharedNavigator
import com.manuepi.fromscratchprojectv2.common.views.HyperlinkInSentence

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    sharedNavigator: SharedNavigator
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .background(color = Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = stringResource(id = androidx.compose.ui.R.string.close_drawer),
            Modifier
                .clickable {
                    sharedNavigator.goBack()
                }
                .padding(12.dp)
                .size(22.dp)
        )

        state.value?.let { modelUi ->
            Text(
                text = state.value?.title.orEmpty(),
                modifier = Modifier
                    .padding(14.dp),
                fontSize = 22.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth(),
            ) {
                GlideImage(
                    model = modelUi.urlToImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = modelUi.urlToImage,
                    failure = placeholder(androidx.core.R.drawable.ic_call_answer),
                    modifier = Modifier.size(60.dp)
                )

                Column(Modifier.padding(12.dp)) {
                    Text(
                        text = if (modelUi.author.isNullOrEmpty()) "" else "Auteur : " + modelUi.author,
                        maxLines = 2,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = if (modelUi.publishedAt.isNullOrEmpty()) "" else "Date : " + modelUi.publishedAt,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Text(
                text = modelUi.content.orEmpty(),
                modifier = Modifier
                    .padding(14.dp),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Normal
            )

            HyperlinkInSentence(
                modifier = Modifier.padding(12.dp),
                sourceText = "Lien vers ",
                hyperlinkText = "l'article en question",
                uri = modelUi.url.orEmpty()
            )
        }
    }
}