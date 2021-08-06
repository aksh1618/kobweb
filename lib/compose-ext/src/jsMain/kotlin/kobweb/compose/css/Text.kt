package kobweb.compose.css

import org.jetbrains.compose.web.css.StyleBuilder

class TextAlign(val value: String) {
    companion object {
        val Left get() = TextAlign("left")
        val Right get() = TextAlign("right")
        val Center get() = TextAlign("center")
        val Justify get() = TextAlign("justify")
        val JustifyAll get() = TextAlign("justify-all")
        val Start get() = TextAlign("start")
        val End get() = TextAlign("end")
        val MatchParent get() = TextAlign("match-parent")
    }
}

fun StyleBuilder.textAlign(textAlign: TextAlign) {
    property("text-align", textAlign.value)
}

class TextDecorationLine(val value: String) {
    companion object {
        val Underline get() = TextDecorationLine("underline")
        val Overline get() = TextDecorationLine("overline")
        val None get() = TextDecorationLine("none")

        val Inherit get() = TextDecorationLine("inherit")
        val Initial get() = TextDecorationLine("initial")
        val Revert get() = TextDecorationLine("revert")
        val Unset get() = TextDecorationLine("unset")
    }
}

fun StyleBuilder.textDecorationLine(textDecorationLine: TextDecorationLine) {
    property("text-decoration-line", textDecorationLine.value)
}