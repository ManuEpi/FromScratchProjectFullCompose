package com.manuepi.fromscratchprojectv2.common.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun BoldInSentence(
    modifier: Modifier,
    sourceText: String,
    endText: String = "",
    boldText: String
) {
    val annotatedString = buildAnnotatedString {
        append(sourceText)
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.None,
                color = Color.Black,
                fontSize = 22.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
        ) {
            append(boldText)
        }
        append(endText)
    }
    Text(
        text = annotatedString,
        modifier = modifier,
        fontSize = 22.sp,
        fontFamily = FontFamily.SansSerif
    )
}