package com.heistdotcom.kotlinaudio.models

data class BufferConfig(
    val minBuffer: Int?,
    val maxBuffer: Int?,
    val playBuffer: Int?,
    val backBuffer: Int?,
)
