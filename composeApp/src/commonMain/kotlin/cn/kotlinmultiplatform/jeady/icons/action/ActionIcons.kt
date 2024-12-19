package cn.kotlinmultiplatform.jeady.icons.action

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

public val Icons.Filled.OpenInNew: ImageVector
    get() {
        if (_openInNew != null) {
            return _openInNew!!
        }
        _openInNew = materialIcon(name = "Filled.OpenInNew") {
            materialPath {
                moveTo(19f, 19f)
                horizontalLineTo(5f)
                verticalLineTo(5f)
                horizontalLineTo(12f)
                verticalLineTo(3f)
                horizontalLineTo(5f)
                curveToRelative(-1.11f, 0f, -2f, 0.9f, -2f, 2f)
                verticalLineTo(19f)
                curveToRelative(0f, 1.1f, 0.89f, 2f, 2f, 2f)
                horizontalLineTo(19f)
                curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                verticalLineTo(12f)
                horizontalLineTo(19f)
                verticalLineTo(19f)
                close()
                moveTo(14f, 3f)
                verticalLineTo(5f)
                horizontalLineTo(17.59f)
                lineTo(7.76f, 14.83f)
                lineTo(9.17f, 16.24f)
                lineTo(19f, 6.41f)
                verticalLineTo(10f)
                horizontalLineTo(21f)
                verticalLineTo(3f)
                horizontalLineTo(14f)
                close()
            }
        }
        return _openInNew!!
    }
private var _openInNew: ImageVector? = null 

public val Icons.Filled.Publish: ImageVector
    get() {
        if (_publish != null) {
            return _publish!!
        }
        _publish = materialIcon(name = "Filled.Publish") {
            materialPath {
                moveTo(5f, 4f)
                verticalLineToRelative(2f)
                horizontalLineToRelative(14f)
                verticalLineToRelative(-2f)
                horizontalLineTo(5f)
                close()
                moveTo(5f, 14f)
                horizontalLineToRelative(4f)
                verticalLineToRelative(6f)
                horizontalLineToRelative(6f)
                verticalLineToRelative(-6f)
                horizontalLineToRelative(4f)
                lineToRelative(-7f, -7f)
                lineToRelative(-7f, 7f)
                close()
            }
        }
        return _publish!!
    }
private var _publish: ImageVector? = null

val Icons.Filled.Help: ImageVector
    get() {
        if (_help != null) {
            return _help!!
        }
        _help = materialIcon(name = "Help") {
            materialPath {
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveToRelative(4.48f, 10f, 10f, 10f)
                reflectiveCurveToRelative(10f, -4.48f, 10f, -10f)
                reflectiveCurveTo(17.52f, 2f, 12f, 2f)
                close()
                moveTo(13f, 19f)
                horizontalLineToRelative(-2f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(2f)
                close()
                moveTo(15.07f, 11.25f)
                lineToRelative(-0.9f, 0.92f)
                curveTo(13.45f, 12.9f, 13f, 13.5f, 13f, 15f)
                horizontalLineToRelative(-2f)
                verticalLineToRelative(-0.5f)
                curveToRelative(0f, -1.1f, 0.45f, -2.1f, 1.17f, -2.83f)
                lineToRelative(1.24f, -1.26f)
                curveToRelative(0.37f, -0.36f, 0.59f, -0.86f, 0.59f, -1.41f)
                curveToRelative(0f, -1.1f, -0.9f, -2f, -2f, -2f)
                reflectiveCurveToRelative(-2f, 0.9f, -2f, 2f)
                horizontalLineTo(8f)
                curveToRelative(0f, -2.21f, 1.79f, -4f, 4f, -4f)
                reflectiveCurveToRelative(4f, 1.79f, 4f, 4f)
                curveToRelative(0f, 0.88f, -0.36f, 1.68f, -0.93f, 2.25f)
                close()
            }
        }
        return _help!!
    }

private var _help: ImageVector? = null 