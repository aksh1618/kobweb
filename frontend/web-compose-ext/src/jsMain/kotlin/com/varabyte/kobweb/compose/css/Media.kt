package com.varabyte.kobweb.compose.css

import org.jetbrains.compose.web.css.*

// See: https://developer.mozilla.org/en-US/docs/Web/CSS/object-fit
class ObjectFit(val value: String) {
    companion object {
        // Keywords
        val Contain get() = ObjectFit("contain")
        val Cover get() = ObjectFit("cover")
        val Fill get() = ObjectFit("fill")
        val None get() = ObjectFit("none")
        val ScaleDown get() = ObjectFit("scale-down")

        // Global values
        val Inherit get() = ObjectFit("inherit")
        val Initial get() = ObjectFit("initial")
        val Revert get() = ObjectFit("revert")
        val Unset get() = ObjectFit("unset")
    }
}

fun StyleBuilder.objectFit(objectFit: ObjectFit) {
    property("object-fit", objectFit.value)
}