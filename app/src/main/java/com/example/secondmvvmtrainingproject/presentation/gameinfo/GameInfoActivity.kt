package com.example.secondmvvmtrainingproject.presentation.gameinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.presentation.gameinfo.ui.theme.SecondMVVMTrainingProjectTheme

class GameInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           SecondMVVMTrainingProjectTheme {
               DefaultPreview()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 250, heightDp = 450)
@Composable
fun DefaultPreview() {
    LazyColumn {
        items(1) {
            Title()
            BodyGeneral()
            BodyGame()
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_ice),
                win = stringResource(R.string.game_info_body_game_pokemon_type_ice_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_ice_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_dragon),
                win = stringResource(R.string.game_info_body_game_pokemon_type_dragon_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_dragon_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_ghost),
                win = stringResource(R.string.game_info_body_game_pokemon_type_ghost_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_ghost_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_fighting),
                win = stringResource(R.string.game_info_body_game_pokemon_type_fighting_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_fighting_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_psychic),
                win = stringResource(R.string.game_info_body_game_pokemon_type_psychic_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_psychic_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_ground),
                win = stringResource(R.string.game_info_body_game_pokemon_type_ground_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_ground_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_rock),
                win = stringResource(R.string.game_info_body_game_pokemon_type_rock_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_rock_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_electric),
                win = stringResource(R.string.game_info_body_game_pokemon_type_electric_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_electric_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_fire),
                win = stringResource(R.string.game_info_body_game_pokemon_type_fire_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_fire_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_bug),
                win = stringResource(R.string.game_info_body_game_pokemon_type_bug_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_bug_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_grass),
                win = stringResource(R.string.game_info_body_game_pokemon_type_grass_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_grass_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_poison),
                win = stringResource(R.string.game_info_body_game_pokemon_type_poison_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_poison_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_normal),
                win = stringResource(R.string.game_info_body_game_pokemon_type_normal_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_normal_lose)
            )
            GameAlgorithm(
                type = stringResource(R.string.game_info_body_game_pokemon_type_water),
                win = stringResource(R.string.game_info_body_game_pokemon_type_water_win),
                lose = stringResource(R.string.game_info_body_game_pokemon_type_water_lose)
            )
            AfterBattle()
        }
    }
}

@Composable
fun Title() {
    Box(
        contentAlignment = Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.common_padding_double))
    ) {
        Text(
            text = stringResource(R.string.game_info_title),
            color = Color(R.color.light_blue_900_dark),
            style = MaterialTheme.typography.h5.copy(
                shadow = Shadow(
                    offset = Offset(8f,8f),
                    blurRadius = 12f,
                    color = Color.Black.copy(alpha = 0.6f)
                ),
                textAlign = TextAlign.Center
            ),
        )
    }
}

@Composable
fun BodyGeneral() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.common_padding_default))
            .padding(top = dimensionResource(R.dimen.common_padding_default))

    ) {
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.game_info_body_general_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle2,
                textDecoration = TextDecoration.Underline
            )
        }
        Text(
            text = stringResource(R.string.game_info_body_general),
            textAlign = TextAlign.Justify,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.common_padding_min))
                .padding(dimensionResource(R.dimen.common_padding_default))
        )
    }
}

@Composable
fun BodyGame() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.common_padding_default))

    ) {
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.game_info_body_game_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle2,
                textDecoration = TextDecoration.Underline
            )
        }
        
        Text(
            text = stringResource(R.string.game_info_body_game),
            textAlign = TextAlign.Justify,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.common_padding_min))
                .padding(dimensionResource(R.dimen.common_padding_default))
        )
        
        
    }
}

@Composable
fun GameAlgorithm(type: String, win: String, lose: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.common_padding_default))
    ) {
        Row(
            modifier = Modifier
            .border(1.dp, Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Box(
                    contentAlignment = Center
                ) {
                    Text(
                        text = stringResource(R.string.game_info_body_game_pokemon_type_title),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(R.color.blue_light))
                    )
                }
                Box(
                    contentAlignment = Center,
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Text(
                        text = type,
                        textAlign = TextAlign.Center,
                        fontSize = 11.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 38.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Black)
            ) {
                Box(
                    contentAlignment = Center
                ) {
                    Text(
                        text = stringResource(R.string.game_info_body_game_pokemon_type_ice_win_title),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(R.color.green_500))
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Text(
                        text = win,
                        textAlign = TextAlign.Start,
                        fontSize = 11.sp
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Box(
                    contentAlignment = Center
                ) {
                    Text(
                        text = stringResource(R.string.game_info_body_game_pokemon_type_ice_lose_title),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(R.color.red))
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Text(
                        text = lose,
                        textAlign = TextAlign.Start,
                        fontSize = 11.sp,
                    )
                }
            }
        }
    }
}

@Composable
fun AfterBattle() {
    Box(
        contentAlignment = Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.common_padding_min))
    ) {
        Text(
            text = stringResource(R.string.game_info_body_after_game),
            textAlign = TextAlign.Justify,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.common_padding_min))
                .padding(dimensionResource(R.dimen.common_padding_default))
        )
    }
}

