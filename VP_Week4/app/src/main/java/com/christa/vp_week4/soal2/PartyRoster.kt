package com.christa.vp_week4.soal2

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.christa.vp_week4.R

private val Bg = Color(0xFF0E0E16)
private val CardBg = Color(0xFF181824)
private val Stroke = Color(0xFF2A2A3A)
private val Gold = Color(0xFFFFB300)
private val HpRed = Color(0xFFFF3B5C)
private val MpBlue = Color(0xFF2F8BFF)
private val TextGrey = Color(0xFF9A9AB0)

private val TemplarColor = Color(0xFFE8A317)
private val OracleColor = Color(0xFF2ECC71)
private val ShadowColor = Color(0xFFB84DFF)
private val MageColor = Color(0xFF22C3E6)
private val BerserkerColor = Color(0xFFFF4D6D)
private val RangerColor = Color(0xFFFF9F1C)

data class Hero(
    val id: Int,
    val name: String,
    val heroClass: String,
    val classColor: Color,
    val level: Int,
    val cp: Int,
    val hp: Int,
    val maxHp: Int,
    val mp: Int,
    val maxMp: Int,
    val atk: Int,
    val def: Int,
    val crit: Int,
    val spd: Int,
    val skillName: String,
    val skillDesc: String,
    @DrawableRes val image: Int,
    val inSquad: Boolean = false,
    val rarity: String = "MYTHIC"
)

private fun rarityColor(rarity: String): Color = when (rarity) {
    "MYTHIC" -> HpRed
    "LEGEND" -> Gold
    "EPIC" -> ShadowColor
    "RARE" -> MpBlue
    else -> TextGrey
}

private val heroes = listOf(
    Hero(
        id = 1, name = "Alistair", heroClass = "High Templar", classColor = TemplarColor,
        level = 50, cp = 5600, hp = 1650, maxHp = 1800, mp = 650, maxMp = 800,
        atk = 380, def = 540, crit = 22, spd = 105,
        skillName = "Wrath of the Heavens",
        skillDesc = "Calls down a celestial hammer, stunning all enemies for 3 seconds and taunting survivors.",
        image = R.drawable.paladin
    ),
    Hero(
        id = 2, name = "Theresa", heroClass = "Divine Oracle", classColor = OracleColor,
        level = 49, cp = 5480, hp = 1100, maxHp = 1200, mp = 2100, maxMp = 2400,
        atk = 240, def = 310, crit = 12, spd = 98,
        skillName = "Blessing of Dawn",
        skillDesc = "Restores 30% HP to all allies and cleanses every negative status effect.",
        image = R.drawable.cleric
    ),
    Hero(
        id = 3, name = "Vespera", heroClass = "Shadowblade", classColor = ShadowColor,
        level = 48, cp = 5350, hp = 890, maxHp = 950, mp = 410, maxMp = 600,
        atk = 520, def = 180, crit = 45, spd = 140,
        skillName = "Night Reaper",
        skillDesc = "Vanishes into the shadows and strikes the weakest enemy three times with guaranteed crits.",
        image = R.drawable.rogue
    ),
    Hero(
        id = 4, name = "Elaria", heroClass = "Archmage", classColor = MageColor,
        level = 47, cp = 5120, hp = 450, maxHp = 600, mp = 1850, maxMp = 2000,
        atk = 610, def = 150, crit = 18, spd = 110,
        skillName = "Meteor Storm",
        skillDesc = "Rains fiery meteors over the battlefield, dealing massive magic damage to all enemies.",
        image = R.drawable.archmage, inSquad = true
    ),
    Hero(
        id = 5, name = "Grimm", heroClass = "Berserker", classColor = BerserkerColor,
        level = 46, cp = 4980, hp = 1900, maxHp = 2100, mp = 120, maxMp = 400,
        atk = 580, def = 290, crit = 30, spd = 92,
        skillName = "Blood Frenzy",
        skillDesc = "Sacrifices 15% HP to double attack speed for 6 seconds.",
        image = R.drawable.berserker, inSquad = true
    ),
    Hero(
        id = 6, name = "Kael", heroClass = "Berserker", classColor = BerserkerColor,
        level = 45, cp = 4720, hp = 1300, maxHp = 1950, mp = 200, maxMp = 400,
        atk = 540, def = 260, crit = 28, spd = 96,
        skillName = "Rampage",
        skillDesc = "Spins with twin axes, hitting all nearby enemies and gaining lifesteal.",
        image = R.drawable.berserker, rarity = "LEGEND"
    ),
    Hero(
        id = 7, name = "Rowan", heroClass = "Ranger", classColor = RangerColor,
        level = 45, cp = 4650, hp = 1200, maxHp = 1400, mp = 500, maxMp = 700,
        atk = 470, def = 300, crit = 35, spd = 120,
        skillName = "Piercing Volley",
        skillDesc = "Fires a barrage of arrows that pierce through every enemy in a line.",
        image = R.drawable.paladin, inSquad = true, rarity = "LEGEND"
    ),
    Hero(
        id = 8, name = "Mirabel", heroClass = "Archmage", classColor = MageColor,
        level = 44, cp = 4600, hp = 520, maxHp = 700, mp = 900, maxMp = 1900,
        atk = 560, def = 140, crit = 16, spd = 104,
        skillName = "Frozen Prison",
        skillDesc = "Encases a target in ice, freezing it for 4 seconds and slowing nearby foes.",
        image = R.drawable.archmage, rarity = "LEGEND"
    ),
    Hero(
        id = 9, name = "Seraphine", heroClass = "Divine Oracle", classColor = OracleColor,
        level = 43, cp = 4480, hp = 800, maxHp = 1100, mp = 1600, maxMp = 2200,
        atk = 220, def = 280, crit = 10, spd = 95,
        skillName = "Sanctuary",
        skillDesc = "Creates a holy circle that heals allies inside it every second.",
        image = R.drawable.cleric, rarity = "EPIC"
    ),
    Hero(
        id = 10, name = "Nyx", heroClass = "Shadowblade", classColor = ShadowColor,
        level = 42, cp = 4350, hp = 700, maxHp = 900, mp = 300, maxMp = 550,
        atk = 490, def = 160, crit = 40, spd = 135,
        skillName = "Smoke Veil",
        skillDesc = "Throws a smoke bomb, making allies untargetable for 2 seconds.",
        image = R.drawable.rogue, rarity = "EPIC"
    ),
    Hero(
        id = 11, name = "Orin", heroClass = "Archmage", classColor = MageColor,
        level = 41, cp = 4210, hp = 300, maxHp = 650, mp = 1400, maxMp = 1800,
        atk = 530, def = 130, crit = 14, spd = 100,
        skillName = "Arcane Surge",
        skillDesc = "Overloads mana, amplifying the next three spells by 50%.",
        image = R.drawable.archmage, rarity = "EPIC"
    ),
    Hero(
        id = 12, name = "Lyra", heroClass = "Ranger", classColor = RangerColor,
        level = 40, cp = 4100, hp = 950, maxHp = 1200, mp = 350, maxMp = 600,
        atk = 450, def = 220, crit = 32, spd = 125,
        skillName = "Hawk Eye",
        skillDesc = "Marks a target, increasing all damage it receives by 25% for 5 seconds.",
        image = R.drawable.paladin, rarity = "EPIC"
    ),
    Hero(
        id = 13, name = "Kira", heroClass = "Shadowblade", classColor = ShadowColor,
        level = 39, cp = 3980, hp = 600, maxHp = 850, mp = 250, maxMp = 500,
        atk = 470, def = 150, crit = 38, spd = 130,
        skillName = "Venom Edge",
        skillDesc = "Coats her blades in poison, dealing damage over time for 6 seconds.",
        image = R.drawable.rogue, rarity = "RARE"
    ),
    Hero(
        id = 14, name = "Fenn", heroClass = "Ranger", classColor = RangerColor,
        level = 38, cp = 3850, hp = 880, maxHp = 1150, mp = 300, maxMp = 550,
        atk = 430, def = 210, crit = 30, spd = 118,
        skillName = "Trap Field",
        skillDesc = "Scatters hidden traps that root enemies who step on them.",
        image = R.drawable.paladin, rarity = "RARE"
    ),
)

private val classOrder = listOf(
    "Berserker", "Archmage", "High Templar", "Divine Oracle", "Shadowblade", "Ranger"
)

private val squadOrder = listOf(5, 4, 7)

@Composable
fun PartyRosterScreen() {
    var selectedClass by remember { mutableStateOf("All") }

    val sortedHeroes = heroes.sortedByDescending { it.cp }
    val shownHeroes = if (selectedClass == "All") {
        sortedHeroes
    } else {
        sortedHeroes.filter { it.heroClass == selectedClass }
    }

    val filterOptions = listOf("All" to heroes.size) +
            classOrder.map { cls -> cls to heroes.count { it.heroClass == cls } }

    val squad = squadOrder.mapNotNull { id -> heroes.find { it.id == id } }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + 16.dp,
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Header(heroes.size) }
        item { SquadCard(squad) }
        item { SearchBar() }
        item {
            FilterRow(
                options = filterOptions,
                selected = selectedClass,
                onSelect = { selectedClass = it }
            )
        }
        items(shownHeroes, key = { it.id }) { hero -> HeroCard(hero) }
    }
}

@Composable
private fun Header(total: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text("Party Roster", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            Text("Guild Vanguard • $total Heroes Available", color = TextGrey, fontSize = 12.sp)
        }
        Pill(text = "Expedition", icon = Icons.Filled.Star)
    }
}

@Composable
private fun Pill(text: String, icon: ImageVector? = null) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Gold.copy(alpha = 0.12f),
        border = BorderStroke(1.dp, Gold.copy(alpha = 0.6f))
    ) {
        Row(
            Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(icon, null, tint = Gold, modifier = Modifier.size(13.dp))
                Spacer(Modifier.width(4.dp))
            }
            Text(text, color = Gold, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun SquadCard(squad: List<Hero>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1828)),
        border = BorderStroke(1.dp, Color(0xFF4A3A3A))
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(7.dp).clip(CircleShape).background(Color(0xFF2ECC71)))
                Spacer(Modifier.width(6.dp))
                Text(
                    "EXPEDITION SQUAD (${squad.size}/4)",
                    color = Color(0xFFCFCFE0),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp,
                    modifier = Modifier.weight(1f)
                )
                Text("⚔ ${squad.sumOf { it.cp }} CP", color = Gold, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                squad.forEach { hero ->
                    Avatar(hero.image, hero.classColor, 44.dp)
                    Spacer(Modifier.width(8.dp))
                }
                repeat(4 - squad.size) {
                    Box(
                        Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, Stroke, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Add, null, tint = TextGrey)
                    }
                    Spacer(Modifier.width(8.dp))
                }
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Color(0xFF1A1300))
                ) {
                    Text("Deploy ⚡", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun Avatar(@DrawableRes image: Int, borderColor: Color, size: Dp) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF3EFE6))
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
    )
}

@Composable
private fun SearchBar() {
    var query by remember { mutableStateOf("") }
    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        placeholder = { Text("Search by hero name, class, or rarity...", fontSize = 13.sp) },
        leadingIcon = { Icon(Icons.Filled.Search, null) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = CardBg,
            unfocusedContainerColor = CardBg,
            focusedBorderColor = Gold,
            unfocusedBorderColor = Stroke,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedLeadingIconColor = TextGrey,
            unfocusedLeadingIconColor = TextGrey,
            focusedPlaceholderColor = TextGrey,
            unfocusedPlaceholderColor = TextGrey,
            cursorColor = Gold
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun FilterRow(
    options: List<Pair<String, Int>>,
    selected: String,
    onSelect: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(options) { (name, count) ->
                val isSelected = name == selected
                Surface(
                    onClick = { onSelect(name) },
                    shape = RoundedCornerShape(50),
                    color = if (isSelected) Gold.copy(alpha = 0.15f) else Color.Transparent,
                    border = BorderStroke(1.dp, if (isSelected) Gold else Stroke)
                ) {
                    Text(
                        "$name ($count)",
                        color = if (isSelected) Gold else Color(0xFFCFCFE0),
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                    )
                }
            }
        }
        Spacer(Modifier.width(8.dp))
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Gold.copy(alpha = 0.12f),
            border = BorderStroke(1.dp, Gold.copy(alpha = 0.6f))
        ) {
            Text(
                "Sort: Power",
                color = Gold,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
            )
        }
    }
}

@Composable
fun HeroCard(hero: Hero) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        border = BorderStroke(1.dp, if (hero.inSquad) Gold else Stroke),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Tag(hero.rarity, rarityColor(hero.rarity))
                Spacer(Modifier.width(6.dp))
                Tag(hero.heroClass, hero.classColor)
                Spacer(Modifier.weight(1f))
                Text("⚔ ${hero.cp} CP", color = Gold, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                if (hero.inSquad) {
                    Spacer(Modifier.width(6.dp))
                    Tag("IN SQUAD", Gold)
                }
            }
            Spacer(Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box {
                    Avatar(hero.image, hero.classColor, 70.dp)
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xCC111111),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(3.dp)
                    ) {
                        Text(
                            "Lvl ${hero.level}",
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                        )
                    }
                }
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            hero.name,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            tint = TextGrey
                        )
                    }
                    Spacer(Modifier.height(6.dp))
                    StatBar("HP", hero.hp, hero.maxHp, HpRed)
                    Spacer(Modifier.height(4.dp))
                    StatBar("MP", hero.mp, hero.maxMp, MpBlue)
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(Modifier.height(12.dp))
                    HorizontalDivider(color = Stroke)
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        MiniStat("ATK", "${hero.atk}", Color(0xFFFF5A5A), Modifier.weight(1f))
                        MiniStat("DEF", "${hero.def}", Color(0xFF2ECC71), Modifier.weight(1f))
                        MiniStat("CRIT", "${hero.crit}%", Color(0xFFFFA726), Modifier.weight(1f))
                        MiniStat("SPD", "${hero.spd}", Color(0xFF22C3E6), Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF1F1F2E),
                        border = BorderStroke(1.dp, Stroke),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Star, null, tint = Gold, modifier = Modifier.size(14.dp))
                                Spacer(Modifier.width(6.dp))
                                Text(hero.skillName, color = Gold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                            Spacer(Modifier.height(4.dp))
                            Text(hero.skillDesc, color = TextGrey, fontSize = 11.sp, lineHeight = 16.sp)
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        ActionButton("✚ Heal (+250 HP)", Color(0xFF2ECC71), Modifier.weight(1f))
                        ActionButton(
                            if (hero.inSquad) "Remove from Squad" else "⚔ Add to Squad",
                            Gold,
                            Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Tag(text: String, color: Color) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = color.copy(alpha = 0.15f),
        border = BorderStroke(1.dp, color.copy(alpha = 0.6f))
    ) {
        Text(
            text,
            color = color,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
        )
    }
}

@Composable
private fun StatBar(label: String, value: Int, max: Int, color: Color) {
    val fraction = (value.toFloat() / max).coerceIn(0f, 1f)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            label,
            color = TextGrey,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(24.dp)
        )
        Box(
            Modifier
                .weight(1f)
                .height(7.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF0B0B12))
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction)
                    .clip(RoundedCornerShape(50))
                    .background(color)
            )
        }
        Spacer(Modifier.width(8.dp))
        Text("$value / $max", color = TextGrey, fontSize = 10.sp, modifier = Modifier.width(72.dp))
    }
}

@Composable
private fun MiniStat(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFF1F1F2E),
        border = BorderStroke(1.dp, Stroke),
        modifier = modifier
    ) {
        Column(
            Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, color = color, fontSize = 9.sp, fontWeight = FontWeight.Bold)
            Text(value, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ActionButton(text: String, color: Color, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, color.copy(alpha = 0.7f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = color.copy(alpha = 0.12f),
            contentColor = color
        ),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 10.dp),
        modifier = modifier
    ) {
        Text(text, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, maxLines = 1)
    }
}