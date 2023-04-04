package com.heistdotcom.kotlinaudio.players

import android.content.Context
import com.heistdotcom.kotlinaudio.models.BufferConfig
import com.heistdotcom.kotlinaudio.models.CacheConfig
import com.heistdotcom.kotlinaudio.models.PlayerConfig

class AudioPlayer(context: Context, playerConfig: PlayerConfig = PlayerConfig(), bufferConfig: BufferConfig? = null, cacheConfig: CacheConfig? = null): BaseAudioPlayer(context, playerConfig, bufferConfig, cacheConfig)