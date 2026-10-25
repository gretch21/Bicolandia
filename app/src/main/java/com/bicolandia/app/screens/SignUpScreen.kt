package com.bicolandia.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bicolandia.app.R
import com.bicolandia.app.ui.theme.*

@Composable
fun SignUpScreen(
  onBack: () -> Unit,
  onSignedUp: () -> Unit,
  onRegister: (String, String, String) -> Boolean = { _, _, _ -> true }
) {
  var name by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var confirm by remember { mutableStateOf("") }
  var error by remember { mutableStateOf("") }
  var showConfirmation by remember { mutableStateOf(false) }

  val fieldColors = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = BicolCoral,
    unfocusedBorderColor = Color(0xFFE3E5E8),
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White
  )

  Scaffold(containerColor = BicolBg) { padding ->
    Column(
      Modifier
        .fillMaxSize()
        .padding(padding)
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 28.dp)
    ) {
      Spacer(Modifier.height(36.dp))
      Image(
        painter = painterResource(id = R.drawable.logo_full),
        contentDescription = "Bicolandia",
        contentScale = ContentScale.Fit,
        modifier = Modifier.height(80.dp).align(Alignment.CenterHorizontally)
      )
      Spacer(Modifier.height(22.dp))
      Text("Create an account", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = BicolTextPrimary)
      Spacer(Modifier.height(4.dp))
      Text("Sign up to start planning your Bicol trip.", color = BicolTextSecondary, fontSize = 13.5.sp)

      Spacer(Modifier.height(24.dp))
      Text("FULL NAME", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
      Spacer(Modifier.height(6.dp))
      OutlinedTextField(
        value = name, onValueChange = { name = it; error = "" },
        placeholder = { Text("Juan Dela Cruz", fontSize = 13.5.sp) },
        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null, tint = BicolTextSecondary) },
        singleLine = true, shape = RoundedCornerShape(14.dp), colors = fieldColors,
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(Modifier.height(16.dp))
      Text("EMAIL", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
      Spacer(Modifier.height(6.dp))
      OutlinedTextField(
        value = email, onValueChange = { email = it; error = "" },
        placeholder = { Text("you@email.com", fontSize = 13.5.sp) },
        leadingIcon = { Icon(Icons.Filled.MailOutline, contentDescription = null, tint = BicolTextSecondary) },
        singleLine = true, shape = RoundedCornerShape(14.dp), colors = fieldColors,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(Modifier.height(16.dp))
      Text("PASSWORD", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
      Spacer(Modifier.height(6.dp))
      OutlinedTextField(
        value = password, onValueChange = { password = it; error = "" },
        placeholder = { Text("••••••••", fontSize = 13.5.sp) },
        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = BicolTextSecondary) },
        singleLine = true, shape = RoundedCornerShape(14.dp), colors = fieldColors,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(Modifier.height(16.dp))
      Text("CONFIRM PASSWORD", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 11.sp)
      Spacer(Modifier.height(6.dp))
      OutlinedTextField(
        value = confirm, onValueChange = { confirm = it; error = "" },
        placeholder = { Text("••••••••", fontSize = 13.5.sp) },
        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = BicolTextSecondary) },
        singleLine = true, shape = RoundedCornerShape(14.dp), colors = fieldColors,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
      )

      if (error.isNotEmpty()) {
        Spacer(Modifier.height(10.dp))
        Text(error, color = BicolCoral, fontSize = 12.sp)
      }

      Spacer(Modifier.height(24.dp))
      Button(
        onClick = {
          error = when {
            name.isBlank() || email.isBlank() || password.isBlank() || confirm.isBlank() -> "Please fill in all fields."
            password != confirm -> "Passwords do not match."
            else -> ""
          }
          if (error.isEmpty()) {
            if (onRegister(name, email, password)) showConfirmation = true
            else error = "An account with this email already exists."
          }
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BicolCoral),
        modifier = Modifier.fillMaxWidth().height(52.dp)
      ) {
        Text("Sign Up", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.White)
      }

      Spacer(Modifier.height(24.dp))
      Row(Modifier.fillMaxWidth().padding(bottom = 28.dp), horizontalArrangement = Arrangement.Center) {
        Text("Already have an account? ", color = BicolTextSecondary, fontSize = 13.sp)
        Text("Log in", color = BicolCoral, fontWeight = FontWeight.Bold, fontSize = 13.sp, modifier = Modifier.clickable { onBack() })
      }
    }
  }

  if (showConfirmation) {
    AlertDialog(
      onDismissRequest = { showConfirmation = false; onSignedUp() },
      title = { Text("Account created!", fontWeight = FontWeight.Bold) },
      text = { Text("Your account has been created successfully. Please log in to continue.") },
      confirmButton = {
        TextButton(onClick = { showConfirmation = false; onSignedUp() }) {
          Text("OK", color = BicolCoral, fontWeight = FontWeight.Bold)
        }
      }
    )
  }
}
