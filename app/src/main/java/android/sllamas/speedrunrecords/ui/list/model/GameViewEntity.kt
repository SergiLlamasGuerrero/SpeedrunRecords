package android.sllamas.speedrunrecords.ui.list.model

import android.os.Parcel
import android.os.Parcelable
import android.sllamas.domain.Game

data class GameViewEntity(
    val id: String,
    val name: String,
    val logoUrl: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(logoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameViewEntity> {
        override fun createFromParcel(parcel: Parcel): GameViewEntity {
            return GameViewEntity(parcel)
        }

        override fun newArray(size: Int): Array<GameViewEntity?> {
            return arrayOfNulls(size)
        }
    }
}

fun Game.transformToUi() = GameViewEntity(id, name, logoUrl)