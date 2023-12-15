package com.example.travelhelper.presentation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import com.example.travelhelper.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    navigateToDetailScreen: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = { focusManager.clearFocus() },
                indication = null,
                interactionSource = interactionSource
            )
    ) {
        SearchBar(onBackClick = onBackClick, navigateToDetailScreen = navigateToDetailScreen)
    }
}

@Composable
private fun SearchBar(
    onBackClick: () -> Unit,
    navigateToDetailScreen: (String) -> Unit
) {
    Row {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Search") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                cursorColor = Color.Black
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    modifier = Modifier.clickable {
                        onBackClick()
                        navigateToDetailScreen(text.text)
                    }
                )
            }
        )
    }
}