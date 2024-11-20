package com.example.assignment2

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment2.ui.theme.Assignment2Theme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")

            } else {
                Log.i("Permission: ", "Denied")
            }
        }

    private fun showPermissionRationale() {
        AlertDialog.Builder(this)
            .setTitle("Permission Required")
            .setMessage(
                "Activity 2 Requires that Permission MSE412 is allowed. Please Allow the permission."
            )
            .setPositiveButton("OK") { _, _ ->
                // Request permission after showing the rationale
                requestPermissionLauncher.launch("com.example.assignment2.MSE412")
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(this, "Permission is required to proceed", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun requestPermission() {

        when {
            ContextCompat.checkSelfPermission(
                this,
                "com.example.assignment2.permission.MSE412"
            ) == PackageManager.PERMISSION_GRANTED -> {

            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this, "com.example.assignment2.permission.MSE412"
            ) -> {
                showPermissionRationale()

            }

            else -> {
                requestPermissionLauncher.launch(
                    "com.example.assignment2.permission.MSE412"
                )

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme {
                requestPermission()
                Assignment2MainApp()
            }
        }
    }

    @Composable
    fun Assignment2MainApp() {
        FullNameAndID()

    }

    @Composable
    fun FullNameAndID(
                      modifier: Modifier = Modifier.fillMaxSize()
                          .wrapContentSize(Alignment.TopCenter)
    ) {
        val context = LocalContext.current
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(stringResource(R.string.student_name), Modifier.padding(30.dp))
            Text(stringResource(R.string.student_id), Modifier.padding(10.dp))
            Row {
                Button(onClick = {

                    val expIntent = Intent(context, Activity2::class.java)
                    context.startActivity(expIntent)

                }) {
                    Text("Start Activity Explicitly")
                }
                Button(onClick = {
                    val implicitIntent = Intent("com.example.assignment2.SHOW_ACTIVITY2").apply {
                        addCategory(Intent.CATEGORY_DEFAULT)
                    }
                    context.startActivity(implicitIntent)
                }) {
                    Text("Start Activity Implicitly")
                }
            }
            Button(onClick = {
                val expIntent = Intent(context, ImageActivity::class.java)
                context.startActivity(expIntent)
            }) {
                Text("View Image Activity") // Correct the button label if needed
            }

        }
    }
}
