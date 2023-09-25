package com.manuepi.feature.splashscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay

private const val splashDelay: Long = 1500

@SuppressLint("PrivateResource")
@Composable
fun SplashScreen(
    isLoaded: Boolean?,
    onSplashEndedValid: () -> Unit,
    onSplashEndedInvalid: () -> Unit
) {
    val currentValid = rememberUpdatedState(newValue = isLoaded)

    LaunchedEffect(key1 = currentValid) {
        delay(splashDelay)
        if (currentValid.value == null || currentValid.value != true)
            onSplashEndedInvalid()
        else
            onSplashEndedValid()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
            contentDescription = stringResource(id = androidx.core.R.string.call_notification_answer_action)
        )
    }
}