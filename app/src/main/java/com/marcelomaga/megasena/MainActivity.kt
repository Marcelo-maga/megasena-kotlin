package com.marcelomaga.megasena

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcelomaga.megasena.ui.theme.MegasenaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MegasenaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppMegasena()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppMegasena() {
    MegasenaTheme {
        GerarNumero(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun GerarNumero(modifier: Modifier = Modifier) {
    val lista = mutableListOf<Int>()

    var result by remember {
        mutableStateOf("Tente a sorte!!!")
    }

    Column(
        modifier = modifier.fillMaxSize().padding(vertical = 180.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        Text(
            text = "Mega Sena",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp
        )

        Image(
            painter = painterResource(id = R.drawable.trevo),
            contentDescription = "trevo de 4"
        )

        Text(
            text = result,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp
        )

        Button(
            onClick = {
                for(i in 0..5) {
                    val interval = 1..60
                    var sortedNumber = interval.random()

                    while(lista.contains(sortedNumber)) {
                        sortedNumber = interval.random()
                    }

                    lista.add(sortedNumber)
                }
                result = lista
                    .sorted()
                    .toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", " -")
            }
        ) {
            Text(
                text = "Gerar Números",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}