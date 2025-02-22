package com.heistdotcom.kotlinaudio.event

import com.heistdotcom.kotlinaudio.models.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerEventHolder {
    private val coroutineScope = MainScope()

    private var _stateChange = MutableSharedFlow<AudioPlayerState>(1)
    var stateChange = _stateChange.asSharedFlow()

    private var _playbackEnd = MutableSharedFlow<PlaybackEndedReason?>(1)
    var playbackEnd = _playbackEnd.asSharedFlow()

    private var _playbackError = MutableSharedFlow<PlaybackError>(1)
    var playbackError = _playbackError.asSharedFlow()

    private var _playWhenReadyChange = MutableSharedFlow<PlayWhenReadyChangeData>(1)
    /**
     * Use these events to track when [com.heistdotcom.kotlinaudio.players.BaseAudioPlayer.playWhenReady]
     * changes.
     */
    var playWhenReadyChange = _playWhenReadyChange.asSharedFlow()

    private var _audioItemTransition = MutableSharedFlow<AudioItemTransitionReason?>(1)

    /**
     * Use these events to track when and why an [AudioItem] transitions to another.
     *
     * Examples of an audio transition include changes to [AudioItem] queue, an [AudioItem] on repeat, skipping an [AudioItem], or simply when the [AudioItem] has finished.
     */
    var audioItemTransition = _audioItemTransition.asSharedFlow()

    private var _positionChanged = MutableSharedFlow<PositionChangedReason?>(1)
    var positionChanged = _positionChanged.asSharedFlow()

    private var _onAudioFocusChanged = MutableSharedFlow<FocusChangeData>(1)
    var onAudioFocusChanged = _onAudioFocusChanged.asSharedFlow()

    private var _onPlaybackMetadata = MutableSharedFlow<PlaybackMetadata>(1)
    var onPlaybackMetadata = _onPlaybackMetadata.asSharedFlow()

    private var _onPlayerActionTriggeredExternally = MutableSharedFlow<MediaSessionCallback>()

    /**
     * Use these events to track whenever a player action has been triggered from an outside source.
     *
     * The sources can be: media buttons on headphones, Android Wear, Android Auto, Google Assistant, media notification, etc.
     *
     * For this observable to send events, set [interceptPlayerActionsTriggeredExternally][com.heistdotcom.kotlinaudio.models.PlayerConfig.interceptPlayerActionsTriggeredExternally] to true.
    */
    var onPlayerActionTriggeredExternally = _onPlayerActionTriggeredExternally.asSharedFlow()

    internal fun updateAudioPlayerState(state: AudioPlayerState) {
        coroutineScope.launch {
            _stateChange.emit(state)
        }
    }

    internal fun updatePlaybackEndedReason(reason: PlaybackEndedReason) {
        coroutineScope.launch {
            _playbackEnd.emit(reason)
        }
    }

    internal fun updatePlayWhenReadyChange(playWhenReadyChange: PlayWhenReadyChangeData) {
        coroutineScope.launch {
            _playWhenReadyChange.emit(playWhenReadyChange)
        }
    }

    internal fun updateAudioItemTransition(reason: AudioItemTransitionReason) {
        coroutineScope.launch {
            _audioItemTransition.emit(reason)
        }
    }

    internal fun updatePositionChangedReason(reason: PositionChangedReason) {
        coroutineScope.launch {
            _positionChanged.emit(reason)
        }
    }

    internal fun updateOnAudioFocusChanged(isPaused: Boolean, isPermanent: Boolean) {
        coroutineScope.launch {
            _onAudioFocusChanged.emit(FocusChangeData(isPaused, isPermanent))
        }
    }

    internal fun updateOnPlaybackMetadata(metadata: PlaybackMetadata) {
        coroutineScope.launch {
            _onPlaybackMetadata.emit(metadata)
        }
    }

    internal fun updatePlaybackError(error: PlaybackError) {
        coroutineScope.launch {
            _playbackError.emit(error)
        }
    }

    internal fun updateOnPlayerActionTriggeredExternally(callback: MediaSessionCallback) {
        coroutineScope.launch {
            _onPlayerActionTriggeredExternally.emit(callback)
        }
    }
}