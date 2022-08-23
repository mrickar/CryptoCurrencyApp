package com.example.cryptocurrency.presentation.coin_details.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrency.data.remote.dto.TeamMember
import com.example.cryptocurrency.presentation.Screen
import com.example.cryptocurrency.presentation.coin_details.CoinDetailsViewModel
import com.example.cryptocurrency.presentation.coin_list.CoinListViewModel
import com.example.cryptocurrency.presentation.coin_list.components.CoinListItem
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailsViewModel= hiltViewModel()
)
{
    val state=viewModel.state.value
    val coin=state.coin
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        coin?.let {
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp))
            {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.weight(8f)
                            )
                        Text(
                            text = if(coin.isActive) "active" else "inactive",
                            color=if(coin.isActive) Color.Green else Color.Red,
                            textAlign=TextAlign.End,
                            fontStyle= Italic,
                            modifier = Modifier
                                .weight(2f)
                                .align(CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = coin.description,
                        style = MaterialTheme.typography.body2
                        )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Tags", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(mainAxisSpacing = 10.dp,crossAxisSpacing = 10.dp, modifier = Modifier.fillMaxWidth()) {
                        coin.tags.forEach{tag->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Team Members", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(coin.team) { teamMember ->
                    TeamListItem(teamMember = teamMember, modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    Divider()
                }
            }
        }

        if(state.error.isNotBlank())
        {
            Text(text = state.error,
                color=MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
                )
        }
        else if(state.isLoading)
        {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
