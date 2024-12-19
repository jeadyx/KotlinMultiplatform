package cn.kotlinmultiplatform.jeady.icons.device

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

public val Icons.Filled.DesktopWindows: ImageVector
    get() {
        if (_desktopWindows != null) {
            return _desktopWindows!!
        }
        _desktopWindows = materialIcon(name = "Filled.DesktopWindows") {
            materialPath {
                moveTo(21f, 2f)
                horizontalLineTo(3f)
                curveToRelative(-1.1f, 0f, -2f, 0.9f, -2f, 2f)
                verticalLineTo(16f)
                curveToRelative(0f, 1.1f, 0.9f, 2f, 2f, 2f)
                horizontalLineTo(10f)
                lineToRelative(-2f, 3f)
                verticalLineTo(22f)
                horizontalLineTo(16f)
                verticalLineTo(21f)
                lineToRelative(-2f, -3f)
                horizontalLineTo(21f)
                curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                verticalLineTo(4f)
                curveToRelative(0f, -1.1f, -0.9f, -2f, -2f, -2f)
                close()
                moveTo(21f, 16f)
                horizontalLineTo(3f)
                verticalLineTo(4f)
                horizontalLineTo(21f)
                verticalLineTo(16f)
                close()
            }
        }
        return _desktopWindows!!
    }
private var _desktopWindows: ImageVector? = null

public val Icons.Filled.Apple: ImageVector
    get() {
        if (_apple != null) {
            return _apple!!
        }
        _apple = materialIcon(name = "Filled.Apple") {
            materialPath {
                moveTo(17.05f, 20.28f)
                curveToRelative(-0.98f, 1.38f, -2.03f, 1.72f, -3.08f, 1.72f)
                curveToRelative(-1.08f, 0f, -1.92f, -0.34f, -2.97f, -0.34f)
                curveToRelative(-1.08f, 0f, -1.92f, 0.34f, -3f, 0.34f)
                curveToRelative(-1.22f, 0f, -2.17f, -0.44f, -3.13f, -1.75f)
                curveToRelative(-2.14f, -3.05f, -2.38f, -8.84f, -0.99f, -12.77f)
                curveToRelative(0.91f, -2.62f, 2.54f, -4.13f, 4.32f, -4.13f)
                curveToRelative(1.36f, 0f, 2.21f, 0.44f, 3.33f, 0.44f)
                curveToRelative(1.09f, 0f, 1.76f, -0.44f, 3.33f, -0.44f)
                curveToRelative(1.47f, 0f, 2.99f, 1.12f, 3.89f, 3.05f)
                curveToRelative(-3.43f, 1.89f, -2.87f, 6.84f, 0.53f, 8.15f)
                curveToRelative(-0.61f, 1.96f, -1.42f, 3.93f, -2.23f, 5.73f)
                close()
                moveTo(12.56f, 0.81f)
                curveToRelative(0.71f, -0.83f, 1.17f, -1.97f, 1.04f, -3.12f)
                curveToRelative(-1.01f, 0.03f, -2.21f, 0.65f, -2.92f, 1.45f)
                curveToRelative(-0.63f, 0.69f, -1.17f, 1.81f, -1.02f, 2.89f)
                curveToRelative(1.11f, 0.09f, 2.24f, -0.52f, 2.9f, -1.22f)
                close()
            }
        }
        return _apple!!
    }
private var _apple: ImageVector? = null

public val Icons.Filled.Android: ImageVector
    get() {
        if (_android != null) {
            return _android!!
        }
        _android = materialIcon(name = "Filled.Android") {
            materialPath {
                moveTo(6f, 18f)
                curveToRelative(0f, 0.55f, 0.45f, 1f, 1f, 1f)
                horizontalLineTo(17f)
                curveToRelative(0.55f, 0f, 1f, -0.45f, 1f, -1f)
                verticalLineTo(8f)
                horizontalLineTo(6f)
                verticalLineTo(18f)
                close()
                moveTo(7.5f, 10f)
                horizontalLineTo(9.5f)
                verticalLineTo(16f)
                horizontalLineTo(7.5f)
                verticalLineTo(10f)
                close()
                moveTo(14.5f, 10f)
                horizontalLineTo(16.5f)
                verticalLineTo(16f)
                horizontalLineTo(14.5f)
                verticalLineTo(10f)
                close()
                moveTo(12f, 3f)
                horizontalLineTo(5f)
                curveToRelative(-1.1f, 0f, -2f, 0.9f, -2f, 2f)
                verticalLineTo(7f)
                horizontalLineTo(19f)
                verticalLineTo(5f)
                curveToRelative(0f, -1.1f, -0.9f, -2f, -2f, -2f)
                horizontalLineTo(12f)
                close()
                moveTo(12f, 1.5f)
                curveToRelative(0.83f, 0f, 1.5f, 0.67f, 1.5f, 1.5f)
                reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f)
                reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f)
                reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f)
                close()
            }
        }
        return _android!!
    }
private var _android: ImageVector? = null 