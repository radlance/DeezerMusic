package com.radlance.deezermusic.app.navigation

import androidx.annotation.Keep
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.LibraryAddCheck
import androidx.compose.material.icons.outlined.LibraryAdd
import androidx.compose.material.icons.outlined.LibraryAddCheck
import androidx.compose.ui.graphics.vector.ImageVector
import com.radlance.deezermusic.R
import kotlinx.serialization.Serializable

interface Destination {
    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
    val labelResId: Int
}

@Keep
@Serializable
object ApiTracks : Destination {
    override val selectedIcon: ImageVector = Icons.Filled.LibraryAdd
    override val unselectedIcon: ImageVector = Icons.Outlined.LibraryAdd
    override val labelResId: Int = R.string.tracks
}

@Keep
@Serializable
object DownloadedTracks : Destination {
    override val selectedIcon: ImageVector = Icons.Filled.LibraryAddCheck
    override val unselectedIcon: ImageVector = Icons.Outlined.LibraryAddCheck
    override val labelResId: Int = R.string.downloaded
}