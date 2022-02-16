package com.example.sobes2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Results(val results: List<NewsData>?) : Parcelable

@Parcelize
data class Image(val image: String) : Parcelable

@Parcelize
data class NewsData(val title: String, val description: String, val images: List<Image>) : Parcelable