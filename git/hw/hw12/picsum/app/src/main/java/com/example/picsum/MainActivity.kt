package com.example.picsum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.picsum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var listImage = listOf(
            "https://i.picsum.photos/id/491/120/120.jpg?hmac=C4bSpb52FfbkI-FkhmuD5vwSniH510TCqhCvdqjcz80",
            "https://i.picsum.photos/id/1016/120/120.jpg?hmac=c2EzlBqRpHcX9b7Gqt4PWRIQVzMx9tiQPSEBPvQmg-s",
            "https://i.picsum.photos/id/599/120/120.jpg?hmac=h-wc13vpMeaLykg9XK13R4My9UWDW8eHu_ZeejqIiN4",
            "https://i.picsum.photos/id/336/120/120.jpg?hmac=B8J55zixEblQfwLQ509o_zAVHiVFBo9tNx1uOR3BEXg"
        )

        binding.listImages = listImage
        binding.context = this
    }
}