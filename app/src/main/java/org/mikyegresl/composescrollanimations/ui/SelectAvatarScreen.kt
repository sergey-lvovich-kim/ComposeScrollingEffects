package org.mikyegresl.composescrollanimations.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
internal fun SelectAvatarScreen(
    state: State<SelectAvatarViewState>,
    actions: SelectAvatarScreenActions
) {
    var isButtonEnabled by remember { mutableStateOf(false) }
    when (val viewState = state.value) {
        is SelectAvatarViewState.SelectAvatarInLoadingState -> {
            isButtonEnabled = false
            PregnancyTrackerProgress(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(40.dp),
                color = AmmaTheme.colors.text.accent
            )
        }

        is SelectAvatarViewState.SelectAvatarInDataState -> {
            isButtonEnabled = true
            ChangeUserAvatarInLoadedState(
                modifier = Modifier.align(Alignment.TopCenter),
                selectedAvatarId = viewState.selectedAvatarId,
                avatars = immutableListOf(viewState.avatarUrls),
                onCloseClick = actions.onCloseClick,
                onAvatarSelected = actions.onAvatarSelected
            )
        }
    }
}

@Composable
internal fun BoxScope.ChangeUserAvatarScreenHeader(
    onCloseClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(start = 16.dp, end = 16.dp, top = 24.dp),
        text = stringResource(id = R.string.profile_choose_avatar),
        style = AmmaTheme.typography.titles.mediumTitle.copy(fontWeight = FontWeight.Bold),
        textAlign = TextAlign.Center
    )
    IconButton(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(start = 16.dp, end = 16.dp),
        onClick = onCloseClick,
    ) {
        Image(
            painter = painterResource(id = ru.mobiledimension.kbr.core.compose.R.drawable.ic_close),
            contentDescription = "Close",
        )
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
internal fun ChangeUserAvatarInLoadedState(
    modifier: Modifier = Modifier,
    selectedAvatarId: String?,
    avatars: ImmutableList<AvailableUserAvatar>,
    onCloseClick: () -> Unit,
    onAvatarSelected: (String) -> Unit
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val scrollOffset = with(LocalDensity.current) {
        120.dp.toPx()
    }.toInt()

    val itemIndexInFocus = remember(selectedAvatarId) {
        derivedStateOf {
            val firstVisibleIndex = listState.firstVisibleItemIndex
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            val lastVisibleIndex = lastVisibleItem?.index ?: 0
            val lastIndex = listState.layoutInfo.totalItemsCount - 1

            /** We pick middle item among visible items. */

            /** We pick middle item among visible items. */

            /** We pick middle item among visible items. */

            /** We pick middle item among visible items. */
            when (val currentIndex = (firstVisibleIndex + lastVisibleIndex) / 2) {
                /** if middle visible item's index is 1
                 * and if the offset of the first item is greater than
                 * 20% of the screen width -> select 1st index
                 * otherwise -> select 0th item index. */
                /** if middle visible item's index is 1
                 * and if the offset of the first item is greater than
                 * 20% of the screen width -> select 1st index
                 * otherwise -> select 0th item index. */
                /** if middle visible item's index is 1
                 * and if the offset of the first item is greater than
                 * 20% of the screen width -> select 1st index
                 * otherwise -> select 0th item index. */
                /** if middle visible item's index is 1
                 * and if the offset of the first item is greater than
                 * 20% of the screen width -> select 1st index
                 * otherwise -> select 0th item index. */
                1 -> {
                    val calculatedIndex = listState.firstVisibleItemScrollOffset.toFloat() /
                            listState.layoutInfo.viewportSize.width*100
                    if (calculatedIndex > 20) currentIndex else 0
                }
                /** if middle visible item's index is pre-last
                 * and if the offset of the last item is smaller than
                 * or equals to 60% of the screen width -> select last index
                 * otherwise -> select pre-last item index. */
                /** if middle visible item's index is pre-last
                 * and if the offset of the last item is smaller than
                 * or equals to 60% of the screen width -> select last index
                 * otherwise -> select pre-last item index. */
                /** if middle visible item's index is pre-last
                 * and if the offset of the last item is smaller than
                 * or equals to 60% of the screen width -> select last index
                 * otherwise -> select pre-last item index. */
                /** if middle visible item's index is pre-last
                 * and if the offset of the last item is smaller than
                 * or equals to 60% of the screen width -> select last index
                 * otherwise -> select pre-last item index. */
                lastIndex - 1 -> {
                    val calculatedIndex = (lastVisibleItem?.offset ?: currentIndex).toFloat() /
                            listState.layoutInfo.viewportSize.width * 100
                    if (calculatedIndex <= 60) lastIndex else currentIndex
                }
                else -> currentIndex
            }
        }
    }
    LaunchedEffect(key1 = selectedAvatarId) {
        scope.launch {
            val savedIndex = avatars.indexOfFirst { it.id == selectedAvatarId }
            if (savedIndex >= 0) {
                /** passing offset of the negative element size because
                 * derivedState calculations may interfere with the side-effects. */
                /** passing offset of the negative element size because
                 * derivedState calculations may interfere with the side-effects. */
                listState.scrollToItem(
                    index = savedIndex,
                    scrollOffset = -scrollOffset
                )
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        ChangeUserAvatarScreenHeader(onCloseClick = onCloseClick)

        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            LazyRow(
                modifier = Modifier.align(Alignment.Center),
                state = listState,
                contentPadding = PaddingValues(start = 14.dp, end = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(
                    items = avatars,
                    key = { _, item -> item.id },
                ) { i, item ->
                    val isSelected = itemIndexInFocus.value == i

                    if (i == 0) { Spacer(modifier = Modifier.width(40.dp)) }
                    UserAvatarItem(
                        modifier = Modifier,
                        avatarItem = item,
                        isSelected = isSelected
                    )
                    Spacer(
                        modifier = if (i == avatars.lastIndex) {
                            Modifier.width(40.dp)
                        } else Modifier.width(28.dp)
                    )
                }
            }
        }
        PrimaryButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
            text = stringResource(id = R.string.change_user_avatar_action_select),
            onButtonClick = {
                val selectedIndex = itemIndexInFocus.value
                if (selectedIndex < avatars.size) {
                    onAvatarSelected(avatars[selectedIndex].id)
                }
            }
        )
    }
}

@Composable
internal fun UserAvatarItem(
    modifier: Modifier = Modifier,
    avatarItem: UserAvatar,
    isSelected: Boolean
) {
    val borderWidth by animateDpAsState(targetValue = if (isSelected) 3.dp else 0.dp, label = "")
    val cardSize by animateDpAsState(targetValue = if (isSelected) 189.dp else 120.dp, label = "")
    val borderAlpha by animateFloatAsState(targetValue = if (isSelected) 1f else 0f, label = "")
    val padding by animateDpAsState(targetValue = if (isSelected) 15.dp else 0.dp, label = "")

    Card(
        modifier = modifier.size(cardSize),
        shape = CircleShape,
        border = BorderStroke(
            borderWidth,
            MaterialTheme.colorScheme..copy(
                alpha = borderAlpha
            )
        ),
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(padding)
                .clip(CircleShape),
            model = avatarItem.absoluteUrl,
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(id = R.string.profile_choose_avatar)
        )
    }
}
