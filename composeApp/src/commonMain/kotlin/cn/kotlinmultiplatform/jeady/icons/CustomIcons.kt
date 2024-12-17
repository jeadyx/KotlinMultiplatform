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