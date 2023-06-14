package dev.glk.multiplatformsample

import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunch(
    val fairings: Fairings?
) {

    @Serializable
    data class Fairings(
        val reused: Boolean?
    )
}