package com.example.cryptocurrency.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.presentation.coin_details.components.CoinDetailsScreen
import com.example.cryptocurrency.presentation.coin_list.components.CoinListScreen
import com.example.cryptocurrency.presentation.ui.theme.CryptoCurrencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCurrencyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = Screen.CoinListScreen.route)
                        {
                            composable(
                                route = Screen.CoinListScreen.route
                            ){
                                CoinListScreen(navController)
                            }
                            composable(
                                route = Screen.CoinDetailScreen.route + "/{coinId}"
                            ){
                                CoinDetailsScreen()
                            }
                        }
                    }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoCurrencyTheme {
    }
}