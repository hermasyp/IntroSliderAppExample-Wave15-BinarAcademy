package com.catnip.introsliderappexample.utils

import androidx.viewpager2.widget.ViewPager2

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun ViewPager2.getNextIndex(): Int {
    val maxPages = this.adapter?.itemCount // 3
    val currentIndex = this.currentItem // 2
    var selectedIndex = -1
    maxPages?.let {
        if (currentIndex + 1 < maxPages) {
            selectedIndex = currentIndex + 1
        }
    }
    return selectedIndex
}

fun ViewPager2.getPreviousIndex(): Int {
    val currentPage = this.currentItem //2
    return if (currentPage - 1 >= 0) {
        currentPage - 1
    } else {
        -1
    }
}