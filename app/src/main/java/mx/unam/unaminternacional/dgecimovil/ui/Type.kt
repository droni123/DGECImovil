package mx.unam.unaminternacional.dgecimovil.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import mx.unam.unaminternacional.dgecimovil.R

private val appDegeciFontFamily = FontFamily(
    fonts = listOf(
        //100
        Font( //200
            resId = R.font.light2,
            weight = FontWeight.Light,
            style = FontStyle.Normal
        ),
        Font( //300
            resId = R.font.light,
            weight = FontWeight.Light,
            style = FontStyle.Normal
        ),
        Font( //400
            resId = R.font.regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font( //500
            resId = R.font.medium,
            weight = FontWeight.Medium,
            style = FontStyle.Normal
        ),
        //600
        Font( //700
            resId = R.font.bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        Font( //800
            resId = R.font.bold2,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Normal
        ),
        Font( //900
            resId = R.font.bold3,
            weight = FontWeight.Black,
            style = FontStyle.Normal
        ),
        //i100
        Font( //i200
            resId = R.font.italight2,
            weight = FontWeight.Light,
            style = FontStyle.Italic
        ),
        Font( //i300
            resId = R.font.italight,
            weight = FontWeight.Light,
            style = FontStyle.Italic
        ),
        Font( //i400
            resId = R.font.itaregular,
            weight = FontWeight.Normal,
            style = FontStyle.Italic
        ),
        Font( //i500
            resId = R.font.itamedium,
            weight = FontWeight.Medium,
            style = FontStyle.Italic
        ),
        //i600
        Font( //i700
            resId = R.font.itabold,
            weight = FontWeight.Bold,
            style = FontStyle.Italic
        ),
        Font( //i800
            resId = R.font.itabold2,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Italic
        ),
        Font( //i900
            resId = R.font.itabold3,
            weight = FontWeight.Black,
            style = FontStyle.Italic
        ),
    )
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    //////////
    headlineLarge = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    //////////
    titleLarge = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    /////////////////////
    labelLarge = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    ///////////////
    bodyLarge = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = appDegeciFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    )
)