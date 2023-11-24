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

package com.jrod7938.textchangeapp.components

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.ModeEditOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.RichTooltipState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.google.firebase.auth.FirebaseAuth
import com.jrod7938.textchangeapp.R
import com.jrod7938.textchangeapp.model.MBook
import com.jrod7938.textchangeapp.model.MCategory
import com.jrod7938.textchangeapp.model.MChatRoom
import com.jrod7938.textchangeapp.model.MCondition
import com.jrod7938.textchangeapp.model.MUser
import com.jrod7938.textchangeapp.model.Message
import com.jrod7938.textchangeapp.model.SearchType
import com.jrod7938.textchangeapp.model.SelectionType
import com.jrod7938.textchangeapp.model.ToggleButtonOption
import com.jrod7938.textchangeapp.navigation.AppScreens
import com.jrod7938.textchangeapp.navigation.BottomNavItem
import com.jrod7938.textchangeapp.screens.account.AccountScreenViewModel
import com.jrod7938.textchangeapp.screens.chatroom.ChatRoomViewModel
import com.jrod7938.textchangeapp.screens.details.BookInfoScreenViewModel
import com.jrod7938.textchangeapp.screens.home.HomeScreen
import com.jrod7938.textchangeapp.screens.login.LoginScreenViewModel
import com.jrod7938.textchangeapp.screens.sell.ListingSubmissionData
import com.jrod7938.textchangeapp.screens.sell.SellScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * This composable is the txtChange name plate. It displays the txtChange name
 * plate.
 *
 * @param size the size of the name plate
 * @param overrideTopPadding the top padding of the name plate
 * @param isRegistered whether the name plate is registered
 */
@Composable
fun NamePlate(
    size: Dp = 200.dp,
    overrideTopPadding: Dp = 50.dp,
    isRegistered: Boolean = true,
) {
    val getResourceId = if (isRegistered) {
        if (isSystemInDarkTheme()) R.drawable.suppreg_dark else R.drawable.suppreg_light
    } else if (isSystemInDarkTheme()) R.drawable.supp_unreg_dark else R.drawable.supp_unreg_light

    Surface(
        modifier = Modifier
            .width(size)
            .padding(top = overrideTopPadding)
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = getResourceId),
            contentDescription = "Supplementary Name Plate"
        )

    }
}

/**
 * This composable is the AppLogo. It displays the app logo.
 *
 * @param appLogoSize the size of the app logo
 * @param namePlateSize the size of the name plate
 * @param namePlateTopPadding the top padding of the name plate
 * @param namePlateRegistered whether the name plate is registered
 */
@Composable
fun AppLogo(
    appLogoSize: Dp = 60.dp,
    namePlateSize: Dp = 200.dp,
    namePlateTopPadding: Dp = 0.dp,
    namePlateRegistered: Boolean = true,
) {
    Row(
        modifier = Modifier
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            modifier = Modifier.size(appLogoSize),
            painter = painterResource(id = R.drawable.applogo),
            contentDescription = "App Logo"
        )
        NamePlate(
            size = namePlateSize,
            overrideTopPadding = namePlateTopPadding,
            isRegistered = namePlateRegistered
        )
    }
}

// @Preview(showBackground = true)
@Composable
private fun AppLogoPreview() {
    AppLogo()
}

/**
 * This composable is the app logo. It displays the app logo.
 *
 * @param size the size of the logo
 * @param scale the scale of the logo
 */
@Composable
fun AppSplashScreenLogo(
    size: Dp = 500.dp,
    scale: Animatable<Float, AnimationVector1D>,
) {
    Surface(
        modifier = Modifier
            .size(size)
            .scale(scale = scale.value),
    ) {
        Image(
            modifier = Modifier.size(75.dp),
            painter = painterResource(id = R.drawable.applogo),
            contentDescription = "App Logo"
        )
    }
}

/**
 * This composable is to view the AppSplashScreenLogo.
 *
 * @see AppSplashScreenLogo
 */
// @Preview(showBackground = true)
@Composable
fun AppSplashScreenLogoPreview() {
    AppSplashScreenLogo(scale = remember {
        Animatable(.9f)
    })
}

/**
 * This composable is the Email Input Field. It displays an input field for the
 * user to enter their email.
 *
 * @param modifier the modifier for the input field
 * @param emailState the state of the email
 * @param labelId the label for the input field
 * @param enabled whether the input field is enabled
 * @param imeAction the IME action for the input field
 * @param onAction the keyboard actions for the input field
 */
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}

/**
 * This composable is the Input Field. It displays an input field for the user
 * to enter text.
 *
 * @param modifier the modifier for the input field
 * @param valueState the state of the input field
 * @param labelId the label for the input field
 * @param enabled whether the input field is enabled
 * @param isSingleLine whether the input field is a single line
 * @param keyboardType the keyboard type for the input field
 * @param imeAction the IME action for the input field
 * @param onAction the keyboard actions for the input field
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}

/**
 * This composable is the User Form. It displays a form for the user to enter
 * their email and password.
 *
 * @param loading whether the form is loading
 * @param isCreateAccount whether the form is for creating an account
 * @param onDone the function to call when the form is done
 *
 * @see EmailInput
 * @see PasswordInput
 * @see SubmitButton
 */
@OptIn(ExperimentalComposeUiApi::class)
//@Preview(showBackground = true)
@Composable
fun UserForm(
    loading: Boolean,
    isCreateAccount: Boolean = false,
    errorMessage: String?,
    viewModel: LoginScreenViewModel,
    onDone: (String, String, String, String) -> Unit = { firstName, lastName, email, pwd -> },
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val firstName = rememberSaveable { mutableStateOf("") }
    val lastName = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    fun isPasswordValid(password : String) : Boolean {
        return if(isCreateAccount) password.matches(Regex("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{6,64})"))
        else password.length in 6..64
    }
    val valid = remember(email.value, password.value, firstName.value, lastName.value) {
        email.value.trim().isNotEmpty()
                && password.value.trim().isNotEmpty()
                && (isPasswordValid(password.value))
                && email.value.endsWith("@pride.hofstra.edu")
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(start = 10.dp, top = 10.dp, bottom = 15.dp)
            .onFocusChanged { viewModel.resetErrorMessage(it) },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        if (isCreateAccount) {
            Text(
                text = "Hello!",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(id = R.string.create_acct),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.inverseSurface
            )
            FirstNameInput(firstNameState = firstName, modifier = Modifier.fillMaxWidth(0.9f))
            LastNameInput(lastNameState = lastName, modifier = Modifier.fillMaxWidth(0.9f))
        } else {
            Text(
                text = "Welcome back!",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Please sign in with your email and password to continue.",
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.inverseSurface
            )
        }
        EmailInput(
            emailState = email,
            enabled = !loading,
            onAction = KeyboardActions { passwordFocusRequest.requestFocus()},
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        PasswordInput(
            modifier = Modifier
                .focusRequester(passwordFocusRequest)
                .fillMaxWidth(0.9f),
            passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            isCreateAccount = isCreateAccount,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(
                    firstName.value.trim(),
                    lastName.value.trim(),
                    email.value.trim(),
                    password.value.trim()
                )
            }
        )
        SubmitButton(
            textId = if (isCreateAccount) "Create Account" else "Login",
            loading = loading,
            validInputs = valid,
        ) {
            onDone(
                firstName.value.trim(),
                lastName.value.trim(),
                email.value.trim(),
                password.value.trim()
            )
            keyboardController?.hide()
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 10.dp, top = 15.dp),
            )
        }

    }
}

/**
 * This composable is the Submit Button. It displays a button for the user to
 * submit their email and password.
 *
 * @param textId the text for the button
 * @param loading whether the button is loading
 * @param validInputs whether the inputs are valid
 * @param onClick the function to call when the button is clicked
 */
@Composable
fun SubmitButton(
    textId: String,
    loading: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(start = 10.dp, top = 15.dp)
            .fillMaxWidth(0.5f),
        enabled = !loading && validInputs,
        shape = MaterialTheme.shapes.small,
        onClick = onClick
    ) {
        if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, fontSize = 14.sp, modifier = Modifier.padding(5.dp))
    }
}

/**
 * This composable is the Password Input Field. It displays an input field for
 * the user to enter their password.
 *
 * @param modifier the modifier for the input field
 * @param passwordState the state of the password
 * @param labelId the label for the input field
 * @param enabled whether the input field is enabled
 * @param passwordVisibility whether the password is visible
 * @param imeAction the IME action for the input field
 * @param onAction the keyboard actions for the input field
 * @param isCreateAccount is the form the registration or log in
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
    isCreateAccount: Boolean,
) {
    var isFocused by remember { mutableStateOf(false)}
    val visualTransformation =
        if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    OutlinedTextField(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .onFocusEvent { isFocused = it.isFocused },
        value = passwordState.value,
        label = { Text(text = labelId) },
        singleLine = true,
        onValueChange = { passwordState.value = it },
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) },
        keyboardActions = onAction,
        supportingText = {
            Column {
                if(isCreateAccount && isFocused ) {
                    if (!passwordState.value.contains(Regex("(?=.*\\d)"))) {
                        Text("Password must contain at least one digit")
                    }
                    if (!passwordState.value.contains(Regex("(?=.*[a-z])"))) {
                        Text("Password must contain at least one lowercase letter")
                    }
                    if (!passwordState.value.contains(Regex("(?=.*[A-Z])"))) {
                        Text("Password must contain at least one uppercase letter")
                    }
                    if (!passwordState.value.contains(Regex("(?=.*[\\W])"))) {
                        Text("Password must contain at least one special character")
                    }
                    if ((passwordState.value.length < 6) || (passwordState.value.length > 64)) {
                        Text("Password length must be between 6 and 64 characters")
                    }
                }
            }
        }
    )
}

/**
 * This composable is the Password Visibility Icon. It displays an icon for the
 * user to toggle the visibility of their password.
 *
 * @param passwordVisibility whether the password is visible
 */
@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icon(
            imageVector = if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
    }
}


/**
 * This composable is the Bottom Navigation Bar. It displays a bottom
 * navigation bar for the user to navigate between screens.
 *
 * @param navController the navigation controller
 * @param items the items for the bottom navigation bar
 *
 * @see BottomNavItem
 */
@Composable
fun BottomNavBar(
    navController: NavHostController,
    items: List<BottomNavItem>
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    AnimatedNavigationBar(
        modifier = Modifier.height(70.dp),
        selectedIndex = selectedIndex,
        ballColor = MaterialTheme.colorScheme.primary,
        cornerRadius = shapeCornerRadius(cornerRadius = 0.dp),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
        barColor = MaterialTheme.colorScheme.primary
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            if (currentRoute?.contains(item.route) == true) selectedIndex = items.indexOf(item)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClickable {
                        if (currentRoute != item.route) {
                            selectedIndex = items.indexOf(item)
                            navController.navigate(item.route)
                            // selectedIndex = item.ordinal
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = if (currentRoute?.contains(item.route) == true) item.selectedIcon else item.unselectedIcon,
                    contentDescription = null,
                    tint = if (currentRoute?.contains(item.route) == true) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.background
                )
            }
        }

    }

}

/**
 * This composable is the App Bar. It displays an app bar for the user to
 * navigate between account and saved books screen.
 *
 * @param navController the navigation controller
 *
 * @see AppLogo
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    navController: NavHostController,
    items: List<BottomNavItem>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val (title, setTitle) = remember { mutableStateOf("") }

    val (expanded, setExpanded) = remember { mutableStateOf(false) }

    val context = LocalContext.current

    val sendInvite = inviteFriends()
    val sendFeedback = sendFeedback()


    items.forEach { item ->
        if (currentRoute?.contains(item.route) == true) setTitle(item.title)
    }
    TopAppBar(
        modifier = Modifier
            .fillMaxHeight(0.1f)
            .padding(top = 15.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 10.dp)
            )
        },
        actions = {
            IconButton(onClick = { navController.navigate(AppScreens.ConversationsScreen.name)}){
                Icon(
                    imageVector = Icons.Default.Message,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Open Conversations"
                )
            }
            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(
                    onClick = { setExpanded(true) },

                    ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "More Vertical"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { setExpanded(false) }
                ) {
                    DropdownMenuItem(
                        content = { Text("Invite Friends") },
                        onClick = { sendInvite.let { context.startActivity(sendInvite) } }
                    )
                    DropdownMenuItem(
                        content = { Text("Send Feedback") },
                        onClick = { sendFeedback.let { context.startActivity(sendFeedback) } }
                    )
                }

            }
        },
    )

}

/**
 * This composable is the invite friends intent. It displays an intent for the
 * user to invite friends.
 *
 * @return the intent to invite friends
 */
@Composable
fun inviteFriends(): Intent {
    val invitation = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(
            Intent.EXTRA_TEXT, "Hello there fellow Hofstra Student!" +
                    "\n\nI invite you to check out txtChange." +
                    "\n\nI am using it to buy and sell from other students"
        )
        putExtra(Intent.EXTRA_SUBJECT, "Join txtChange Today!")
    }
    return invitation
}

/**
 * This composable is the send feedback intent. It displays an intent for the
 * user to send feedback.
 *
 * @return the intent to send feedback
 */
@Composable
fun sendFeedback(): Intent {
    val feedback = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(
            Intent.EXTRA_EMAIL, arrayOf("txtChangeTeam@gmail.com")
        )
        putExtra(
            Intent.EXTRA_SUBJECT, "txtChange Feedback"
        )
    }

    return feedback
}

/**
 * A card that displays a book category
 *
 * @param category the category of the book
 * @param bookImageUrl the url of the book image
 * @param navController the nav controller
 *
 * @return a card that displays a book category
 *
 * @see HomeScreen
 */
@Composable
fun CategoryCard(
    category: String,
    bookImageUrl: String,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp)
            .clickable(onClick = { navController.navigate("${AppScreens.SearchScreen.name}/$category") }),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .padding(10.dp),
                painter = rememberAsyncImagePainter(model = bookImageUrl),
                contentDescription = "$category image"
            )
            Text(
                text = category,
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 18.sp),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Displays the categories of books.
 *
 * @param bookCategories the categories of books
 * @param navController the navigation controller
 *
 * @see CategoryCard
 * @see HomeScreen
 */
@Composable
fun DisplayCategories(
    bookCategories: HashMap<String, MBook>,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(bookCategories.entries.toList()) { entry ->
            val category = entry.key
            val book = entry.value

            CategoryCard(
                category = category,
                bookImageUrl = book.imageURL,
                navController = navController
            )
        }
    }
}

/**
 * This function displays the buttons on the home screen.
 *
 * @param navController the navigation controller
 *
 * @see HomeScreen
 */
@Composable
fun HomeScreenButtons(
    navController: NavHostController
) {
    var show by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(width = 200.dp, height = 40.dp),
            onClick = { navController.navigate(AppScreens.SearchScreen.name) }
        ) {
            Text(text = "Find A Book")
        }
        Button(
            modifier = Modifier
                .padding(16.dp)
                .size(width = 200.dp, height = 40.dp),
            onClick = { show = true }
        ) {
            Text(text = "Sell A Book")
        }

        if (show) PostListingMBS(onSheetDismissed = { show = false })
    }
}

/**
 * This composable is the FirstName Input Field. It displays an input field for the
 * user to enter their first name.
 *
 * @param modifier the modifier for the input field
 * @param firstNameState the state of the email
 * @param labelId the label for the input field
 * @param enabled whether the input field is enabled
 * @param imeAction the IME action for the input field
 * @param onAction the keyboard actions for the input field
 */
@Composable
fun FirstNameInput(
    modifier: Modifier = Modifier,
    firstNameState: MutableState<String>,
    labelId: String = "First Name",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = firstNameState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Text,
        imeAction = imeAction,
        onAction = onAction
    )
}

/**
 * This composable is the LastName Input Field. It displays an input field for the
 * user to enter their last name.
 *
 * @param modifier the modifier for the input field
 * @param lastNameState the state of the email
 * @param labelId the label for the input field
 * @param enabled whether the input field is enabled
 * @param imeAction the IME action for the input field
 * @param onAction the keyboard actions for the input field
 */
@Composable
fun LastNameInput(
    modifier: Modifier = Modifier,
    lastNameState: MutableState<String>,
    labelId: String = "Last Name",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = lastNameState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Text,
        imeAction = imeAction,
        onAction = onAction
    )
}

/**
 * This composable is the Book Edit Alert. It displays a card for a book in the account screen to edit.
 *
 * @param book the book to display
 * @param onConfirm the function to call when the user confirms the book
 * @param onDismiss the function to call when the user dismisses the book
 *
 * @see MBook
 */
@Composable
fun EditBookDialog(
    book: MBook,
    onConfirm: (MBook) -> Unit,
    onDismiss: () -> Unit
) {
    var editedCondition by remember { mutableStateOf(book.condition) }
    var editedPrice by remember { mutableStateOf(book.price.toString()) }

    val valid by remember(editedCondition, editedPrice) {
        mutableStateOf(
            editedCondition.matches("^[a-zA-Z\\s]+$".toRegex())
                    && editedPrice.matches("^\\d*\\.?\\d+$".toRegex())
                    && editedPrice.toDouble() > 0
        )
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Edit ${book.title}", fontSize = 14.sp) },
        text = {
            Column {
                BookConditionDropdown(
                    selectedCondition = editedCondition,
                    onConditionSelected = { editedCondition = it }
                )
                OutlinedTextField(
                    value = editedPrice,
                    enabled = true,
                    onValueChange = {
                        if (it.matches("^\\d*\\.?\\d*$".toRegex())) {
                            editedPrice = it
                        }
                    },
                    label = { Text("Book Price") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions.Default
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (valid) {
                    onConfirm(
                        book.copy(
                            condition = editedCondition,
                            price = editedPrice.toDouble()
                        )
                    )
                    onDismiss()
                }
            }) {
                Text("Confirm", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel", fontWeight = FontWeight.Bold)
            }
        }
    )
}

/**
 * Account Book Listings
 *
 * @param bookListings List<MBook> the list of book listings
 * @param currentlyEditingBook MutableState<MBook?> the book that is currently being edited
 * @param viewModel AccountScreenViewModel the viewmodel for the screen
 *
 * @see MBook
 * @see AccountScreenViewModel
 */
@Composable
fun AccountListings(
    bookListings: List<MBook>,
    currentlyEditingBook: MutableState<MBook?>,
    viewModel: AccountScreenViewModel = viewModel(),
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp, top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        bookListings.chunked(2).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                BookListingRows(
                    rowItems = row,
                    viewModel = viewModel,
                    currentlyEditingBook = currentlyEditingBook,
                    navController = navController,
                )
            }
        }
    }
}

/**
 * Book Listing Rows
 *
 * @param rowItems List<MBook> the list of book listings
 * @param viewModel AccountScreenViewModel the viewmodel for the screen
 * @param currentlyEditingBook MutableState<MBook?> the book that is currently being edited
 * @param navController NavController the nav controller
 *
 * @see MBook
 * @see AccountScreenViewModel
 */
@Composable
fun BookListingRows(
    rowItems: List<MBook>,
    viewModel: AccountScreenViewModel,
    currentlyEditingBook: MutableState<MBook?>,
    navController: NavController
) {
    for (item in rowItems) {
        BookListingItem(
            book = item,
            viewModel = viewModel,
            currentlyEditingBook = currentlyEditingBook,
            navController = navController
        )
    }
}

/**
 * Book Listing Item
 *
 * @param book MBook the book
 * @param viewModel AccountScreenViewModel the viewmodel for the screen
 * @param currentlyEditingBook MutableState<MBook?> the book that is currently being edited
 * @param navController NavController the nav controller
 */
@Composable
fun BookListingItem(
    book: MBook,
    viewModel: AccountScreenViewModel,
    currentlyEditingBook: MutableState<MBook?>,
    navController: NavController
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var show by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .height(250.dp)
            .width((screenWidth / 2) - 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = MaterialTheme.shapes.extraSmall,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(book.imageURL),
                    contentDescription = "Book Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("${AppScreens.BookInfoScreen.name}/${book.bookID}")
                        }
                )
            }

            Text(
                text = book.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { currentlyEditingBook.value = book },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondaryContainer,
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    content = {
                        Icon(
                            imageVector = Icons.Default.ModeEditOutline,
                            contentDescription = "Edit Book",
                            tint = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )

                IconButton(
                    onClick = { show = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    content = {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Delete Book",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
            }
            if (show) {
                DestructiveActionDialog(
                    isVisible = true,
                    onConfirmAction = { viewModel.deleteBook(book) },
                    onDismissAction = { show = false },
                    title = "Are You Sure?",
                    text = "You are about to delete '${book.title}'. This action cannot be undone. Do you still want to proceed?",
                    confirmButtonText = "Continue",
                    dismissButtonText = "Cancel",
                    imageVector = Icons.Default.DeleteForever
                )
            }
        }
    }
}


/**
 * This composable is the confirmation dialog when
 * the user wants to delete or edit book.
 *
 * @param isVisible Boolean whether the dialog is visible
 * @param onConfirmAction () -> Unit the function to call when the user confirms the action
 * @param onDismissAction () -> Unit the function to call when the user dismisses the action
 * @param title String the title of the dialog
 * @param text String the text of the dialog
 * @param confirmButtonText String the text of the confirm button
 * @param dismissButtonText String the text of the dismiss button
 * @param imageVector ImageVector the image vector of the dialog
 */
@Composable
fun DestructiveActionDialog(
    isVisible: Boolean,
    onConfirmAction: () -> Unit,
    onDismissAction: () -> Unit,
    title: String,
    text: String,
    confirmButtonText: String,
    dismissButtonText: String,
    imageVector: ImageVector,
) {
    val (view, setView) = remember { mutableStateOf(isVisible) }
    if (view) {
        AlertDialog(
            onDismissRequest = onDismissAction.also { setView(false) },
            confirmButton = {
                TextButton(
                    onClick = onConfirmAction,
                    content = {
                        Text(
                            confirmButtonText,
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissAction.also { setView(false) },
                    content = { Text(dismissButtonText) }
                )
            },
            title = {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Text(text)
            },
            icon = {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "Delete Warning",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
    }
}

/**
 * Account Info
 *
 * @param user MUser the user
 * @param viewModel AccountScreenViewModel the viewmodel for the screen
 *
 * @see MUser
 */
@Composable
fun AccountInfo(
    user: MUser, navController: NavController,
    viewModel: AccountScreenViewModel
) {
    var showLogout by remember { mutableStateOf(false) }
    var showUpdate by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inverseSurface,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.inverseSurface,
                disabledContentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {},
            content = { Text("${user.firstName[0]}", fontSize = 40.sp) },
            modifier = Modifier.size(100.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            text = "${user.firstName} ${user.lastName}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "@${user.displayName}",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${user.bookListings.size} Listings | ${user.savedBooks.size} Saved",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(bottom = 15.dp)
        ) {
            Button(onClick = { showUpdate = true }) {
                Text("Edit Profile")
            }
            IconButton(
                onClick = { showLogout = true },
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 5.dp, bottom = 5.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onError,
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout",
                    tint = MaterialTheme.colorScheme.onError,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(20.dp)
                )
            }
        }
    }
    if (showUpdate) EditProfileDialog(
        viewModel = viewModel,
        onDismissAction = { showUpdate = false },
        isVisible = true
    )

    if (showLogout) DestructiveActionDialog(
        isVisible = true,
        onConfirmAction = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate(AppScreens.LoginScreen.name) {
                popUpTo(navController.graph.startDestinationRoute!!) { inclusive = true }
                launchSingleTop = true
            }
        },
        onDismissAction = { showLogout = false },
        title = "Log out",
        text = "Are you sure you want to log out?",
        confirmButtonText = "Logout",
        dismissButtonText = "Cancel",
        imageVector = Icons.Default.Logout
    )
}

/**
 * This composable is the Edit Profile Dialog. It displays a dialog for the user to
 * edit their profile.
 *
 * @param viewModel AccountScreenViewModel the viewmodel for the screen
 * @param onDismissAction () -> Unit the function to call when the user dismisses the action
 * @param isVisible Boolean whether the dialog is visible
 */
@Composable
fun EditProfileDialog(
    viewModel: AccountScreenViewModel,
    onDismissAction: () -> Unit,
    isVisible: Boolean
) {
    val (view, setView) = remember { mutableStateOf(isVisible) }
    val firstName = remember { mutableStateOf(TextFieldValue("")) }
    var isValidFirstName by remember { mutableStateOf(false) }

    val lastName = remember { mutableStateOf(TextFieldValue("")) }
    var isValidLastName by remember { mutableStateOf(false) }

    if (view) {
        AlertDialog(
            onDismissRequest = onDismissAction.also { setView(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismissAction.also {
                            viewModel.editUserProfile(
                                firstName.value.text,
                                lastName.value.text
                            )
                            setView(false)
                        }
                    },
                    content = {
                        Text(
                            "Update",
                            fontWeight = FontWeight.ExtraBold
                        )
                    },
                    enabled = isValidFirstName && isValidLastName
                )
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissAction.also { setView(false) },
                    content = { Text("Cancel") }
                )
            },
            title = {
                Text(
                    text = "Edit User Profile",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Column(horizontalAlignment = Alignment.Start) {
                    OutlinedTextField(
                        label = { Text("First Name") },
                        value = firstName.value,
                        onValueChange = { input ->
                            firstName.value = input
                            isValidFirstName = input.text.isNotEmpty()
                        },
                        isError = !isValidFirstName,
                        supportingText = {
                            if (!isValidFirstName) {
                                Text("First name field must not be empty")
                            }
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Empty FirstName",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .clickable { firstName.value = TextFieldValue("") }
                                    .size(20.dp)
                            )
                        }
                    )

                    OutlinedTextField(
                        label = { Text("Last Name") },
                        value = lastName.value,
                        onValueChange = { input ->
                            lastName.value = input
                            isValidLastName = input.text.isNotEmpty()
                        },
                        isError = !isValidLastName,
                        supportingText = {
                            if (!isValidLastName) {
                                Text("Last name field must not be empty")
                            }
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Empty LastName",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .clickable { lastName.value = TextFieldValue("") }
                                    .size(20.dp)
                            )
                        }
                    )

                }

            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
    }
}


/**
 * This composable is the Book Condition Dropdown. It displays a dropdown for
 * the user to select the condition of their book.
 *
 * @param selectedCondition the selected condition
 * @param onConditionSelected the function to call when the condition is selected
 *
 */
@Composable
fun BookConditionDropdown(
    selectedCondition: String,
    onConditionSelected: (String) -> Unit
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ConditionTooltip()
            TextButton(onClick = { isDropdownExpanded = true }
            ) {
                Text(selectedCondition)
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop down arrow"
                )
            }

            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                MCondition.conditions.forEach { condition ->
                    DropdownMenuItem(
                        onClick = {
                            onConditionSelected(condition.returnCondition())
                            isDropdownExpanded = false
                        }
                    ) {
                        Text(
                            condition.returnCondition(),
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                }
            }
        }
    }
}

/**
 * Displays the book info
 *
 * @param book the book to display
 * @param user the user that is logged in
 * @param onContactClicked callback to display the seller's email
 * @param viewModel BookInfoScreenViewModel the viewmodel for the screen
 *
 * @see MBook
 * @see MUser
 * @see BookInfoScreenViewModel
 */
@Composable
fun BookInfoView(
    book: MBook,
    user: MUser,
    onContactClicked: () -> Unit,
    viewModel: BookInfoScreenViewModel = viewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = book.imageURL),
                contentDescription = "${book.title} Image",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = book.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (user.email != book.email) {
                    Button(
                        colors = ButtonDefaults
                            .buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        onClick = onContactClicked
                    ) {
                        Text(text = "Contact Seller", fontSize = 16.sp)
                    }
                    Button(onClick = {
                        if (user.savedBooks.contains(book.bookID)) {
                            viewModel.unsaveBook(book)
                            viewModel.fetchBookDetails(book.bookID)
                            GlobalScope.launch {
                                withContext(Dispatchers.Main) {
                                    viewModel.getUser()
                                }
                            }
                        } else {
                            viewModel.saveBook(book)
                            viewModel.fetchBookDetails(book.bookID)
                            GlobalScope.launch {
                                withContext(Dispatchers.Main) {
                                    viewModel.getUser()
                                }
                            }
                        }
                    }) {
                        Text(
                            text = if (user.savedBooks.contains(book.bookID)) "Unsave" else "Save",
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Author: ${book.author}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ISBN: ${book.isbn}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Category: ${book.mCategory}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Condition: ${book.condition}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price: $${book.price}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Status:", fontWeight = FontWeight.Bold)
                Box(modifier = Modifier
                    .clickable(
                        enabled = book.email != viewModel.email
                    ) {
                        viewModel.buyerVerifiedBook(book)
                        book.buyerConfirm = true
                        viewModel.fetchBookDetails(book.bookID)
                        viewModel.removeBookIfBothPartiesVerified(book)
                    }
                ) {
                    Row {
                        Text(text = "Buyer Verification: ")
                        androidx.compose.material3.Icon(
                            modifier = Modifier.border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.onBackground
                            ),
                            imageVector = if (book.buyerConfirm) Icons.Default.Check else Icons.Default.Clear,
                            tint = if (book.buyerConfirm) Color.Green else Color.Red,
                            contentDescription = "Buyer Verification"
                        )
                    }
                }

                Box(modifier = Modifier
                    .clickable(enabled = book.email == viewModel.email) {
                        viewModel.sellerVerifiedBook(book)
                        book.sellerConfirm = true
                        viewModel.fetchBookDetails(book.bookID)
                        viewModel.removeBookIfBothPartiesVerified(book)
                    }
                ) {
                    Row {
                        Text(text = "Seller Verification: ")
                        androidx.compose.material3.Icon(
                            modifier = Modifier.border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.onBackground
                            ),
                            imageVector = if (book.sellerConfirm) Icons.Default.Check else Icons.Default.Clear,
                            tint = if (book.sellerConfirm) Color.Green else Color.Red,
                            contentDescription = "Buyer Verification"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Description: ${book.description}", fontSize = 16.sp)
        }
    }
}

/**
 * This composable is the Book Condition Tooltip. It displays a tooltip for
 * the user to see the condition of their book.
 *
 * @see MCondition
 * @see BookConditionDropdown
 */
@Composable
fun SelectionPill(
    option: ToggleButtonOption,
    selected: Boolean,
    onClick: (option: ToggleButtonOption) -> Unit = {}
) {

    Button(
        onClick = { onClick(option) },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.background,
        ),
        shape = MaterialTheme.shapes.extraLarge,
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp),
        contentPadding = ButtonDefaults.ContentPadding,
        modifier = Modifier.padding(14.dp, 0.dp),
    ) {
        Row(
            modifier = Modifier.padding(0.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = option.text,
                color = if (selected) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(0.dp),
                fontWeight = FontWeight.Bold
            )
            if (option.iconRes != null) {
                Icon(
                    painterResource(id = option.iconRes),
                    contentDescription = null,
                    tint = if (selected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(4.dp, 2.dp, 2.dp, 2.dp),
                )
            }
        }
    }
}

/**
 * This toggle button is a button that allows the user to select multiple options.
 *
 * @param options the options for the toggle button
 * @param modifier the modifier for the toggle button
 * @param type the type of selection for the toggle button
 * @param onClick the function to call when the user clicks the toggle button
 */
@Composable
fun ToggleButton(
    options: Array<ToggleButtonOption>,
    modifier: Modifier = Modifier,
    type: SelectionType = SelectionType.SINGLE,
    onClick: (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
) {
    val state = remember { mutableStateMapOf<String, ToggleButtonOption>() }

    OutlinedButton(
        onClick = { },
        border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
        contentPadding = PaddingValues(0.dp, 0.dp),
        modifier = modifier
            .padding(0.dp)
            .height(52.dp),
    ) {
        if (options.isEmpty()) {
            return@OutlinedButton
        }
        val onItemClick: (option: ToggleButtonOption) -> Unit = { option ->
            if (type == SelectionType.SINGLE) {
                options.forEach {
                    val key = it.text
                    if (key == option.text) {
                        state[key] = option
                    } else {
                        state.remove(key)
                    }
                }
            } else {
                val key = option.text
                if (!state.contains(key)) {
                    state[key] = option
                } else {
                    state.remove(key)
                }
            }
            onClick(state.values.toTypedArray())
        }
        if (options.size == 1) {
            val option = options.first()
            SelectionPill(
                option = option,
                selected = state.contains(option.text),
                onClick = onItemClick,
            )
            return@OutlinedButton
        }
        val first = options.first()
        val last = options.last()
        val middle = options.slice(1..options.size - 2)
        SelectionPill(
            option = first,
            selected = state.contains(first.text) || state.isEmpty(),
            onClick = onItemClick,
        )
        // VerticalDivider()
        middle.map { option ->
            SelectionPill(
                option = option,
                selected = state.contains(option.text),
                onClick = onItemClick,
            )
            // VerticalDivider()
        }
        SelectionPill(
            option = last,
            selected = state.contains(last.text),
            onClick = onItemClick,
        )
    }
}

/**
 * This composable is the Book Thumbnail. It displays a thumbnail for
 * the user to see the book.
 *
 * @param book the book to display
 * @param viewModel BookInfoScreenViewModel the viewmodel for the screen
 */
@Composable
fun BookThumbnail(
    book: MBook,
    viewModel: BookInfoScreenViewModel = viewModel(),
    navController: NavHostController,
) {

    val user by viewModel.user.observeAsState(initial = null)
    val isBookSaved = user?.savedBooks?.contains(book.bookID) == true
    val (isChecked, setChecked) = remember(isBookSaved) { mutableStateOf(isBookSaved) }
    val (view, setView) = remember { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(user) {
        setChecked(user?.savedBooks?.contains(book.bookID) == true)
    }
    LaunchedEffect(true) {
        viewModel.getUser()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = book.imageURL),
            contentDescription = "Image of ${book.title}",
            modifier = Modifier.size(175.dp)
        )

        Text(
            text = book.title,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(0.7f),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )

        Text(text = "by ${book.author}", fontSize = 14.sp, textAlign = TextAlign.Center)


        Text(
            text = "Price: $${book.price}",
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = "Condition: ${book.condition}",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                SavedToFavoritesButton(
                    isChecked = isChecked,
                    onClick = {
                        if (user?.savedBooks?.contains(book.bookID)!!) {
                            viewModel.unsaveBook(book)
                            viewModel.viewModelScope.launch { viewModel.getUser() }
                            Toast.makeText(
                                context,
                                "Removed from Saved",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            viewModel.saveBook(book)
                            viewModel.viewModelScope.launch { viewModel.getUser() }
                            Toast.makeText(
                                context,
                                "Added to Saved",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        setChecked(!isChecked)
                    })
                Button(
                    onClick = { setView(true) }
                ) {
                    Text(text = "Purchase")
                }
                Icon(
                    Icons.Outlined.Info,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "View More",
                    modifier = Modifier
                        .clickable { navController.navigate("${AppScreens.BookInfoScreen.name}/${book.bookID}") }
                        .padding(start = 10.dp)
                        .size(30.dp)
                )
            }
        }
    }
    if (view) {

        AlertDialog(
            shape = MaterialTheme.shapes.medium,
            onDismissRequest = { setView(false) },
            dismissButton = {
                TextButton(onClick = { setView(false) }) {
                    Text("Cancel", fontWeight = FontWeight.Bold)
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    book.let { book ->
                        val emailIntent = viewModel.prepareInterestEmailIntent(book)
                        emailIntent.let {
                            context.startActivity(emailIntent)
                        }
                    }
                }) { Text("Continue", fontWeight = FontWeight.Bold) }

            },
            title = {
                Text(
                    "Contact Seller?",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Text(
                    "Email the seller of this listing to the begin transaction.",
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        )
    }
}

/**
 * This composable is the Book Search Bar. It displays a search bar for
 * the user to search for books.
 *
 * @param bookList List<MBook> the list of books
 * @param text String the text to search for
 * @param filter SearchType the type of search
 * @param navController NavController the nav controller
 * @param viewModel BookInfoScreenViewModel the viewmodel for the screen
 *
 * @see MBook
 * @see SearchType
 * @see BookInfoScreenViewModel
 */
@Composable
fun DisplaySearchResults(
    bookList: List<MBook>,
    text: String,
    filter: SearchType,
    navController: NavHostController,
    viewModel: BookInfoScreenViewModel = viewModel()
) {

    val (searchText, setSearchText) = remember { mutableStateOf("") }
    val (searchType, setSearchType) = remember { mutableStateOf(filter) }

    setSearchType(filter)

    LaunchedEffect(true) { viewModel.getUser() }

    Column() {
        if (bookList.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Sorry, we couldn't find anything for your query",
                    modifier = Modifier
                        .padding(top = 15.dp, start = 30.dp)
                        .fillMaxWidth(0.70f),
                    fontSize = 15.sp,
                    softWrap = true,
                )
            }
        } else {

            // check if this display needs to be changed

            if (searchText != text && text.isNotEmpty()) {
                if (((searchType == SearchType.ISBN) || (searchType == SearchType.None)) && searchText != bookList[0].isbn) {
                    setSearchType(SearchType.ISBN)
                    if (searchType == filter && text == bookList[0].isbn) setSearchText(text)

                } else if ((searchType == SearchType.Title) && searchText != bookList[0].title) {
                    setSearchType(SearchType.Title)
                    if (searchType == filter && text == bookList[0].title) setSearchText(text)

                } else if ((searchType == SearchType.Author) && searchText != bookList[0].author) {
                    setSearchType(SearchType.Author)
                    if (searchType == filter && text == bookList[0].author) setSearchText(text)
                }
            }

            Column() {
                val annotatedString = buildAnnotatedString {
                    append("Here's what we found for: ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("'${if (searchText.isNotEmpty()) searchText else text}'")
                    }
                }
                Text(
                    text = annotatedString,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 15.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    softWrap = true,
                )

                LazyColumn {

                    bookList.forEach { book ->
                        item {
                            BookThumbnail(book, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

/**
 * This composable is the SavedToFavoritesButton. It displays a button for
 * the user to save a book to their favorites.
 *
 * @param isChecked Boolean whether the book is saved
 * @param onClick () -> Unit the function to call when the user clicks the button
 */
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SavedToFavoritesButton(
    isChecked: Boolean,
    onClick: () -> Unit
) {
    IconToggleButton(
        checked = isChecked,
        onCheckedChange = { onClick() }
    ) {
        val transition = updateTransition(isChecked, label = "Checked indicator")

        val tint by transition.animateColor(
            label = "Tint"
        ) { isChecked ->
            if (isChecked) Color.Red else MaterialTheme.colorScheme.primary
        }

        val size by transition.animateDp(
            transitionSpec = {
                if (false isTransitioningTo true) {
                    keyframes {
                        durationMillis = 250
                        30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                        35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                        40.dp at 75 // ms
                        35.dp at 150 // ms
                    }
                } else {
                    spring(stiffness = Spring.StiffnessVeryLow)
                }
            },
            label = "Size"
        ) { 30.dp }

        Icon(
            imageVector = if (isChecked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size)
        )
    }
}

/**
 * This composable is the Sell FAB. It displays a FAB for
 * the user to sell a book.
 *
 * @see PostListingMBS
 */
@Composable
fun SellFAB() {
    var show by remember { mutableStateOf(false) }
    SmallFloatingActionButton(
        content = {
            Icon(
                Icons.Filled.Add,
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.background
            )
        },
        shape = CircleShape,
        contentColor = MaterialTheme.colorScheme.background,
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(65.dp),
        onClick = { show = true }
    )

    if (show) {
        PostListingMBS(onSheetDismissed = { show = false })
    }
}

/**
 * This composable is the Post Listing Modal Bottom Sheet. It displays a modal bottom sheet for
 * the user to post a listing.
 *
 * @param onSheetDismissed () -> Unit the function to call when the user dismisses the sheet
 * @param viewModel SellScreenViewModel the viewmodel for the screen
 *
 * @see SellScreenViewModel
 * @see PostListingForm
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PostListingMBS(
    onSheetDismissed: () -> Unit,
    viewModel: SellScreenViewModel = viewModel()
) {
    val message by viewModel.message.collectAsState()
    val loading by viewModel.loading.observeAsState(initial = false)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onSheetDismissed,
        sheetState = sheetState,
    ) {

        PostListingForm(viewModel, loading, message)
    }

    DisposableEffect(Unit) {
        onDispose {
        }
    }

}

/**
 * This composable is the Post Listing Form. It displays a form for
 * the user to post a listing.
 *
 * @param viewModel SellScreenViewModel the viewmodel for the screen
 * @param loading Boolean whether the form is loading
 * @param message String the message to display
 *
 * @see SellScreenViewModel
 * @see PostListingMBS
 * @see DestructiveActionDialog
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListingForm(
    viewModel: SellScreenViewModel,
    loading: Boolean,
    message: String?
) {
    val textStateISBN = remember { mutableStateOf(TextFieldValue()) } // ISBN Text-field value
    var isValidISBN by remember { mutableStateOf(true) }
    fun checkISBN(isbn: String): Boolean {
        return isbn.matches(Regex("^[0-9X]*\$"))
    }

    val textStatePrice = remember { mutableStateOf(TextFieldValue()) } // Price Text-field value
    var isValidPrice by remember { mutableStateOf(true) }
    fun checkPrice(price: String): Boolean {
        return price.matches(Regex("([0-9]*[.])?[0-9]+")) // field validation rege
    }

    var isConditionExpanded by remember { mutableStateOf(false) } // condition drop down state
    var selectedCondition by remember { mutableStateOf("") } // selection
    var isValidCondition by remember { mutableStateOf(true) }

    var isCategoryExpanded by remember { mutableStateOf(false) } // category drop down state
    var selectedCategory by remember { mutableStateOf("") } // selection
    var isValidCategory by remember { mutableStateOf(true) }

    var onFormConfirm by remember { mutableStateOf(false) }

    if (onFormConfirm) { // when form is submitted and confirm, reset values
        textStateISBN.value = TextFieldValue("")
        textStatePrice.value = TextFieldValue("")
        selectedCategory = ""
        selectedCondition = ""
    }

    Column {

        Text(
            text = "Create Textbook Listing",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp)
                .fillMaxWidth(),
            maxLines = 1,
        )
        OutlinedTextField(
            label = { ISBNTooltip() },
            enabled = true,
            value = textStateISBN.value,
            onValueChange = { input ->
                onFormConfirm = false
                textStateISBN.value = input
                isValidISBN = input.text.isNotEmpty() && checkISBN(input.text)
            },
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 15.dp
                )
                .fillMaxWidth(),
            isError = !isValidISBN,
            maxLines = 1,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close ISBN",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable { textStateISBN.value = TextFieldValue("") }
                        .size(20.dp)
                )
            },

            supportingText = {
                if (!isValidISBN) {
                    ErrToolTip(
                        message = "ISBN must not be empty and must only contain numbers",
                        contentDescription = "ISBN Error Tooltip"
                    )
                }
            },

            )

        // CATEGORY
        ExposedDropdownMenuBox(
            expanded = isCategoryExpanded,
            onExpandedChange = { newValue -> isCategoryExpanded = newValue },
            modifier = Modifier.padding(
                start = 20.dp,
                bottom = 15.dp,
                end = 20.dp,
                top = 15.dp
            ),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                readOnly = true,
                label = { Text("Category", fontSize = 15.sp) },
                value = selectedCategory,
                onValueChange = { input ->
                    onFormConfirm = false
                    isValidCategory = input.isNotEmpty()
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCategoryExpanded) },
                isError = !isValidCategory,
                maxLines = 1,
            )
            ExposedDropdownMenu(
                expanded = isCategoryExpanded,
                onDismissRequest = { isCategoryExpanded = false }
            ) {
                MCategory.categories.forEach { category ->
                    DropdownMenuItem(
                        content = {
                            Text(
                                category.toString(),
                                color = MaterialTheme.colorScheme.inverseSurface
                            )
                        },
                        onClick = {
                            selectedCategory = category.toString()
                            isCategoryExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
        Row {
            OutlinedTextField(
                label = { Text("Price", fontSize = 15.sp) },
                value = textStatePrice.value,
                onValueChange = { input ->
                    onFormConfirm = false
                    textStatePrice.value = input
                    isValidPrice = input.text.isNotEmpty() && checkPrice(input.text)
                },
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        bottom = 15.dp,
                        top = 15.dp
                    )
                    .fillMaxWidth(0.4f),
                isError = !isValidPrice,
                maxLines = 1,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Price",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clickable { textStatePrice.value = TextFieldValue("") }
                            .size(20.dp)
                    )

                },
                supportingText = {
                    if (!isValidPrice) {
                        ErrToolTip(
                            message = "Price must not be empty and only contain whole numbers or decimals",
                            contentDescription = "Price Error ToolTip"
                        )
                    }
                }
            )

            // TEXTBOOK CONDITION
            ExposedDropdownMenuBox(
                expanded = isConditionExpanded,
                onExpandedChange = { newValue -> isConditionExpanded = newValue },
                modifier = Modifier.padding(
                    top = 15.dp,
                    bottom = 15.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true,
                    label = { ConditionTooltip() },
                    value = selectedCondition,
                    onValueChange = { input ->
                        onFormConfirm = false
                        isValidCondition = input.isNotEmpty()
                    },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isConditionExpanded) },
                    isError = !isValidCondition,
                    maxLines = 1,
                )
                ExposedDropdownMenu(
                    expanded = isConditionExpanded,
                    onDismissRequest = { isConditionExpanded = false }
                ) {
                    MCondition.conditions.forEach { condition ->
                        DropdownMenuItem(
                            content = {
                                Text(
                                    condition.returnCondition(),
                                    color = MaterialTheme.colorScheme.inverseSurface
                                )
                            },
                            onClick = {
                                selectedCondition =
                                    condition.returnCondition(); isConditionExpanded =
                                false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )

                    }
                }
            }
        }
        SellSubmitButton(
            loading,
            viewModel,
            ListingSubmissionData(
                isbn = textStateISBN.value.text,
                price = textStatePrice.value.text,
                condition = selectedCondition,
                category = selectedCategory,

                isbnValid = isValidISBN,
                priceValid = isValidPrice,
                conditionValid = isValidCondition,
                categoryValid = isValidCategory
            )
        )

    }

    if (!message.isNullOrEmpty()) {
        if (message.contains("Error") || message.contains("No results")) {
            ConfirmDialog(
                title = "Oops..",
                content = "$message\n\nPlease try again.",
                isVisible = true,
                confirmButtonText = "Okay",
                onClick = { viewModel.reset() }
            )
        } else {
            ConfirmDialog(
                title = "Congratulations!",
                content = "$message\n\nClick anywhere outside the form to exit the editor or keep working.",
                isVisible = true,
                confirmButtonText = "Okay",
                onClick = { viewModel.reset(); onFormConfirm = true }
            )
        }
    }
}

/**
 * This composable is the Sell Submit Button. It displays a button for
 * the user to submit a listing.
 *
 * @param loading Boolean whether the form is loading
 * @param viewModel SellScreenViewModel the viewmodel for the screen
 * @param submissionData ListingSubmissionData the data to submits
 */
@Composable
fun SellSubmitButton(
    loading: Boolean,
    viewModel: SellScreenViewModel,
    submissionData: ListingSubmissionData
) {
    Button(
        content = {
            // get loading from view model, if loading set content to loading indicator
            if (loading) CircularProgressIndicator(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.size(10.dp),
                strokeWidth = 1.dp,
            )
            else {
                Text(
                    text = "Create",
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 16.sp
                )
            }
        },
        onClick = {
            viewModel.viewModelScope.launch {
                viewModel.createBookListing(
                    MBook(
                        isbn = submissionData.isbn,
                        condition = submissionData.condition,
                        price = submissionData.price.toDouble(),
                        mCategory = submissionData.category,
                    )
                )
            }
        },
        shape = MaterialTheme.shapes.large,
        enabled = (
                (submissionData.isbnValid && submissionData.isbn.isNotEmpty()) &&
                        (submissionData.priceValid && submissionData.price.isNotEmpty()) &&
                        (submissionData.categoryValid && submissionData.category.isNotEmpty()) &&
                        (submissionData.conditionValid && submissionData.condition.isNotEmpty())
                ),
        modifier = Modifier.padding(start = 20.dp, bottom = 20.dp)

    )
}

/**
 * This composable is the Destructive Action Dialog. It displays a dialog for
 * the user to confirm a destructive action.
 *
 * @param title String the title of the dialog
 * @param content String the content of the dialog
 * @param isVisible Boolean whether the dialog is visible
 * @param confirmButtonText String the text for the confirm button
 * @param onClick () -> Unit the function to call when the user clicks the button
 */
@Composable
fun ConfirmDialog(
    title: String,
    content: String,
    isVisible: Boolean,
    confirmButtonText: String,
    onClick: () -> Unit,
) {

    val (view, setView) = remember { mutableStateOf(isVisible) }

    if (view) {
        AlertDialog(
            onDismissRequest = { setView(false) },
            confirmButton = {
                TextButton(onClick = onClick,
                    content = {
                        Text(confirmButtonText, fontWeight = FontWeight.Bold)
                    }
                )
            },
            title = {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Text(
                    text = content,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            shape = MaterialTheme.shapes.medium
        )
    }
}

/**
 * ISBN Tooltip Composable that displays a tooltip for the user to see the ISBN.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ISBNTooltip() {
    Row {
        Text("ISBN", fontSize = 15.sp)
        PlainTooltipBox(
            tooltip = { Text("The rest of the book information will be populated using the ISBN.") },
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.HelpOutline,
                contentDescription = "ISBN Help Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp)
                    .tooltipAnchor()
                    .size(15.dp)
            )
        }
    }
}

/**
 * Condition Tooltip Composable that displays a tooltip for the user to see the condition.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConditionTooltip() {
    val scope = rememberCoroutineScope()
    val tooltipState by remember { mutableStateOf(RichTooltipState()) }
    Row {
        Text("Condition", fontSize = 15.sp)
        RichTooltipBox(
            title = { Text("Guide To Used Book Conditions") },
            text = { ConditionsDescriptions() },
            tooltipState = tooltipState,
            modifier = Modifier.padding(end = 30.dp),
            action =
            {
                TextButton(
                    onClick = {
                        scope.launch {
                            tooltipState.dismiss()
                        }
                    },
                    content = { Text("Close", fontWeight = FontWeight.Bold, fontSize = 15.sp) })
            },
        ) {
            Icon(
                imageVector = Icons.Default.HelpOutline,
                contentDescription = "Condition ToolTip Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp)
                    .tooltipAnchor()
                    .size(15.dp)
            )
        }
    }

}

/**
 * Error Tooltip Composable that displays a tooltip for the user to see the error.
 *
 * @param message String the message to display
 * @param contentDescription String the content description
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrToolTip(
    message: String,
    contentDescription: String
) {
    Row {
        PlainTooltipBox(
            tooltip = { Text(message) },
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ErrorOutline,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .padding(end = 5.dp, top = 2.dp)
                    .tooltipAnchor()
                    .size(15.dp)
            )
        }
        Text("View Errors")
    }
}

/**
 * This composable is the Conditions Descriptions. It displays a list of conditions
 */
@Composable
fun ConditionsDescriptions() {
    LazyColumn {
        MCondition.conditions.forEach { item ->
            item {
                Column {
                    Text(item.returnCondition(), fontWeight = FontWeight.Bold)
                    Text(item.returnDescription())
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }

}

/**
 * This composable is the Verification Dialog. It displays a dialog for
 * the user to verify their email.
 *
 * @param isVisible Boolean whether the dialog is visible
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationDialog(isVisible: Boolean) {
    val (view, setView) = remember { mutableStateOf(isVisible) }
    if(view) {
        AlertDialog(
            shape = MaterialTheme.shapes.medium,
            onDismissRequest = { setView(false) },
            confirmButton = {
                TextButton(onClick = { setView(false) })
                { Text("Okay", fontWeight = FontWeight.Bold) }
            },
            title = {
                Text("Check your email",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary) },
            text = {
                Text("We sent a verification link with instructions to the email you provided. " +
                        "Please open the email and follow the instructions to complete your registration",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary) }
        )
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomView(
    currentUser: MUser,
    senderId: String,
    recipientId: String,
    viewModel: ChatRoomViewModel,
    chatRoom: MChatRoom,
    chatRoomMessages: List<Message>
){
    var text by remember {mutableStateOf("")}
    val currentDate by remember {mutableStateOf(Date())}

    Column(
        content = {
            TopAppBar(
                title = {
                    Text(
                        text ="Title",
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    Text(text = "End Thread",
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 14.sp,
                    )
                    IconButton(onClick = {}){
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            contentDescription = "Close Conversation",
                            tint = MaterialTheme.colorScheme.secondaryContainer
                        )
                    }
                },
            )

            LazyColumn (
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxSize()
                    .weight(1f)
            ) {
                chatRoomMessages.forEach { message ->
                    item {
                        val isFromMe = message.senderId == currentUser.userId
                        Column(
                            horizontalAlignment = if(isFromMe) Alignment.End else Alignment.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 15.dp)
                        ) {
                            ChatBubble(isFromMe, message.messageText)
                        }
                    }
                }
            }

            BottomAppBar(
                backgroundColor = MaterialTheme.colorScheme.background,
                elevation = 0.dp,
                cutoutShape = CircleShape,
                modifier = Modifier
                    .height(70.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(start = 5.dp, bottom = 15.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    value = text,
                    onValueChange = { text = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Message,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Message Icon",
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Send,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Send Message Icon",
                            modifier = Modifier.clickable {
                                viewModel.writeMessage(
                                    chatRoomId = chatRoom.chatRoomId,
                                    senderId = senderId,
                                    recipientId = recipientId,
                                    timeStamp = currentDate,
                                    messageText = text,
                                )
                            }
                        )
                    }
                )
            }
        }
   )
}


@Composable
fun ChatBubble(isFromMe: Boolean, content: String){
    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 48f,
                    topEnd = 48f,
                    bottomStart = if (isFromMe) 48f else 0f,
                    bottomEnd = if (isFromMe) 0f else 48f
                )
            )
            .background(
                if (isFromMe) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondaryContainer
            )
            .padding(16.dp)
    ){
        Text(
            text = content,
            softWrap = true,
            maxLines = 10,
            fontSize = 12.sp,
            color = if(isFromMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}