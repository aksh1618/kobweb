package com.varabyte.kobweb.silk.components.layout

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.asAttributesBuilder
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.ComponentVariant
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.breakpoint.ResponsiveValues
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLElement

private const val MAX_COLUMN_COUNT = 4

val SimpleGridStyle = ComponentStyle.base("silk-simple-grid") {
    Modifier.display(DisplayStyle.Grid)
}

private val SimpleGridColumnVariants: Map<Breakpoint?, Map<Int, ComponentVariant>> = run {
    (listOf(null) + Breakpoint.values())
        .associateWith { breakpoint ->
            val name = breakpoint?.toString()?.lowercase() ?: "base"
            val variants = (0 until MAX_COLUMN_COUNT)
                .associate { i ->
                    val numColumns = i + 1
                    val gridModifier = Modifier.styleModifier {
                        gridTemplateColumns("repeat($numColumns, 1fr)")
                    }
                    numColumns to SimpleGridStyle.addVariant("$name-$numColumns") {
                        if (breakpoint == null) {
                            base { gridModifier }
                        } else {
                            breakpoint { gridModifier }
                        }
                    }
                }

            variants
        }
}

fun numColumns(base: Int, sm: Int = base, md: Int = sm, lg: Int = md, xl: Int = lg) =
    ResponsiveValues(base, sm, md, lg, xl)

/**
 * A widget making it easy to create a common case of responsive grids, specifically one where you simply specify the
 * number of columns and then its contents will flow to a new row automatically.
 *
 * Children of the Grid will be auto-slotted based on how many columns you specified
 *
 * ```
 * SimpleGrid(numColumns(2)) {
 *   Box(...) // Row 0, Col 0
 *   Box(...) // Row 0, Col 1
 *   Box(...) // Row 1, Col 0
 * }
 * ```
 *
 * The [numColumns] parameter actually takes accepts responsive values, so that the behavior can change as the
 * screen size changes:
 *
 * ```
 * SimpleGrid(numColumns(2, md = 3)) { ... }
 * ```
 *
 * Above, that will create a grid with two columns in smaller layouts (mobile, tablet) and 3 columns in larger ones
 * (desktop).
 */
@Composable
fun SimpleGrid(
    numColumns: ResponsiveValues<Int>,
    modifier: Modifier = Modifier,
    variant: ComponentVariant? = null,
    elementScope: (@Composable ElementScope<HTMLElement>.() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Div(
        attrs = SimpleGridStyle
            .toModifier(variant)
            // null is special case to mean "base" in this case
            .then(SimpleGridColumnVariants.getValue(null).getValue(numColumns.base).toModifier())
            .then(SimpleGridColumnVariants.getValue(Breakpoint.SM).getValue(numColumns.sm).toModifier().takeIf { numColumns.sm != numColumns.base } ?: Modifier)
            .then(SimpleGridColumnVariants.getValue(Breakpoint.MD).getValue(numColumns.md).toModifier().takeIf { numColumns.md != numColumns.sm } ?: Modifier)
            .then(SimpleGridColumnVariants.getValue(Breakpoint.LG).getValue(numColumns.lg).toModifier().takeIf { numColumns.lg != numColumns.md } ?: Modifier)
            .then(SimpleGridColumnVariants.getValue(Breakpoint.XL).getValue(numColumns.xl).toModifier().takeIf { numColumns.xl != numColumns.lg } ?: Modifier)
            .then(modifier)
            .asAttributesBuilder()
    ) {
        elementScope?.invoke(this)
        content()
    }
}