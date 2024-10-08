package com.ichin23.filmes.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.ichin23.filmes.MainActivity
import com.ichin23.filmes.filmes

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MainPage(modifier: Modifier){
    var busca by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    var filmesLista by remember { mutableStateOf(filmes) }

    Column(modifier=modifier){
        Text(
            "Filmes",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color(0xffe36217),
            fontSize = 35.sp)
        TextField(
            value = search,
            onValueChange = {value ->
                search=value
                if(value.length>0){
                    busca=true
                    filmesLista = filmes.filter{filme-> (filme["nome"] as String).lowercase().contains(search.lowercase())}

                }else{
                    busca=false
                    filmesLista= filmes
                }
            },
            placeholder = {Text("Buscar...", color=Color.White)},
            leadingIcon = {Icon(Icons.Outlined.Search, "Buscar filmes", tint = Color.White)},
            modifier = Modifier.fillMaxWidth().padding(12.dp, 4.dp),
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xff5e5e5e),
                unfocusedContainerColor = Color(0xff5e5e5e)
            )
        )
        Box(Modifier.height(10.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
           items(filmesLista){filme->
                CardFilme(filme)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardFilme(filme: Map<String, Any>){
    val painter = rememberAsyncImagePainter(filme["linkFoto"])
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xff5e5e5e)),
        modifier = Modifier.height(310.dp),
        onClick = {

            val intent = Intent(context, FilmeActivity::class.java)
            intent.putExtra("FILME", HashMap(filme))
            context.startActivity(intent)
        }
    ){
        Column(
            Modifier.padding(5.dp)
        ){
            Box(
                Modifier.height(250.dp)
            ) {
                AsyncImage(
                    model = filme["linkFoto"],
                    contentDescription = "Capa Filme: ${filme["nome"]}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,

                    )

            }
            Text("${filme["nome"]}", color = Color.White
            )
        }
    }
}