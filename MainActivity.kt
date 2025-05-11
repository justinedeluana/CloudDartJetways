package com.example.clouddartjetways

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var menuButton: ImageView
    private var popupWindow: PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        menuButton = findViewById(R.id.menuButton)
        menuButton.setOnClickListener {
            toggleMenu()
        }
    }

    private fun toggleMenu() {
        if (popupWindow == null || !popupWindow!!.isShowing) {
            openMenu()
        } else {
            closeMenu()
        }
    }

    private fun openMenu() {
        // Load the menu layout
        val inflater = LayoutInflater.from(this)
        val menuView = inflater.inflate(R.layout.menu_layout, null)

        // Create a PopupWindow
        popupWindow = PopupWindow(menuView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true)

        // Find the close button in the menu layout
        val closeButton: ImageButton = menuView.findViewById(R.id.btnClose)

        // Set click listener to close the PopupWindow
        closeButton.setOnClickListener {
            closeMenu()
        }

        // Show the PopupWindow
        popupWindow!!.showAtLocation(menuButton, 1, 1, 1)

        // Add animation to the menu button
        val animation = AnimationUtils.loadAnimation(this, R.anim.menu_button_animation)
        menuButton.startAnimation(animation)
    }

    private fun closeMenu() {
        popupWindow?.dismiss()
        popupWindow = null
    }
}
