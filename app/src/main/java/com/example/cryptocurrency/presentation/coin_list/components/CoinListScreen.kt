package com.example.cryptocurrency.presentation.coin_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cryptocurrency.presentation.Screen
import com.example.cryptocurrency.presentation.coin_list.CoinListViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel=hiltViewModel()
)
{
    println("COINLISTSCREEN IN")
    val state=viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        println("BOX IN")
        LazyColumn(modifier = Modifier.fillMaxSize())
        {
            println(state.coins)
            items(state.coins)
            {coin ->
                println("ITEMS IN")
                CoinListItem(coin = coin,
                    onItemClick ={
                        navController.navigate(Screen.CoinDetailScreen.route+"/${coin.id}")
                    }
                )
                println(coin.name)
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