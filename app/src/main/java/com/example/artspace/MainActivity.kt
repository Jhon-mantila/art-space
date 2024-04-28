package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    artSpace()
                }
            }
        }
    }
}

class ImageInfo(val imageResId: Int, val autor: Int, val year: Int)

@Composable
fun artSpace(modifier: Modifier = Modifier) {
    var currentIndex by remember {
        mutableStateOf(0)
    }

    val imageList = listOf(
        ImageInfo(R.drawable.child1, R.string.child1, 2024),
        ImageInfo(R.drawable.child3, R.string.child2, 2023),
        ImageInfo(R.drawable.child4, R.string.child3, 2022)

    )

    println("tamaño lista: " + imageList.size)
    println(imageList[0].imageResId)
    println("CurrentIndex: " + currentIndex)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
              painter = painterResource(id = imageList[currentIndex].imageResId),
              contentDescription = null,
              modifier = Modifier
                  .padding(bottom = 40.dp)
                  .shadow(
                      elevation = 4.dp,
                      )
          )
        Text(
            text = stringResource(id = imageList[currentIndex].autor)
        )
        Text(
            text = imageList[currentIndex].year.toString(),
            modifier = Modifier.padding(bottom =  40.dp)

        )
        Row {
            Button(onClick = { currentIndex = showPreviousImage(imageList.size, currentIndex) }) {
                Text(text = "Anterior")
            }
            Button(onClick = { currentIndex = showNextImage(imageList.size, currentIndex) }) {
                Text(text = "Siguiente")
            }
        }

    }
}

private fun showPreviousImage(size: Int, currentIndex: Int): Int{

    return if (currentIndex > 0) currentIndex - 1 else size - 1
}

private fun showNextImage(size: Int, currentIndex: Int): Int{
    println("Tamaño:" + size)
    println("Actial:" +currentIndex)
    var response = 0

    if (currentIndex < size - 1){
        println("Entro")
        response = currentIndex + 1
        println("Valor: "+response)
    } else{
        response = 0
    }
    return response
}

@Composable
fun previewImageSlider(){
    val imageList = listOf(
        ImageInfo(R.drawable.child1, R.string.child1, 2024),
        ImageInfo(R.drawable.child3, R.string.child2, 2023),
        ImageInfo(R.drawable.child4, R.string.child3, 2022)

    )
}

@Preview(showBackground = true)
@Composable
fun viewPreview() {
    ArtSpaceTheme {
        artSpace()
    }
}