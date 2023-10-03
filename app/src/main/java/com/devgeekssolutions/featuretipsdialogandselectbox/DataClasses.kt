package com.devgeekssolutions.featuretipsdialogandselectbox

data class Tip(
    val id: Int,
    val tipValue: Int,
    var isSelected: Boolean,
    val isCustom: Boolean = false,
)