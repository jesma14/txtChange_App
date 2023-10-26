/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2023, Jrod7938, Khang-ALe, jesma14, Holesum
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jrod7938.textchangeapp.screens.search


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.jrod7938.textchangeapp.components.SelectionType
import com.jrod7938.textchangeapp.components.ToggleButton
import com.jrod7938.textchangeapp.components.ToggleButtonOption
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    navController: NavHostController,
    category: String? = "",
    viewModel: BookSearchScreenViewModel = viewModel()
) {
    Column(){
        Search()
        SearchBy()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search() {
    // search input state
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val initQuery: Array<String> = arrayOf<String>()
    var queryItems by remember { mutableStateOf(initQuery) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        SearchBar( // search bar display
            query = text,
            onQueryChange = { text = it },
            onSearch = { active = false; queryItems += text },
            active = active,
            onActiveChange = { active = it },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Search,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { if (text.isNotEmpty()) text = "" else active = false }) {
                    Icon(
                        Icons.Outlined.Clear,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Close Search"
                    )

                }
            },
            placeholder = { Text("Search") }

        ) {
            queryItems.forEach {
                Row(modifier = Modifier.padding(14.dp)) {
                    Icon(imageVector = Icons.Default.History ,
                        contentDescription = "History",
                        modifier = Modifier.padding(end = 15.dp))
                    Text(text = it.toString(),
                        modifier = Modifier.clickable { text = it })
                }
            }
        }
    }
}

@Composable
fun SearchBy(){
    var active by remember { mutableStateOf(false)}
    var a by remember { mutableStateOf(false)}

    Row(horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp, start = 28.dp)){
        Text("Search Options")
        Spacer(modifier = Modifier.padding(5.dp))
        ClickableText(
            text = AnnotatedString(text = if(active) "Show" else "Hide"), onClick = {
                active = !active },
            style = TextStyle(color = MaterialTheme.colorScheme.primary, textDecoration = TextDecoration.Underline  )
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        AnimatedVisibility(visible = a,
            enter = slideInVertically(
                initialOffsetY = { -40 }
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically() + fadeOut()) {
            SearchFilterBar()
        }
    }


    LaunchedEffect(active) {
        launch {
            a = !a
        }
    }

}
var searchTypeFromSelection : String = ""
fun selectSearchType(type : String) : SearchType{
    return when (type) {
        "ISBN" -> SearchType.ISBN
        "Title" -> SearchType.Title
        "Author" -> SearchType.Author
        else -> SearchType.ISBN
    }
}

@Composable
fun SearchFilterBar(){
    val context = LocalContext.current
    val options = arrayOf(
        ToggleButtonOption("ISBN", iconRes = null),
        ToggleButtonOption("Title", iconRes = null),
        ToggleButtonOption("Author", iconRes = null)
    )
    ToggleButton(
        options = options,
        type = SelectionType.SINGLE,
        modifier = Modifier.padding(end = 4.dp),
        onClick = {
            selectedOption ->  searchTypeFromSelection = selectedOption[0].text

        }
    )
}

enum class SearchType {
    ISBN,
    Title,
    Author
}

@Composable
fun SearchResults(){
    var searchModel : BookSearchScreenViewModel
    var type = selectSearchType(searchTypeFromSelection)
    // if(type == SearchType.ISBN) searchModel.searchBookByISBN()
    // need query info

}



