package com.dewecod.mvvmhilt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dewecod.mvvmhilt.R
import com.dewecod.mvvmhilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isFavoriteListVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
            .commit()

        binding.changeFragment.setOnClickListener {
            if (isFavoriteListVisible) {
                isFavoriteListVisible = false
                binding.changeFragment.text = "Favorites"
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
                    .commit()
            } else {
                /*isFavoriteListVisible = true
                binding.changeFragment.text = "Home"
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, Favorites())
                    .commit()*/
            }
        }
    }
}