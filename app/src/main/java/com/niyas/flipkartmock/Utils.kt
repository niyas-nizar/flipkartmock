package com.niyas.flipkartmock

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.navigate(
    fragment: Fragment,
    addToBackStack: Boolean = true
) {

    val rootView: ViewGroup = findViewById(android.R.id.content)
    val container = rootView.findViewById<FrameLayout>(R.id.container)
        ?: throw Throwable("Activity FrameLayout id needs to be \"container\"")
    add(fragment, container.id, addToBackStack)
}

fun AppCompatActivity.replace(
    fragment: Fragment, isAnimation: Boolean = true
) {

    val rootView: ViewGroup = findViewById(android.R.id.content)
    val container = rootView.findViewById<FrameLayout>(R.id.container)
        ?: throw Throwable("Activity FrameLayout id needs to be \"container\"")
    if (isAnimation)
        replace(fragment, container.id)
    else
        replaceWithoutAnim(fragment, container.id)

}

fun AppCompatActivity.replace(
    fragment: Fragment,
    @IdRes container: Int,
    backStackName: String = ""
) {
    supportFragmentManager.transact {
        replace(container, fragment, backStackName)
    }
}

fun AppCompatActivity.replaceWithoutAnim(
    fragment: Fragment,
    @IdRes container: Int,
    addToBackStack: Boolean = false,
    backStackName: String = ""
) {
    supportFragmentManager.transact {
        replace(container, fragment, backStackName)
        if (addToBackStack) addToBackStack(backStackName)
    }
}

fun AppCompatActivity.add(
    fragment: Fragment,
    @IdRes container: Int,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.transact {
        add(container, fragment, fragment::class.java.simpleName)
        if (addToBackStack) addToBackStack(fragment::class.java.simpleName)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commitAllowingStateLoss()
}

fun AppCompatActivity.dismissKeyboard() {
    val inputManager: InputMethodManager = this
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val currentFocusedView: View? = this.currentFocus
    if (currentFocusedView != null) {
        inputManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun Context.dismissKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.hasPermission(vararg permissions: String): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        permissions.all { singlePermission ->
            this.checkSelfPermission(singlePermission) == PackageManager.PERMISSION_GRANTED
        }
    else true
}





