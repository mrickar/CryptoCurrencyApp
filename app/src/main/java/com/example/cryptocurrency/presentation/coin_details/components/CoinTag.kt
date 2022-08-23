package com.example.cryptocurrency.presentation.coin_details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.cryptocurrency.presentation.ui.theme.CryptoCurrencyTheme

class TagProvider:PreviewParameterProvider<String>
{
    override val values: Sequence<String>
        get() = listOf<String>("denneme").asSequence()

}


@Composable
fun CoinTag(
    tag:String
)
{
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(10.dp)
    )
    {
        Text(
            text = tag,
            color=MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
            )
    }
}

@Preview(showBackground = true)
@Composable
fun CoinTagPreview(
    @PreviewParameter(TagProvider::class) tag:String
)
{
    CoinTag(tag = tag)
}