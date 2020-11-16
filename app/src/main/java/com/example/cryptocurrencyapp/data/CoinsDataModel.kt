package com.example.cryptocurrencyapp.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CoinsDataModel(
    val coins: List<CoinItemModel>
)

data class CoinItemModel(
    val item: TrendCoinsModel
)

data class TrendCoinsModel(
    val id: String?,
    val name: String?,
    val symbol: String?,
    val market_cap_rank: Int,
    val thumb: String?,
    val large: String?,
    val score: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(symbol)
        parcel.writeInt(market_cap_rank)
        parcel.writeString(thumb)
        parcel.writeString(large)
        parcel.writeInt(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrendCoinsModel> {
        override fun createFromParcel(parcel: Parcel): TrendCoinsModel {
            return TrendCoinsModel(parcel)
        }

        override fun newArray(size: Int): Array<TrendCoinsModel?> {
            return arrayOfNulls(size)
        }
    }

}