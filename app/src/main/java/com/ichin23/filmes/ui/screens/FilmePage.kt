package com.ichin23.filmes.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.util.HashMap

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilmePage(filme: HashMap<*, *>, modifier:Modifier){
    val generos: List<String> = filme["genero"] as List<String>
    Column(
        modifier = modifier.padding(12.dp)
    ){
        Row{
            Box(
                Modifier.height(250.dp),

            ) {
                AsyncImage(
                    model = filme["linkFoto"],
                    contentDescription = "Capa Filme: ${filme["nome"]}",
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.Fit,

                    )

            }
            Box(Modifier.width(6.dp))
            Column(
                Modifier
                    .height(250.dp)
                    .padding(4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text("${filme["nome"]}", fontSize = 22.sp, fontWeight = FontWeight.W600, color = Color.White)
                Column{
                    Text("Avaliação:", fontSize = 18.sp, fontWeight = FontWeight.W600, color = Color.White)
                    Row {
                        Icon(
                            Icons.Filled.Star,
                            tint = Color.Yellow,
                            contentDescription = "Avaliação"
                        )
                        Text("${filme["avaliacao"]}", color = Color.White, fontSize = 22.sp)
                    }
                    Box(Modifier.height(6.dp))
                    Text("Gêneros: ", fontSize = 18.sp, fontWeight = FontWeight.W600, color = Color.White)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        generos.forEach { genero ->
                            Box(
                                modifier
                                    .background(Color(0xffe36217), shape = RoundedCornerShape(15))
                                    .padding(4.dp)
                            )
                            {
                                Text("${genero}")
                            }

                        }
                    }
                }
            }
        }
        Box(Modifier.height(10.dp))
        Text("Descrição", fontSize = 18.sp, fontWeight = FontWeight.W600, color = Color.White)
        Text("${filme["descricao"]}", fontSize = 14.sp, color = Color.White)
        Box(Modifier.height(18.dp))
        Card(
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xff5e5e5e))
        ) {
            Column(
                modifier=Modifier.padding(8.dp).fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ){
                Text("Produtora: ${filme["estudio"]}", color = Color.White)
                Text("Diretor: ${filme["diretor"]}", color = Color.White)
                Text("País: ${filme["pais"]}", color = Color.White)
            }
        }
    }
}