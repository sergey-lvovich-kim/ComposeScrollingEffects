package org.mikyegresl.composescrollanimations.ui

data class UserAvatar(
    val id: String,
    val url: String
)

internal sealed interface SelectAvatarViewState {

    /** Show loader to the user until data is loaded and available. */
    data object SelectAvatarInLoadingState : SelectAvatarViewState

    /** Show available data to the user.  */
    data class SelectAvatarInDataState(
        val avatars: List<UserAvatar>,
        val selectedAvatarId: String
    ) : SelectAvatarViewState
}

/** Actions performed on [SelectAvatarScreen]. */
internal data class SelectAvatarScreenActions(
    val onBackPressed: () -> Unit,
    val onSaveBtnPressed: (UserAvatar) -> Unit
)

