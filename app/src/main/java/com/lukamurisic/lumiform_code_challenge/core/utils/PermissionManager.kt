package com.lukamurisic.lumiform_code_challenge.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

/**
A simple and powerful permission manager for handling runtime permissions in Jetpack Compose.

This manager simplifies permission requests, rationale handling, and state tracking.
 **/
class PermissionManager(
    private val context: Context
) {
    /**
    Checks if the given permission is already granted.
    @param permission The permission to check (e.g., Manifest.permission.CAMERA).
    @return `true` if granted, `false` otherwise.
    8*/
    fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context, permission
        ) == PermissionChecker.PERMISSION_GRANTED
    }

    /**
    Opens the app's settings page to allow the user to grant permissions manually.
     */
    fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}

/**
A composable function to request a single permission in Jetpack Compose.

@param permission The permission to request (e.g., Manifest.permission.CAMERA).
@param onPermissionResult A callback that receives `true` if granted, `false` otherwise.
 **/
@Composable
fun RequestPermission(
    permission: String,
    onPermissionResult: (Boolean) -> Unit
) {
    val permissionState = remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionState.value = isGranted
        onPermissionResult(isGranted)
    }

    if (!permissionState.value) {
        permissionLauncher.launch(permission)
    }
}

/**
A composable function to request multiple permissions in Jetpack Compose.

@param permissions A list of permissions to request.
@param onPermissionsResult A callback that returns a map of permission results.
 **/
@Composable
fun RequestMultiplePermissions(
    permissions: List<String>,
    onPermissionsResult: (Map<String, Boolean>) -> Unit
) {
    val permissionsState = remember { mutableStateOf(mapOf<String, Boolean>()) }

    val multiplePermissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        permissionsState.value = results
        onPermissionsResult(results)
    }

    if (permissionsState.value.isEmpty()) {
        multiplePermissionsLauncher.launch(permissions.toTypedArray())
    }
}
