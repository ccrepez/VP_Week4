package com.christa.vp_week4.soal1

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.christa.vp_week4.R

private val Bg = Color(0xFFF4F1EE)
private val Orange = Color(0xFFFF5A36)
private val Dark = Color(0xFF1B1B2F)
private val Grey = Color(0xFF8A8A8A)

data class Car(
    val id: Int,
    val name: String,
    val transmission: String,
    val year: Int,
    val mileage: String,
    val fuel: String,
    val location: String,
    val price: String,
    val description: String,
    @DrawableRes val image: Int,
    val isFavorite: Boolean = false
)

private fun fuelColors(fuel: String): Pair<Color, Color> = when (fuel) {
    "PHEV" -> Color(0xFFE9E3FF) to Color(0xFF6A4FD8)
    "HYBRID" -> Color(0xFFDDF3EC) to Color(0xFF2E8B6E)
    "EV" -> Color(0xFFDDEBFF) to Color(0xFF2F6FD8)
    "DIESEL" -> Color(0xFFECECEC) to Color(0xFF555555)
    else -> Color(0xFFFFF1D9) to Color(0xFFE8932B)
}

private val carList = listOf(
    Car(id = 1, name = "Tiggo 9 CSH", transmission = "Auto", year = 2024, mileage = "5,000 km",
        fuel = "PHEV", location = "Surabaya", price = "Rp 599.000.000",
        description = "Chery Tiggo 9 CSH adalah SUV plug-in hybrid 7 penumpang dengan kabin mewah dan jarak tempuh listrik yang panjang.",
        image = R.drawable.tiggo, isFavorite = true),
    Car(id = 2, name = "Honda CRV", transmission = "Auto", year = 2013, mileage = "15,200 km",
        fuel = "PETROL", location = "Malang", price = "Rp 185.000.000",
        description = "Honda CR-V generasi keempat dengan mesin 2.4L i-VTEC yang tangguh, kabin lega, dan perawatan mudah.",
        image = R.drawable.crv),
    Car(id = 3, name = "Toyota Yaris Cross", transmission = "Auto", year = 2024, mileage = "30,500 km",
        fuel = "HYBRID", location = "Jakarta", price = "Rp 435.000.000",
        description = "Toyota Yaris Cross Hybrid memadukan efisiensi bahan bakar dengan desain crossover yang kompak.",
        image = R.drawable.yariscross, isFavorite = true),
    Car(id = 4, name = "Daihatsu Xenia", transmission = "Manual", year = 2022, mileage = "28,000 km",
        fuel = "PETROL", location = "Sidoarjo", price = "Rp 210.000.000",
        description = "Daihatsu Xenia berbasis platform DNGA, irit, nyaman untuk 7 penumpang, dan biaya perawatan rendah.",
        image = R.drawable.xenia),
    Car(id = 5, name = "Toyota Yaris", transmission = "Auto", year = 2021, mileage = "52,400 km",
        fuel = "PETROL", location = "Semarang", price = "Rp 245.000.000",
        description = "The Toyota Yaris G AT is a sporty compact hatchback that punches above its weight class. Equipped with a 1.5L dual VVT-i engine, 7 airbags, pre-collision system, and a floating head unit.",
        image = R.drawable.yaris, isFavorite = true),
    Car(id = 6, name = "Honda HR-V", transmission = "Auto", year = 2023, mileage = "12,800 km",
        fuel = "PETROL", location = "Gresik", price = "Rp 375.000.000",
        description = "Honda HR-V dengan desain coupe-SUV yang modern, kabin fleksibel, dan fitur Honda Sensing.",
        image = R.drawable.hrv),
    Car(id = 7, name = "Daihatsu Ayla", transmission = "Manual", year = 2020, mileage = "41,000 km",
        fuel = "PETROL", location = "Bandung", price = "Rp 115.000.000",
        description = "City car mungil yang sangat irit, mudah diparkir, dan cocok untuk mobil pertama.",
        image = R.drawable.ayla),
    Car(id = 8, name = "Jaecoo J5 EV", transmission = "Auto", year = 2025, mileage = "3,200 km",
        fuel = "EV", location = "Jakarta", price = "Rp 279.000.000",
        description = "SUV listrik kompak dengan desain tangguh, layar besar, dan biaya operasional sangat rendah.",
        image = R.drawable.j5, isFavorite = true),
    Car(id = 9, name = "MG4 EV", transmission = "Auto", year = 2024, mileage = "9,800 km",
        fuel = "EV", location = "Tangerang", price = "Rp 395.000.000",
        description = "Hatchback listrik penggerak roda belakang dengan handling lincah dan pengisian cepat.",
        image = R.drawable.mg4),
    Car(id = 10, name = "Toyota Innova Reborn", transmission = "Auto", year = 2019, mileage = "67,000 km",
        fuel = "DIESEL", location = "Surabaya", price = "Rp 330.000.000",
        description = "MPV diesel legendaris yang bandel, bertenaga, dan nyaman untuk perjalanan jauh sekeluarga.",
        image = R.drawable.reborn),
)

@Composable
fun CarApp() {
    var cars by remember { mutableStateOf(carList) }
    var selectedId by remember { mutableStateOf<Int?>(null) }

    val toggleFav: (Int) -> Unit = { id ->
        cars = cars.map { if (it.id == id) it.copy(isFavorite = !it.isFavorite) else it }
    }

    val selected = cars.firstOrNull { it.id == selectedId }
    if (selected == null) {
        CarListScreen(
            cars = cars,
            onCarClick = { selectedId = it.id },
            onFavClick = { toggleFav(it.id) }
        )
    } else {
        BackHandler { selectedId = null }
        CarDetailScreen(
            car = selected,
            onBack = { selectedId = null },
            onFavClick = { toggleFav(selected.id) }
        )
    }
}

@Composable
fun CarListScreen(cars: List<Car>, onCarClick: (Car) -> Unit, onFavClick: (Car) -> Unit) {
    val savedCount = cars.count { it.isFavorite }

    Column(
        Modifier
            .fillMaxSize()
            .background(Bg)
            .statusBarsPadding()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(Modifier.weight(1f)) {
                Box(
                    Modifier
                        .width(32.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Orange)
                )
                Spacer(Modifier.height(8.dp))
                Text("On Sale", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, color = Dark)
                Text("Find your perfect ride", fontSize = 13.sp, color = Grey)
            }
            Surface(
                shape = RoundedCornerShape(50),
                color = Color(0xFFFFF0EC),
                border = BorderStroke(1.dp, Orange.copy(alpha = 0.4f))
            ) {
                Row(
                    Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Favorite, null, tint = Orange, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("$savedCount saved", color = Orange, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
        HorizontalDivider(color = Color(0xFFE3DFDB))

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.navigationBarsPadding()
        ) {
            items(cars, key = { it.id }) { car ->
                CarCard(car = car, onClick = { onCarClick(car) }, onFavClick = { onFavClick(car) })
            }
        }
    }
}

@Composable
fun CarCard(car: Car, onClick: () -> Unit, onFavClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(width = 110.dp, height = 85.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(car.image),
                    contentDescription = car.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                FuelBadge(car.fuel, Modifier.align(Alignment.BottomStart).padding(6.dp))
            }
            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        car.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Dark,
                        modifier = Modifier.weight(1f)
                    )
                    FavoriteIcon(car.isFavorite, onFavClick)
                }
                Surface(shape = RoundedCornerShape(6.dp), color = Color(0xFFF1EFEC)) {
                    Text(
                        car.transmission, fontSize = 10.sp, color = Grey,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(5.dp).clip(CircleShape).background(Orange))
                    Spacer(Modifier.width(4.dp))
                    Text("${car.year}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Dark)
                    Spacer(Modifier.weight(1f))
                    Text(car.mileage, fontSize = 11.sp, color = Grey)
                }
            }
        }
    }
}

@Composable
private fun FuelBadge(fuel: String, modifier: Modifier = Modifier) {
    val (bg, fg) = fuelColors(fuel)
    Surface(shape = RoundedCornerShape(6.dp), color = bg, modifier = modifier) {
        Text(
            fuel, color = fg, fontSize = 9.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
        )
    }
}

@Composable
private fun FavoriteIcon(isFavorite: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(28.dp)) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) Orange else Color(0xFFBDBDBD),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun CarDetailScreen(car: Car, onBack: () -> Unit, onFavClick: () -> Unit) {
    Box(Modifier.fillMaxSize().background(Bg)) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box(Modifier.fillMaxWidth().height(300.dp)) {
                Image(
                    painter = painterResource(car.image),
                    contentDescription = car.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(12.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Dark)
                }
                FuelBadge(car.fuel, Modifier.align(Alignment.BottomStart).padding(start = 12.dp, bottom = 34.dp))
            }

            Column(
                Modifier
                    .offset(y = (-24).dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Bg)
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(car.name, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Dark, modifier = Modifier.weight(1f))
                    FavoriteIcon(car.isFavorite, onFavClick)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(5.dp).clip(CircleShape).background(Orange))
                    Spacer(Modifier.width(6.dp))
                    Text(car.location, fontSize = 12.sp, color = Grey)
                }
                Spacer(Modifier.height(14.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFFFF0EC),
                    border = BorderStroke(1.dp, Orange.copy(alpha = 0.4f))
                ) {
                    Text(
                        car.price, color = Orange, fontWeight = FontWeight.Bold, fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }

                SectionLabel("SPECIFICATIONS")
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SpecCard("YEAR", "${car.year}", Modifier.weight(1f))
                    SpecCard("MILEAGE", car.mileage, Modifier.weight(1f))
                }
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SpecCard("TRANSMISSION", car.transmission, Modifier.weight(1f))
                    SpecCard("FUEL TYPE", car.fuel, Modifier.weight(1f))
                }

                SectionLabel("ABOUT THIS CAR")
                Text(car.description, fontSize = 13.sp, color = Grey, lineHeight = 20.sp)
                Spacer(Modifier.height(90.dp))
            }
        }

        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Bg)
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedIconButton(
                onClick = onFavClick,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Orange.copy(alpha = 0.5f)),
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    if (car.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    "Favorite", tint = Orange
                )
            }
            Spacer(Modifier.width(12.dp))
            Box(
                Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.horizontalGradient(listOf(Orange, Color(0xFFFF7A45))))
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Text("Contact Seller", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Spacer(Modifier.height(22.dp))
    Text(text, fontSize = 10.sp, letterSpacing = 1.sp, color = Color(0xFFAAAAAA), fontWeight = FontWeight.SemiBold)
    Spacer(Modifier.height(10.dp))
}

@Composable
private fun SpecCard(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFECE8E4))
    ) {
        Column(Modifier.padding(14.dp)) {
            Text(label, fontSize = 9.sp, letterSpacing = 1.sp, color = Color(0xFFAAAAAA))
            Spacer(Modifier.height(6.dp))
            Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Dark)
        }
    }
}