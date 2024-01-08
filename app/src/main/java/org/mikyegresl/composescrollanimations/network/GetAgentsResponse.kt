package org.mikyegresl.composescrollanimations.network

import com.google.gson.annotations.SerializedName

data class GetAgentsResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: AgentResponse
) {
    data class AgentResponse(
        @SerializedName("uuid")
        val uuid: String? = null,
        @SerializedName("displayName")
        val displayName: String? = null,
        @SerializedName("displayIcon")
        val displayIcon: String? = null,
        @SerializedName("backgroundGradientColors")
        val backgroundGradientColors: List<String>? = null
    )
}