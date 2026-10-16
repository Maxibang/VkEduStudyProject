package com.example.vkeducation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun AppDetailsScreen(
    mainApp: AppDetailsModel,
    recommendations: List<AppDetailsModel>,
    onAppClick: (AppDetailsModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(mainApp.title) {
        scrollState.scrollTo(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val context = androidx.compose.ui.platform.LocalContext.current
            val safeIconName = mainApp.iconName.lowercase()
            val iconResId = context.resources.getIdentifier(safeIconName, "drawable", context.packageName)

            if (iconResId != 0) {
                Image(
                    painter = androidx.compose.ui.res.painterResource(id = iconResId),
                    contentDescription = "Иконка приложения",
                    contentScale = ContentScale.Crop, // Картинка ровно заполнит квадрат
                    modifier = Modifier
                        .size(88.dp) // Размер иконки
                        .clip(RoundedCornerShape(18.dp)) // Скругление углов
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = mainApp.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = mainApp.developer, color = Color.Gray, fontSize = 14.sp)
                Text(text = "Рейтинг: ${mainApp.rating}", fontSize = 14.sp)
                Text(text = "Скачиваний: ${mainApp.downloads}", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Установить")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "О приложении", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = mainApp.description, fontSize = 14.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Скриншоты", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            // Задаем фиксированный шаг-отступ в 8.dp между скриншотами
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(mainApp.screenshots) { screenshotName ->
                val context = androidx.compose.ui.platform.LocalContext.current

                val safeScreenName = screenshotName.lowercase()
                    .replace(".jpeg", "")
                    .replace(".jpg", "")
                    .replace(".png", "")

                val screenId = context.resources.getIdentifier(safeScreenName, "drawable", context.packageName)

                if (screenId != 0) {
                    Image(
                        painter = androidx.compose.ui.res.painterResource(id = screenId),
                        contentDescription = "Скриншот приложения",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(250.dp) // Жесткая высота для всей ленты, как в Google Play
                            .clip(RoundedCornerShape(8.dp)) // Аккуратно скругляем углы самой картинки
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Вам может понравиться", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
            items(recommendations) { app ->
                Column(modifier = Modifier.width(100.dp).clickable { onAppClick(app) }) {
                    val context = androidx.compose.ui.platform.LocalContext.current

                    // Защищаем имя: в нижний регистр и отсекаем расширения
                    val safeRecIconName = app.iconName.lowercase()
                        .replace(".jpeg", "")
                        .replace(".jpg", "")
                        .replace(".png", "")

                    val recIconId = context.resources.getIdentifier(safeRecIconName, "drawable", context.packageName)

                    if (recIconId != 0) {
                        Image(
                            painter = androidx.compose.ui.res.painterResource(id = recIconId),
                            contentDescription = "Иконка рекомендации",
                            contentScale = ContentScale.Crop, // Картинка идеально заполнит квадрат
                            modifier = Modifier
                                .size(80.dp) // Фиксированный размер мини-иконки 80x80
                                .clip(RoundedCornerShape(12.dp)) // Скругляем углы
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(text = app.title, fontSize = 12.sp, fontWeight = FontWeight.Medium, maxLines = 2, lineHeight = 16.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = app.rating, fontSize = 11.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(6.dp))

                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0077FF),
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                        modifier = Modifier.fillMaxWidth().height(28.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(text = "Скачать", fontSize = 10.sp)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.navigationBarsPadding().height(48.dp))
    }
}
