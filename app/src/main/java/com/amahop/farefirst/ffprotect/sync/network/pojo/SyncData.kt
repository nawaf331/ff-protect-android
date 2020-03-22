package com.amahop.farefirst.ffprotect.sync.network.pojo

import com.amahop.farefirst.ffprotect.sync.network.Location
import com.amahop.farefirst.ffprotect.tracker.db.Tracker

data class SyncData(
    val clientUid: String, // This value is used only for reference
    val location: Location?,
    val fcmToken: String?,
    val trackers: Array<Tracker>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SyncData

        if (location != other.location) return false
        if (fcmToken != other.fcmToken) return false
        if (!trackers.contentEquals(other.trackers)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = location?.hashCode() ?: 0
        result = 31 * result + (fcmToken?.hashCode() ?: 0)
        result = 31 * result + trackers.contentHashCode()
        return result
    }
}