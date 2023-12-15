package com.example.travelhelper.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelhelper.R
import com.example.travelhelper.data.local.BookmarkEntity
import com.example.travelhelper.domain.entity.NearbyDestination
import com.example.travelhelper.domain.entity.PopularDestination
import com.example.travelhelper.presentation.home.CurrencyUiState
import com.example.travelhelper.ui.theme.Gray02
import com.example.travelhelper.ui.theme.TravelHelperTheme

@Composable
fun BookmarkContent(
    onClick: () -> Unit,
    item: BookmarkEntity
) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(113.dp)
            .padding(horizontal = 20.dp)
            .clip(shape)
            .background(Color.White)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 18.dp)
        ) {
           NetworkImage(
               imageUrl = item.imageUrl,
               placeholder = painterResource(id = R.drawable.test),
               modifier = Modifier
                   .padding(start = 16.dp)
                   .size(82.dp)
                   .clip(shape)
           )
           Spacer(modifier = Modifier.width(10.dp))
           Information(item.name, cutTextBeforeSecondComma(item.location), R.drawable.ic_location)
        }
    }
}

fun cutTextBeforeSecondComma(text: String): String {
    var commaCount = 0
    for (i in text.indices.reversed()) {
        if (text[i] == ',') {
            commaCount++
            if (commaCount == 2) {
                return text.substring(i + 1)
            }
        }
    }
    return ""
}

@Composable
private fun Information(
    name: String,
    description: String,
    @DrawableRes iconRes: Int
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleSmall,
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Gray02
            )
        }
    }
}

@Composable
fun PopularityContent(
    item: PopularDestination,
    imageUrl: String
 ) {
    val shape = RoundedCornerShape(12.dp)

    Box(
        modifier = Modifier
            .height(113.dp)
            .shadow(elevation = 4.dp, shape = shape)
            .clip(shape)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 15.dp, start = 10.dp, end = 35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                imageUrl = imageUrl,
                placeholder = painterResource(id = R.drawable.test),
                modifier = Modifier
                    .size(93.dp)
                    .clip(shape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Information(item.name, "${item.num} visited", R.drawable.ic_person)
        }
    }
}

@Composable
fun NearbyContent(
    item: NearbyDestination,
    imageUrl: String,
    onClick: (String) -> Unit
) {
    val shape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
            .width(270.dp)
            .shadow(elevation = 4.dp, shape = shape)
            .clip(shape)
            .background(Color.White)
            .clickable(
                onClick = { onClick(item.name) }
            )
    ) {
        NetworkImage(
            imageUrl = imageUrl,
            placeholder = painterResource(id = R.drawable.test),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 160.dp)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .shadow(elevation = 4.dp, shape = shape)
                .clip(shape)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = item.name,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = item.address,
                style = MaterialTheme.typography.bodyMedium,
                color = Gray02,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun CurrencyConverter(
    uiState: CurrencyUiState
) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = shape)
            .clip(shape)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            when(uiState) {
                is CurrencyUiState.Loading -> {
                    Loading(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is CurrencyUiState.Empty -> {

                }
                is CurrencyUiState.CurrencyData -> {
                    CurrencyData(R.drawable.ic_usa, "USD", uiState.data[4].currency.toFloat())
                    Spacer(modifier = Modifier.height(7.dp))
                    CurrencyData(R.drawable.ic_euro, "EUR", uiState.data[1].currency.toFloat())
                    Spacer(modifier = Modifier.height(7.dp))
                    CurrencyData(R.drawable.ic_japan, "JPY", uiState.data[3].currency.toFloat())
                    Spacer(modifier = Modifier.height(7.dp))
                    CurrencyData(R.drawable.ic_china, "CNY", uiState.data[0].currency.toFloat())
                    Spacer(modifier = Modifier.height(7.dp))
                    CurrencyData(R.drawable.ic_uk, "GBP", uiState.data[2].currency.toFloat())
                }
            }
        }
    }
}

@Composable
private fun CurrencyData(
    @DrawableRes icon: Int,
    country: String,
    currency: Float
) {
    val shape = RoundedCornerShape(10.dp)
    var text by remember { mutableStateOf("1") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(30.dp)
                .padding(end = 10.dp)
        )
        Text(
            textAlign = TextAlign.Center,
            text = country
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            textAlign = TextAlign.End,
            text = try {
                    "${currency * text.toInt()} KRW"
                } catch (e: Exception) {
                    "0 KRW"
            }
        )
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .clip(shape)
                .background(Color.White)
                .width(100.dp)
                .height(35.dp)
                .border(1.dp, Color(0xFF949190), shape)
                .padding(horizontal = 10.dp, vertical = 5.dp),
        )
    }
}

@Preview
@Composable
private fun BookmarkContentPreview() {
    TravelHelperTheme {
//        BookmarkContent(
//            onClick = {}
//        )
    }
}

@Preview
@Composable
private fun PopularityContentPreview() {
    TravelHelperTheme {
//        PopularityContent("", "")
    }
}

@Preview
@Composable
private fun NearbyContentPreview() {
    TravelHelperTheme {
//        NearbyContent(item = "경복궁", onClick = {})
    }
}

@Preview
@Composable
private fun CurrencyPreview() {
    TravelHelperTheme {
//        CurrencyData()
    }
}