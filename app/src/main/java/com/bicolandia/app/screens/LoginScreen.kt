package com.bicolandia.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.R
import com.bicolandia.app.ui.theme.*

@Composable
fun LoginScreen(
  onContinue: () -> Unit,
  onCreateAccount: () -> Unit = {},
  onLogin: (String, String) -> Boolean = { _, _ -> true }
) {
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var passwordVisible by remember { mutableStateOf(false) }
  var error by remember { mutableStateOf("") }

  Scaffold(containerColor = BicolBg) { padding ->
    Column(
      Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(horizontal = 28.dp)
    ) {
      Spacer(Modifier.height(48.dp))

      Image(
        painter = painterResource(id = R.drawable.logo_full),
        contentDescription = "Bicolandia",
        contentScale = ContentScale.Fit,
        modifier = Modifier.height(90.dp).align(Alignment.CenterHorizontally)
      )

      Spacer(Modifier.height(28.dp))
      Text("Welcome back", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = BicolTextPrimary)
      Spacer(Modifier.height(4.dp))
      Text("Sign in to keep planning your Bicol trip.", color = BicolTextSecondary, fontSize = 13.5.sp)

      Spacer(Modifier.height(32.dp))

      Text("EMAIL", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
      Spacer(Modifier.height(6.dp))
      OutlinedTextField(
        value = email,
        onValueChange = { email = it; error = "" },
        placeholder = { Text("you@email.com", fontSize = 13.5.sp) },
        leadingIcon = { Icon(Icons.Filled.MailOutline, contentDescription = null, tint = BicolTextSecondary) },
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = BicolCoral,
          unfocusedBorderColor = Color(0xFFE3E5E8),
          focusedContainerColor = Color.White,
          unfocusedContainerColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(Modifier.height(18.dp))

      Text("PASSWORD", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
      Spacer(Modifier.height(6.dp))
      OutlinedTextField(
        value = password,
        onValueChange = { password = it; error = "" },
        placeholder = { Text("••••••••", fontSize = 13.5.sp) },
        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = BicolTextSecondary) },
        trailingIcon = {
          Icon(
            if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
            contentDescription = null, tint = BicolTextSecondary,
            modifier = Modifier.clickable { passwordVisible = !passwordVisible }
          )
        },
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = BicolCoral,
          unfocusedBorderColor = Color(0xFFE3E5E8),
          focusedContainerColor = Color.White,
          unfocusedContainerColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
      )

      if (error.isNotEmpty()) {
        Spacer(Modifier.height(10.dp))
        Text(error, color = BicolCoral, fontSize = 12.sp)
      }

      Spacer(Modifier.height(24.dp))

      Button(
        onClick = {
          if (email.isBlank() || password.isBlank()) error = "Please enter your email and password."
          else if (onLogin(email, password)) onContinue()
          else error = "Incorrect email or password, or no account yet. Tap Create an account below."
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BicolCoral),
        modifier = Modifier.fillMaxWidth().height(52.dp)
      ) {
        Text("Log In", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.White)
      }

      Spacer(Modifier.weight(1f))

      Row(Modifier.fillMaxWidth().padding(bottom = 28.dp), horizontalArrangement = Arrangement.Center) {
        Text("New to Bicolandia? ", color = BicolTextSecondary, fontSize = 13.sp)
        Text(
          "Create an account", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 13.sp,
          modifier = Modifier.clickable { onCreateAccount() }
        )
      }
    }
  }
}
