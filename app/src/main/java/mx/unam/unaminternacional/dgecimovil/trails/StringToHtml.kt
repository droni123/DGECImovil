package mx.unam.unaminternacional.dgecimovil.trails

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import java.lang.Integer.min

object StringToHtml {
    private val tags = linkedMapOf(
        "<b>" to "</b>",
        "<i>" to "</i>",
        "<u>" to "</u>"
    )
    fun parseHtml(String:String): AnnotatedString {
        val newlineReplace = String.replace("<br>", "\n")

        return buildAnnotatedString {
            recurse(newlineReplace, this)
        }
    }
    private fun recurse(string: String, to: AnnotatedString.Builder) {
        val startTag = tags.keys.find { string.startsWith(it) }
        val endTag = tags.values.find { string.startsWith(it) }
        when {
            tags.any { string.startsWith(it.value) } -> {
                to.pop()
                recurse(string.removeRange(0, endTag!!.length), to)
            }
            tags.any { string.startsWith(it.key) } -> {
                to.pushStyle(tagToStyle(startTag!!))
                recurse(string.removeRange(0, startTag.length), to)
            }
            tags.any { string.contains(it.key) || string.contains(it.value) } -> {
                val firstStart = tags.keys.map { string.indexOf(it) }.filterNot { it == -1 }.minOrNull() ?: -1
                val firstEnd = tags.values.map { string.indexOf(it) }.filterNot { it == -1 }.minOrNull() ?: -1
                val first = when {
                    firstStart == -1 -> firstEnd
                    firstEnd == -1 -> firstStart
                    else -> min(firstStart, firstEnd)
                }
                to.append(string.substring(0, first))
                recurse(string.removeRange(0, first), to)
            } else -> {
                to.append(string)
            }
        }
    }
    private fun tagToStyle(tag: String): SpanStyle {
        return when (tag) {
            "<b>" -> {
                SpanStyle(fontWeight = FontWeight.Bold)
            }
            "<i>" -> {
                SpanStyle(fontStyle = FontStyle.Italic)
            }
            "<u>" -> {
                SpanStyle(textDecoration = TextDecoration.Underline)
            } else -> throw IllegalArgumentException("Tag $tag no es válido.")
        }
    }
}