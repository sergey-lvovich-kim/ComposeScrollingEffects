package org.mikyegresl.composescrollanimations.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AgentDto(
    val uuid: String,
    val displayName: String,
    val displayIcon: String,
    val backgroundGradientColors: List<String>,
): Parcelable