package cn.kotlinmultiplatform.jeady.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MarkdownPreview(
    content: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        val linkColor = MaterialTheme.colors.primary
        Text(
            text = buildAnnotatedString {
                // Simple Markdown rendering
                content.split("\n").forEach { line ->
                    when {
                        line.startsWith("# ") -> {
                            pushStyle(SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ))
                            append(line.substring(2))
                            pop()
                        }
                        line.startsWith("## ") -> {
                            pushStyle(SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ))
                            append(line.substring(3))
                            pop()
                        }
                        line.startsWith("### ") -> {
                            pushStyle(SpanStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ))
                            append(line.substring(4))
                            pop()
                        }
                        line.startsWith("- ") -> {
                            append("• ${line.substring(2)}")
                        }
                        line.startsWith("```") -> {
                            pushStyle(SpanStyle(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                background = MaterialTheme.colors.surface
                            ))
                            append(line.substring(3))
                            pop()
                        }
                        else -> {
                            // Handle inline styles
                            var text = line
                            // Bold
                            text = text.replace(Regex("\\*\\*(.*?)\\*\\*")) { matchResult ->
                                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                                append(matchResult.groupValues[1])
                                pop()
                                ""
                            }
                            // Italic
                            text = text.replace(Regex("\\*(.*?)\\*")) { matchResult ->
                                pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
                                append(matchResult.groupValues[1])
                                pop()
                                ""
                            }
                            // Links
                            text = text.replace(Regex("\\[(.*?)\\]\\((.*?)\\)")) { matchResult ->
                                pushStyle(SpanStyle(
                                    color = linkColor,
                                    textDecoration = TextDecoration.Underline
                                ))
                                append(matchResult.groupValues[1])
                                pop()
                                ""
                            }
                            if (text.isNotEmpty()) {
                                append(text)
                            }
                        }
                    }
                    append("\n")
                }
            }
        )
    }
} 