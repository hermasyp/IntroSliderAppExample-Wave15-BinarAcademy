package com.catnip.introsliderappexample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Parcelize
data class SliderData(
    val title : String,
    val desc : String,
    val imgSlider : String
) : Parcelable
