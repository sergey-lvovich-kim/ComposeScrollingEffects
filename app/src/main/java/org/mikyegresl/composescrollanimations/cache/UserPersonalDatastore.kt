package org.mikyegresl.composescrollanimations.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.mikyegresl.composescrollanimations.ui.UserAvatar
import java.util.concurrent.Flow

class UserPersonalDatastore(
    private val datastore: DataStore<Preferences>
) {

    suspend fun saveSelectedUserAvatar(avatar: UserAvatar) {
        datastore.updateData {

        }
    }

    suspend fun getSelectedUserAvatar(): Flow<UserAvatar> {

    }
}