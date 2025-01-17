package com.varabyte.kobweb.compose.css

import org.jetbrains.compose.web.css.*

class AlignmentBaselinePosition(val value: String) {
    companion object {
        inline val First get() = AlignmentBaselinePosition("first")
        inline val Last get() = AlignmentBaselinePosition("last")
    }
}

class AlignmentOverflowStrategy(val value: String) {
    companion object {
        inline val Safe get() = AlignmentOverflowStrategy("safe")
        inline val Unsafe get() = AlignmentOverflowStrategy("unsafe")
    }
}


// region JustifySelf

// See https://developer.mozilla.org/en-US/docs/Web/CSS/justify-self
class JustifySelf(val value: String) {
    class BaselinePosition(val value: String) {
        companion object {
            inline val First get() = BaselinePosition("first")
            inline val Last get() = BaselinePosition("last")
        }
    }

    class OverflowStrategy(val value: String) {
        companion object {
            inline val Safe get() = OverflowStrategy("safe")
            inline val Unsafe get() = OverflowStrategy("unsafe")
        }
    }

    companion object {
        // Basic
        inline val Auto get() = JustifySelf("auto")
        inline val Normal get() = JustifySelf("normal")
        inline val Stretch get() = JustifySelf("stretch")

        // Positional
        inline val Center get() = JustifySelf("center")
        inline val Start get() = JustifySelf("start")
        inline val End get() = JustifySelf("end")
        inline val FlexStart get() = JustifySelf("flex-start")
        inline val FlexEnd get() = JustifySelf("flex-end")
        inline val SelfStart get() = JustifySelf("self-start")
        inline val SelfEnd get() = JustifySelf("self-end")
        inline val Left get() = JustifySelf("left")
        inline val Right get() = JustifySelf("right")

        // Baseline
        inline val Baseline get() = JustifySelf("baseline")

        // Global
        inline val Inherit get() = JustifySelf("inherit")
        inline val Initial get() = JustifySelf("initial")
        inline val Revert get() = JustifySelf("revert")
        inline val Unset get() = JustifySelf("unset")
    }
}

fun StyleBuilder.justifySelf(justifySelf: JustifySelf) {
    property("justify-self", justifySelf.value)
}

fun StyleBuilder.justifySelf(baseline: AlignmentBaselinePosition) {
    property("justify-self", "${baseline.value} baseline")
}

fun StyleBuilder.justifySelf(overflow: AlignmentOverflowStrategy, position: JustifySelf) {
    property("justify-self", "${overflow.value} ${position.value}")
}

// endregion

// region JustifySelf

// See https://developer.mozilla.org/en-US/docs/Web/CSS/justify-self
class JustifyItems(val value: String) {
    companion object {
        // Basic
        inline val Normal get() = JustifySelf("normal")
        inline val Stretch get() = JustifySelf("stretch")

        // Positional
        inline val Center get() = JustifySelf("center")
        inline val Start get() = JustifySelf("start")
        inline val End get() = JustifySelf("end")
        inline val FlexStart get() = JustifySelf("flex-start")
        inline val FlexEnd get() = JustifySelf("flex-end")
        inline val SelfStart get() = JustifySelf("self-start")
        inline val SelfEnd get() = JustifySelf("self-end")
        inline val Left get() = JustifySelf("left")
        inline val Right get() = JustifySelf("right")

        // Baseline
        inline val Baseline get() = JustifySelf("baseline")

        // Global
        inline val Inherit get() = JustifySelf("inherit")
        inline val Initial get() = JustifySelf("initial")
        inline val Revert get() = JustifySelf("revert")
        inline val Unset get() = JustifySelf("unset")
    }
}

fun StyleBuilder.justifyItems(justifyItems: JustifyItems) {
    property("justify-items", justifyItems.value)
}

fun StyleBuilder.justifyItems(baseline: AlignmentBaselinePosition) {
    property("justify-items", "${baseline.value} baseline")
}

fun StyleBuilder.justifyItems(overflow: AlignmentOverflowStrategy, position: JustifySelf) {
    property("justify-items", "${overflow.value} ${position.value}")
}

// endregion

fun StyleBuilder.placeContent(alignContent: AlignContent, justifyContent: JustifyContent) {
    property("place-content", "${alignContent.value} ${justifyContent.value}")
}

fun StyleBuilder.placeSelf(alignSelf: AlignSelf, justifySelf: JustifySelf) {
    placeSelf( "${alignSelf.value} ${justifySelf.value}")
}