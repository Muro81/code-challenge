package com.lukamurisic.lumiform_code_challenge.core.utils

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

/**
  Object containing predefined keyboard configurations for various input types in Compose.

  This utility object provides commonly used keyboard configurations combining different
  keyboard types (Text, Number, Phone, etc.) with IME actions (Next, Done).
  Each configuration is provided as a Composable function returning KeyboardOptions.

  Available Keyboard Configurations:
  - Text input with Next action
  - Text input with Done action
  - Email input with Next action
  - Numeric input with Next action
  - Numeric input with Done action
  - Phone number input with Next action
  - Phone number input with Done action
  - Password input with Next action
  - Password input with Done action

  Also includes a standard KeyboardActions configuration for handling focus management.

  Example Usage:
  ```
  TextField(
      value = text,
      onValueChange = { text = it },
      keyboardOptions = KeyboardType.textKeyboardNext(),
      keyboardActions = KeyboardType.keyboardActions()
  )
  ```
 **/

object KeyboardType {
    val textKeyboardNext: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    }

    val textKeyboardDone: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    }

    val textKeyboardEmailNext: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    }


    val numericKeyboardNext: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    }

    val numericKeyboardDone: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        )
    }

    val phoneNumberKeyboardNext: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        )
    }

    val phoneNumberKeyboardDone: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        )
    }

    val passwordKeyboardNext: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        )
    }

    val passwordKeyboardDone: @Composable () -> KeyboardOptions = {
        KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    }


    val keyboardActions: @Composable () -> KeyboardActions = {
        val focusManager = LocalFocusManager.current
        KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onPrevious = { focusManager.moveFocus(FocusDirection.Up) }
        )
    }
}