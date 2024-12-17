package cn.kotlinmultiplatform.jeady.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Filled.CustomCode: ImageVector
    get() {
        if (_customCode != null) {
            return _customCode!!
        }
        _customCode = ImageVector.Builder(
            name = "CustomCode",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(9.4f, 16.6f)
                lineTo(4.8f, 12f)
                lineTo(9.4f, 7.4f)
                lineTo(8f, 6f)
                lineTo(2f, 12f)
                lineTo(8f, 18f)
                lineTo(9.4f, 16.6f)
                close()
                moveTo(14.6f, 16.6f)
                lineTo(19.2f, 12f)
                lineTo(14.6f, 7.4f)
                lineTo(16f, 6f)
                lineTo(22f, 12f)
                lineTo(16f, 18f)
                lineTo(14.6f, 16.6f)
                close()
            }
        }.build()
        return _customCode!!
    }
private var _customCode: ImageVector? = null

val Icons.Filled.CustomFormatListBulleted: ImageVector
    get() {
        if (_customFormatListBulleted != null) {
            return _customFormatListBulleted!!
        }
        _customFormatListBulleted = ImageVector.Builder(
            name = "CustomFormatListBulleted",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(4f, 10.5f)
                curveTo(3.17f, 10.5f, 2.5f, 11.17f, 2.5f, 12f)
                reflectiveCurveTo(3.17f, 13.5f, 4f, 13.5f)
                reflectiveCurveTo(5.5f, 12.83f, 5.5f, 12f)
                reflectiveCurveTo(4.83f, 10.5f, 4f, 10.5f)
                close()
                moveTo(4f, 4.5f)
                curveTo(3.17f, 4.5f, 2.5f, 5.17f, 2.5f, 6f)
                reflectiveCurveTo(3.17f, 7.5f, 4f, 7.5f)
                reflectiveCurveTo(5.5f, 6.83f, 5.5f, 6f)
                reflectiveCurveTo(4.83f, 4.5f, 4f, 4.5f)
                close()
                moveTo(4f, 16.5f)
                curveTo(3.17f, 16.5f, 2.5f, 17.17f, 2.5f, 18f)
                reflectiveCurveTo(3.17f, 19.5f, 4f, 19.5f)
                reflectiveCurveTo(5.5f, 18.83f, 5.5f, 18f)
                reflectiveCurveTo(4.83f, 16.5f, 4f, 16.5f)
                close()
                moveTo(7f, 19f)
                horizontalLineTo(21f)
                verticalLineTo(17f)
                horizontalLineTo(7f)
                verticalLineTo(19f)
                close()
                moveTo(7f, 13f)
                horizontalLineTo(21f)
                verticalLineTo(11f)
                horizontalLineTo(7f)
                verticalLineTo(13f)
                close()
                moveTo(7f, 5f)
                verticalLineTo(7f)
                horizontalLineTo(21f)
                verticalLineTo(5f)
                horizontalLineTo(7f)
                close()
            }
        }.build()
        return _customFormatListBulleted!!
    }
private var _customFormatListBulleted: ImageVector? = null

val Icons.Filled.CustomTitle: ImageVector
    get() {
        if (_customTitle != null) {
            return _customTitle!!
        }
        _customTitle = ImageVector.Builder(
            name = "CustomTitle",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(5f, 4f)
                verticalLineTo(7f)
                horizontalLineTo(10.5f)
                verticalLineTo(19f)
                horizontalLineTo(13.5f)
                verticalLineTo(7f)
                horizontalLineTo(19f)
                verticalLineTo(4f)
                horizontalLineTo(5f)
                close()
            }
        }.build()
        return _customTitle!!
    }
private var _customTitle: ImageVector? = null

val Icons.Filled.CustomFormatItalic: ImageVector
    get() {
        if (_customFormatItalic != null) {
            return _customFormatItalic!!
        }
        _customFormatItalic = ImageVector.Builder(
            name = "CustomFormatItalic",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(10f, 4f)
                verticalLineTo(7f)
                horizontalLineTo(12.21f)
                lineTo(8.79f, 15f)
                horizontalLineTo(6f)
                verticalLineTo(18f)
                horizontalLineTo(14f)
                verticalLineTo(15f)
                horizontalLineTo(11.79f)
                lineTo(15.21f, 7f)
                horizontalLineTo(18f)
                verticalLineTo(4f)
                horizontalLineTo(10f)
                close()
            }
        }.build()
        return _customFormatItalic!!
    }
private var _customFormatItalic: ImageVector? = null

val Icons.Filled.CustomFormatBold: ImageVector
    get() {
        if (_customFormatBold != null) {
            return _customFormatBold!!
        }
        _customFormatBold = ImageVector.Builder(
            name = "CustomFormatBold",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(15.6f, 10.79f)
                curveTo(16.57f, 10.12f, 17.25f, 9.02f, 17.25f, 8f)
                curveTo(17.25f, 5.74f, 15.5f, 4f, 13.25f, 4f)
                horizontalLineTo(7f)
                verticalLineTo(18f)
                horizontalLineTo(14.04f)
                curveTo(16.13f, 18f, 17.75f, 16.3f, 17.75f, 14.21f)
                curveTo(17.75f, 12.69f, 16.89f, 11.39f, 15.6f, 10.79f)
                close()
                moveTo(10f, 6.5f)
                horizontalLineTo(13f)
                curveTo(13.83f, 6.5f, 14.5f, 7.17f, 14.5f, 8f)
                reflectiveCurveTo(13.83f, 9.5f, 13f, 9.5f)
                horizontalLineTo(10f)
                verticalLineTo(6.5f)
                close()
                moveTo(13.5f, 15.5f)
                horizontalLineTo(10f)
                verticalLineTo(12.5f)
                horizontalLineTo(13.5f)
                curveTo(14.33f, 12.5f, 15f, 13.17f, 15f, 14f)
                reflectiveCurveTo(14.33f, 15.5f, 13.5f, 15.5f)
                close()
            }
        }.build()
        return _customFormatBold!!
    }
private var _customFormatBold: ImageVector? = null

val Icons.Filled.CustomLink: ImageVector
    get() {
        if (_customLink != null) {
            return _customLink!!
        }
        _customLink = ImageVector.Builder(
            name = "CustomLink",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(3.9f, 12f)
                curveTo(3.9f, 10.29f, 5.29f, 8.9f, 7f, 8.9f)
                horizontalLineTo(11f)
                verticalLineTo(7f)
                horizontalLineTo(7f)
                curveTo(4.24f, 7f, 2f, 9.24f, 2f, 12f)
                reflectiveCurveTo(4.24f, 17f, 7f, 17f)
                horizontalLineTo(11f)
                verticalLineTo(15.1f)
                horizontalLineTo(7f)
                curveTo(5.29f, 15.1f, 3.9f, 13.71f, 3.9f, 12f)
                close()
                moveTo(8f, 13f)
                horizontalLineTo(16f)
                verticalLineTo(11f)
                horizontalLineTo(8f)
                verticalLineTo(13f)
                close()
                moveTo(17f, 7f)
                horizontalLineTo(13f)
                verticalLineTo(8.9f)
                horizontalLineTo(17f)
                curveTo(18.71f, 8.9f, 20.1f, 10.29f, 20.1f, 12f)
                curveTo(20.1f, 13.71f, 18.71f, 15.1f, 17f, 15.1f)
                horizontalLineTo(13f)
                verticalLineTo(17f)
                horizontalLineTo(17f)
                curveTo(19.76f, 17f, 22f, 14.76f, 22f, 12f)
                reflectiveCurveTo(19.76f, 7f, 17f, 7f)
                close()
            }
        }.build()
        return _customLink!!
    }
private var _customLink: ImageVector? = null

val Icons.Filled.CustomSchedule: ImageVector
    get() {
        if (_customSchedule != null) {
            return _customSchedule!!
        }
        _customSchedule = ImageVector.Builder(
            name = "CustomSchedule",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(11.99f, 2f)
                curveTo(6.47f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveTo(4.47f, 22f, 9.99f, 22f)
                curveTo(15.52f, 22f, 20f, 17.52f, 20f, 12f)
                reflectiveCurveTo(15.52f, 2f, 11.99f, 2f)
                close()
                moveTo(12f, 20f)
                curveTo(7.58f, 20f, 4f, 16.42f, 4f, 12f)
                reflectiveCurveTo(7.58f, 4f, 12f, 4f)
                reflectiveCurveTo(20f, 7.58f, 20f, 12f)
                reflectiveCurveTo(16.42f, 20f, 12f, 20f)
                close()
                moveTo(12.5f, 7f)
                horizontalLineTo(11f)
                verticalLineTo(13f)
                lineTo(16.25f, 16.15f)
                lineTo(17f, 14.92f)
                lineTo(12.5f, 12.25f)
                verticalLineTo(7f)
                close()
            }
        }.build()
        return _customSchedule!!
    }
private var _customSchedule: ImageVector? = null

val Icons.Filled.CustomVisibility: ImageVector
    get() {
        if (_customVisibility != null) {
            return _customVisibility!!
        }
        _customVisibility = ImageVector.Builder(
            name = "CustomVisibility",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 4.5f)
                curveTo(7f, 4.5f, 2.73f, 7.61f, 1f, 12f)
                curveTo(2.73f, 16.39f, 7f, 19.5f, 12f, 19.5f)
                reflectiveCurveTo(21.27f, 16.39f, 23f, 12f)
                curveTo(21.27f, 7.61f, 17f, 4.5f, 12f, 4.5f)
                close()
                moveTo(12f, 17f)
                curveTo(9.24f, 17f, 7f, 14.76f, 7f, 12f)
                reflectiveCurveTo(9.24f, 7f, 12f, 7f)
                reflectiveCurveTo(17f, 9.24f, 17f, 12f)
                reflectiveCurveTo(14.76f, 17f, 12f, 17f)
                close()
                moveTo(12f, 9f)
                curveTo(10.34f, 9f, 9f, 10.34f, 9f, 12f)
                reflectiveCurveTo(10.34f, 15f, 12f, 15f)
                reflectiveCurveTo(15f, 13.66f, 15f, 12f)
                reflectiveCurveTo(13.66f, 9f, 12f, 9f)
                close()
            }
        }.build()
        return _customVisibility!!
    }
private var _customVisibility: ImageVector? = null

val Icons.Filled.CustomGitHub: ImageVector
    get() {
        if (_customGitHub != null) {
            return _customGitHub!!
        }
        _customGitHub = ImageVector.Builder(
            name = "CustomGitHub",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 2f)
                curveTo(6.475f, 2f, 2f, 6.475f, 2f, 12f)
                curveTo(2f, 16.425f, 4.8625f, 20.1625f, 8.8375f, 21.4875f)
                curveTo(9.3375f, 21.575f, 9.525f, 21.275f, 9.525f, 21.0125f)
                curveTo(9.525f, 20.775f, 9.5125f, 19.9875f, 9.5125f, 19.15f)
                curveTo(7f, 19.6125f, 6.35f, 18.5375f, 6.15f, 17.975f)
                curveTo(6.0375f, 17.6875f, 5.55f, 16.8f, 5.125f, 16.5625f)
                curveTo(4.775f, 16.375f, 4.275f, 15.9125f, 5.1125f, 15.9f)
                curveTo(5.9f, 15.8875f, 6.4625f, 16.625f, 6.65f, 16.925f)
                curveTo(7.55f, 18.4375f, 8.9875f, 18.0125f, 9.5625f, 17.75f)
                curveTo(9.65f, 17.1f, 9.9125f, 16.6625f, 10.2f, 16.4125f)
                curveTo(7.975f, 16.1625f, 5.65f, 15.3f, 5.65f, 11.475f)
                curveTo(5.65f, 10.3875f, 6.0375f, 9.4875f, 6.675f, 8.7875f)
                curveTo(6.575f, 8.5375f, 6.225f, 7.5125f, 6.775f, 6.1375f)
                curveTo(6.775f, 6.1375f, 7.6125f, 5.875f, 9.525f, 7.1625f)
                curveTo(10.325f, 6.9375f, 11.175f, 6.825f, 12.025f, 6.825f)
                curveTo(12.875f, 6.825f, 13.725f, 6.9375f, 14.525f, 7.1625f)
                curveTo(16.4375f, 5.8625f, 17.275f, 6.1375f, 17.275f, 6.1375f)
                curveTo(17.825f, 7.5125f, 17.475f, 8.5375f, 17.375f, 8.7875f)
                curveTo(18.0125f, 9.4875f, 18.4f, 10.375f, 18.4f, 11.475f)
                curveTo(18.4f, 15.3125f, 16.0625f, 16.1625f, 13.8375f, 16.4125f)
                curveTo(14.2f, 16.725f, 14.5125f, 17.325f, 14.5125f, 18.2625f)
                curveTo(14.5125f, 19.6f, 14.5f, 20.675f, 14.5f, 21.0125f)
                curveTo(14.5f, 21.275f, 14.6875f, 21.5875f, 15.1875f, 21.4875f)
                curveTo(19.1375f, 20.1625f, 22f, 16.425f, 22f, 12f)
                curveTo(22f, 6.475f, 17.525f, 2f, 12f, 2f)
                close()
            }
        }.build()
        return _customGitHub!!
    }
private var _customGitHub: ImageVector? = null

val Icons.Filled.CustomBugReport: ImageVector
    get() {
        if (_customBugReport != null) {
            return _customBugReport!!
        }
        _customBugReport = ImageVector.Builder(
            name = "CustomBugReport",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20f, 8f)
                horizontalLineTo(17.19f)
                curveTo(16.74f, 7.22f, 16.12f, 6.55f, 15.37f, 6.04f)
                lineTo(17f, 4.41f)
                lineTo(15.59f, 3f)
                lineTo(13.42f, 5.17f)
                curveTo(12.96f, 5.06f, 12.49f, 5f, 12f, 5f)
                curveTo(11.51f, 5f, 11.04f, 5.06f, 10.59f, 5.17f)
                lineTo(8.41f, 3f)
                lineTo(7f, 4.41f)
                lineTo(8.62f, 6.04f)
                curveTo(7.88f, 6.55f, 7.26f, 7.22f, 6.81f, 8f)
                horizontalLineTo(4f)
                verticalLineTo(10f)
                horizontalLineTo(6.09f)
                curveTo(6.04f, 10.33f, 6f, 10.66f, 6f, 11f)
                verticalLineTo(12f)
                horizontalLineTo(4f)
                verticalLineTo(14f)
                horizontalLineTo(6f)
                verticalLineTo(15f)
                curveTo(6f, 15.34f, 6.04f, 15.67f, 6.09f, 16f)
                horizontalLineTo(4f)
                verticalLineTo(18f)
                horizontalLineTo(6.81f)
                curveTo(7.85f, 19.79f, 9.78f, 21f, 12f, 21f)
                reflectiveCurveTo(16.15f, 19.79f, 17.19f, 18f)
                horizontalLineTo(20f)
                verticalLineTo(16f)
                horizontalLineTo(17.91f)
                curveTo(17.96f, 15.67f, 18f, 15.34f, 18f, 15f)
                verticalLineTo(14f)
                horizontalLineTo(20f)
                verticalLineTo(12f)
                horizontalLineTo(18f)
                verticalLineTo(11f)
                curveTo(18f, 10.66f, 17.96f, 10.33f, 17.91f, 10f)
                horizontalLineTo(20f)
                verticalLineTo(8f)
                close()
                moveTo(14f, 16f)
                horizontalLineTo(10f)
                verticalLineTo(14f)
                horizontalLineTo(14f)
                verticalLineTo(16f)
                close()
                moveTo(14f, 12f)
                horizontalLineTo(10f)
                verticalLineTo(10f)
                horizontalLineTo(14f)
                verticalLineTo(12f)
                close()
            }
        }.build()
        return _customBugReport!!
    }
private var _customBugReport: ImageVector? = null

val Icons.Filled.CustomEmail: ImageVector
    get() {
        if (_customEmail != null) {
            return _customEmail!!
        }
        _customEmail = ImageVector.Builder(
            name = "CustomEmail",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20f, 4f)
                horizontalLineTo(4f)
                curveTo(2.9f, 4f, 2.01f, 4.9f, 2.01f, 6f)
                lineTo(2f, 18f)
                curveTo(2f, 19.1f, 2.9f, 20f, 4f, 20f)
                horizontalLineTo(20f)
                curveTo(21.1f, 20f, 22f, 19.1f, 22f, 18f)
                verticalLineTo(6f)
                curveTo(22f, 4.9f, 21.1f, 4f, 20f, 4f)
                close()
                moveTo(20f, 8f)
                lineTo(12f, 13f)
                lineTo(4f, 8f)
                verticalLineTo(6f)
                lineTo(12f, 11f)
                lineTo(20f, 6f)
                verticalLineTo(8f)
                close()
            }
        }.build()
        return _customEmail!!
    }
private var _customEmail: ImageVector? = null 