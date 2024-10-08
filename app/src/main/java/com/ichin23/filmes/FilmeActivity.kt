package com.ichin23.filmes.ui.screens

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ichin23.filmes.ui.theme.FilmesTheme
import java.io.Serializable

class FilmeActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            val filmeHash = getSerializable(this, "FILME", HashMap::class.java)

            FilmesTheme {
                Scaffold (
                    Modifier.fillMaxSize()
                ){
                    FilmePage(filme = filmeHash, modifier= Modifier.padding(it))
                }

            }

        }
    }
}

fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T
{
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        activity.intent.getSerializableExtra(name, clazz)!!
    else
        activity.intent.getSerializableExtra(name) as T
}