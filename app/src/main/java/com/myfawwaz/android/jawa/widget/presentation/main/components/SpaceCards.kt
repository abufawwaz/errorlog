package com.myfawwaz.android.jawa.widget.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myfawwaz.app.mybrain.ui.theme.Blue
import com.myfawwaz.app.mybrain.R
import com.myfawwaz.android.jawa.widget.presentation.util.Screen
import com.myfawwaz.app.mybrain.ui.theme.Purple

@Composable
fun SpaceRegularCard(
    title: String,
    image: Int,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = backgroundColor,
        elevation = 6.dp
    ) {
        Column(
            Modifier
                .clickable { onClick() }
                .aspectRatio(1f)
                .padding(5.dp),
            verticalArrangement = Arrangement.Top
            ) {
            LazyColumn {

                item {

                    Image( //gambar note
                        contentScale = ContentScale.Crop,
                     //   modifier = Modifier.fillMaxSize(),
                        modifier = Modifier
                    .size(50.dp)
                    .align(alignment = Alignment.End).padding(5.dp),
                         painter = painterResource(id = image),
                        contentDescription = title,
                        alpha = 1F //transfaransi
                    )
                }
                item {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = title,
                       style = MaterialTheme.typography.body2.copy(color = Color.White),
                        fontSize = 15.sp


                    )
                }
            }

        }
    }
}

@Composable
fun SpaceWideCard(
    title: String,
    image: Int,
    backgroundColor: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = backgroundColor,
        elevation = 12.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(18.dp),
        ) {


            Image(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.End),
                painter = painterResource(id = image),
                contentDescription = title)
            Spacer(Modifier.height(1.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.h6.copy(color = Color.White),
            )

        }
    }
}

@Preview
@Composable
fun SpaceRegularCardPreview() {
    SpaceRegularCard(
        "Notes",
        R.drawable.notes_img,
        Blue
    )
}

@Preview
@Composable
fun SpaceWideCardPreview() {
    SpaceWideCard(
        "Coba",
        R.drawable.calendar_img,
        Purple
    ){

    }
}