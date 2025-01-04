@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package kotlinmultiplatform.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.InternalResourceApi

private object CommonMainFont0 {
  public val NotoSansSC_Bold: FontResource by 
      lazy { init_NotoSansSC_Bold() }

  public val NotoSansSC_Regular: FontResource by 
      lazy { init_NotoSansSC_Regular() }
}

@InternalResourceApi
internal fun _collectCommonMainFont0Resources(map: MutableMap<String, FontResource>) {
  map.put("NotoSansSC_Bold", CommonMainFont0.NotoSansSC_Bold)
  map.put("NotoSansSC_Regular", CommonMainFont0.NotoSansSC_Regular)
}

internal val Res.font.NotoSansSC_Bold: FontResource
  get() = CommonMainFont0.NotoSansSC_Bold

private fun init_NotoSansSC_Bold(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:NotoSansSC_Bold",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/kotlinmultiplatform.composeapp.generated.resources/font/NotoSansSC-Bold.ttf", -1, -1),
    )
)

internal val Res.font.NotoSansSC_Regular: FontResource
  get() = CommonMainFont0.NotoSansSC_Regular

private fun init_NotoSansSC_Regular(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:NotoSansSC_Regular",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/kotlinmultiplatform.composeapp.generated.resources/font/NotoSansSC-Regular.ttf", -1, -1),
    )
)
