package com.manuepi.fromscratchprojectv2.common.views

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun HyperlinkInSentence(
    modifier: Modifier,
    sourceText: String,
    hyperlinkText: String,
    uri: String
) {
    val annotatedString = buildAnnotatedString {
        append(sourceText)
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Normal
            )
        ) {
            append(hyperlinkText)
            addStringAnnotation(
                tag = "URL",
                annotation = uri,
                start = length - hyperlinkText.length,
                end = length
            )
        }
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
        }
    )
}