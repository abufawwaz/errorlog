package com.myfawwaz.android.jawa.widget.presentation.calendarlib

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kizitonwose.calendar.compose.CalendarLayoutInfo
import com.kizitonwose.calendar.compose.HeatMapCalendar
import com.kizitonwose.calendar.compose.heatmapcalendar.HeatMapCalendarState
import com.kizitonwose.calendar.compose.heatmapcalendar.HeatMapWeek
import com.kizitonwose.calendar.compose.heatmapcalendar.rememberHeatMapCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import com.myfawwaz.android.jawa.widget.presentation.calendarlib.Util.displayText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit

private enum class Level(val color: Color) {
    Zero(Color(0xFFEBEDF0)),
    One(Color(0xFF9BE9A8)),
    Two(Color(0xFF40C463)),
    Three(Color(0xFF30A14E)),
    Four(Color(0xFF216E3A)),
}

@RequiresApi(Build.VERSION_CODES.O)
private fun generateRandomData(startDate: LocalDate,
                               endDate: LocalDate): Map<LocalDate, Level> {
    val levels = Level.values()
    return (0..ChronoUnit.DAYS.between(startDate, endDate)).associateTo(hashMapOf())
    {
            count -> startDate.plusDays(count) to levels.random()
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExampleHeatMap(
   // mainNavController: NavHostController
) {
    var refreshKey by remember { mutableStateOf(1) }
    val endDate = remember { LocalDate.now() } //hari sekarang terakhir
    // GitHub only shows contributions for the past 12 months
    val startDate = remember { endDate.minusMonths(12) } //12bulan dari hari sekarang
    val data = remember { mutableStateOf<Map<LocalDate, Level>>(emptyMap()) }
    var selection by remember { mutableStateOf<Pair<LocalDate, Level>?>(null) }
    LaunchedEffect(startDate, endDate, refreshKey) {
        selection = null
        data.value = withContext(Dispatchers.IO) {
            generateRandomData(startDate, endDate)
        }
    }
    Column(
        modifier = Modifier // .fillMaxSize() full page
            .background(MaterialTheme.colors.background),
    ) {
        val state = rememberHeatMapCalendarState(
            startMonth = startDate.yearMonth,
            endMonth = endDate.yearMonth,
            firstVisibleMonth = endDate.yearMonth,
            firstDayOfWeek = firstDayOfWeekFromLocale(),
        )

        HeatMapCalendar(
            modifier = Modifier.padding(vertical = 10.dp),
            state = state,
            contentPadding = PaddingValues(end = 6.dp),
            dayContent = { day, week ->
                Day(
                    day = day,
                    startDate = startDate,
                    endDate = endDate,
                    week = week,
                    level = data.value[day.date] ?: Level.Zero,
                ) { clicked ->
                    selection = Pair(clicked, data.value[clicked] ?: Level.Zero)
                }
            },
            weekHeader = { WeekHeader(it) },
            monthHeader = { MonthHeader(it, endDate, state) },
        )
        //pengecekan
        val levels = Level.values()
        Text(text = ""
               // + ChronoUnit.DAYS.between(startDate, endDate)+"nnn" // menampilkan jumlah hari
             //   +levels.random()
           // + generateDataHeat(startDate, endDate)
              //  +startDate.plusDays(365)
            , fontSize = 10.sp)
       // SimpleTextField("" +generateRandomData(startDate, endDate))
            TextInfo(modifier = Modifier.fillMaxWidth(), selection = selection,)
/**/     CalendarInfo(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 44.dp),
        selection = selection,
    ){}

 /**/
/* // tombol refress
Box(
   // modifier = Modifweight(1f)
) {
    BottomContent(
        modifier = Modifier
            .fillMaxWidth()
          //  .align(Alignment.BottomCenter)
            .padding(20.dp)
           , selection = selection,
    ) { refreshKey += 1 }
}

 */
}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    selection: Pair<LocalDate, Level>? = null,
    refresh: (() -> Unit),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        if (selection != null) {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(text = "Clicked: ${formatter.format(selection.first)}")
                LevelBox("",color = selection.second.color)
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = refresh,
        ) {
            Text(
                text = "Generate random data",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }



    }
}
@Composable
fun SimpleTextField(ContentText:String) {
    var text by remember { mutableStateOf(TextFieldValue(ContentText)) }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        }
    )
}
@RequiresApi(Build.VERSION_CODES.O)
private val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun TextInfo(
    modifier: Modifier = Modifier,
    selection: Pair<LocalDate, Level>? = null,

    ) {
    Row(
        modifier = modifier,
        //horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(text = "    Less", fontSize = 10.sp)
        Level.values().forEach { level ->
            LevelBox("",level.color)
        }
        Text(text = "More", fontSize = 10.sp,)

        if (selection != null) {
        Text(text = "      Clicked: ${formatter.format(selection.first)}", fontSize = 10.sp,)
        LevelBox("", color = selection.second.color)
        }

}

}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CalendarInfo(
    modifier: Modifier = Modifier,
    selection: Pair<LocalDate, Level>? = null,
    refresh: (() -> Unit),
) {
Row(
modifier = modifier,
horizontalArrangement = Arrangement.End,
verticalAlignment = Alignment.Bottom,
) {


Text(text = "Less", fontSize = 10.sp)
Level.values().forEach { level ->
    LevelBox("",level.color)
}
Text(text = "More", fontSize = 10.sp)
}
}

private val daySize = 18.dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable //boxhari
private fun Day(
    day: CalendarDay,
    startDate: LocalDate,
    endDate: LocalDate,
    week: HeatMapWeek,
    level: Level,
    onClick: (LocalDate) -> Unit,
) {
// We only want to draw boxes on the days that are in the
// past 12 months. Since the calendar is month-based, we ignore
// the future dates in the current month and those in the start
// month that are older than 12 months from today.
// We draw a transparent box on the empty spaces in the first week
// so the items are laid out properly as the column is top to bottom.
val weekDates = week.days.map { it.date }
if (day.date in startDate..endDate) {
LevelBox("",level.color) { onClick(day.date) }
} else if (weekDates.contains(startDate)) {
LevelBox("",Color.Transparent)
}
}

@Composable  //mewarnai box
private fun LevelBox(TextBox: String,color: Color, onClick: (() -> Unit)? = null) {
Box(
modifier = Modifier
    .size(daySize) // Must set a size on the day.
    .padding(2.dp)
    .clip(RoundedCornerShape(2.dp))
    .background(color = color)
    .clickable(enabled = onClick != null) { onClick?.invoke() },
){
Text(text = TextBox)
}
}

@Composable
private fun WeekHeader(dayOfWeek: DayOfWeek) {
Box(
modifier = Modifier
    .height(daySize) // Must set a height on the day of week so it aligns with the day.
    .padding(horizontal = 4.dp),
) {
Text(
    text = dayOfWeek.displayText(),
    modifier = Modifier.align(Alignment.Center),
    fontSize = 10.sp,
)
}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MonthHeader(
calendarMonth: CalendarMonth,
endDate: LocalDate,
state: HeatMapCalendarState,
) {
val density = LocalDensity.current
val firstFullyVisibleMonth by remember {
// Find the first index with at most one box out of bounds.
derivedStateOf { getMonthWithYear(state.layoutInfo, daySize, density) }
}
if (calendarMonth.weekDays.first().first().date <= endDate) {
val month = calendarMonth.yearMonth
val title = if (month == firstFullyVisibleMonth) {
    month.displayText(short = true)
} else {
    month.month.displayText()
}
Box(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 1.dp, start = 2.dp),
) {
    Text(text = title, fontSize = 10.sp)
}
}
}

// Find the first index with at most one box out of bounds.
private fun getMonthWithYear(
layoutInfo: CalendarLayoutInfo,
daySize: Dp,
density: Density,
): YearMonth? {
val visibleItemsInfo = layoutInfo.visibleMonthsInfo
return when {
visibleItemsInfo.isEmpty() -> null
visibleItemsInfo.count() == 1 -> visibleItemsInfo.first().month.yearMonth
else -> {
    val firstItem = visibleItemsInfo.first()
    val daySizePx = with(density) { daySize.toPx() }
    if (
        firstItem.size < daySizePx * 3 || // Ensure the Month + Year text can fit.
        firstItem.offset < layoutInfo.viewportStartOffset && // Ensure the week row size - 1 is visible.
        (layoutInfo.viewportStartOffset - firstItem.offset > daySizePx)
    ) {
        visibleItemsInfo[1].month.yearMonth
    } else {
        firstItem.month.yearMonth
    }
}
}
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(heightDp = 600)
@Composable
private fun Example6Preview() {
// LevelBox(Color.Blue)
ExampleHeatMap()

}
fun latHashMap(args: Array<String>) {
    var hashmap = hashMapOf<Int,String>()
    hashmap.put(1, "2022-08-31=Three")
    hashmap.put(2, "2022-08-30=Zero")
    hashmap.put(3, "2022-08-28=Four")
    for (i in hashmap.keys) {
        println("Key = ${i}, value = ${hashmap.get(i)}")
    }
}
@RequiresApi(Build.VERSION_CODES.O)
private fun generateDataHeat(startDate: LocalDate,
                               endDate: LocalDate): Map<LocalDate, Level> {
    val levels = Level.values()
    val list1 = listOf("a", "abc", "abcd", "cde")
    val count1 = listOf(1, 2, 3, 4)
    val predicate: (String) -> Boolean = {it.length == 1}
    val predicate2: (Int) -> Boolean = {it == 1}
    var ahari:Long = 1
val warna = if (startDate.plusDays(ahari).toString() == "2023-05-26" ){}else{}
    val result = list1.count(predicate)
    print(result)

    //sampleStart
    data class Person(val firstName: String, val lastName: String)

    val scientists = listOf(Person("Grace", "Hopper"), Person("Jacob", "Bernoulli"), Person("Johann", "Bernoulli"))

    val byLastName = mutableMapOf<String, String>()
    println("byLastName.isEmpty() is ${byLastName.isEmpty()}") // true

    scientists.associateTo(byLastName) { it.lastName to it.firstName }

    println("byLastName.isNotEmpty() is ${byLastName.isNotEmpty()}") // true
// Jacob Bernoulli does not occur in the map because only the last pair with the same key gets added
    println(byLastName) // {Hopper=Grace, Bernoulli=Johann}
//sampleEnd

    return (0..ChronoUnit.DAYS.between(startDate, endDate)).associateTo(hashMapOf())
    {
        count -> startDate.plusDays(count)  to levels.random()
    }




}


val DataTGl =
    "{2022-08-31=Three, 2022-08-30=Zero, 2022-08-29=Zero, 2022-08-28=Four, 2022-08-27=Zero, 2022-08-26=Zero, 2022-08-25=Zero, 2022-08-24=Three, 2022-08-23=Four, 2022-08-22=Two, 2022-08-21=Zero, 2022-08-20=Zero, 2022-08-19=Four, 2022-08-18=One, 2022-08-17=One, 2022-08-16=Zero, 2022-08-15=Four, 2022-08-14=Two, 2022-08-13=Four, 2022-08-12=Four, 2022-08-11=Three, 2022-08-10=One, 2022-08-09=Zero, 2022-08-08=One, 2022-08-07=Zero, 2022-08-06=Zero, 2022-08-05=One, 2022-08-04=Three, 2022-08-03=Two, 2022-08-02=Two, 2022-08-01=Four, 2023-01-31=Three, 2022-09-30=Zero, 2023-01-30=Three, 2022-09-29=Four, 2023-01-29=Zero, 2022-09-28=Two, 2023-01-28=One, 2022-09-27=One, 2023-01-27=Four, 2022-09-26=Four, 2023-01-26=Zero, 2022-09-25=Two, 2023-01-25=One, 2022-09-24=Two, 2023-01-24=One, 2022-09-23=One, 2023-01-23=Zero, 2022-09-22=Four, 2023-01-22=Four, 2022-09-21=Three, 2023-01-21=Three, 2022-09-20=Two, 2023-01-20=One, 2022-09-19=Three, 2023-01-19=Two, 2022-09-18=One, 2023-01-18=One, 2022-09-17=Zero, 2023-01-17=Four, 2022-09-16=One, 2023-01-16=Three, 2022-09-15=Three, 2023-01-15=Four, 2022-09-14=Two, 2023-01-14=One, 2022-09-13=Three, 2023-01-13=Four, 2022-09-12=One, 2023-01-12=Three, 2022-09-11=Four, 2023-01-11=Zero, 2022-09-10=One, 2023-01-10=One, 2022-09-09=One, 2023-01-09=Zero, 2022-09-08=Zero, 2023-01-08=One, 2022-09-07=One, 2023-01-07=Two, 2022-09-06=Four, 2023-01-06=One, 2022-09-05=Zero, 2023-01-05=Three, 2022-09-04=Four, 2023-01-04=Zero, 2022-09-03=Four, 2023-01-03=Three, 2022-09-02=Four, 2023-01-02=Zero, 2022-09-01=Zero, 2023-01-01=Zero, 2022-10-31=Zero, 2022-10-30=Three, 2022-10-29=Zero, 2022-10-28=One, 2023-02-28=Four, 2022-10-27=Zero, 2023-02-27=Four, 2022-10-26=Three, 2023-02-26=One, 2022-10-25=Four, 2023-02-25=Three, 2022-10-24=Four, 2023-02-24=Zero, 2022-10-23=One, 2023-02-23=Three, 2022-10-22=Four, 2023-02-22=Two, 2022-10-21=Three, 2023-02-21=Two, 2022-10-20=One, 2023-02-20=Zero, 2022-10-19=Zero, 2023-02-19=Zero, 2022-10-18=Four, 2023-02-18=Three, 2022-10-17=Two, 2023-02-17=One, 2022-10-16=Four, 2023-02-16=Zero, 2022-10-15=One, 2023-02-15=Two, 2022-10-14=One, 2023-02-14=Two, 2022-10-13=Zero, 2023-02-13=Zero, 2022-10-12=Zero, 2023-02-12=Three, 2022-10-11=Two, 2023-02-11=One, 2022-10-10=Three, 2023-02-10=Three, 2022-10-09=Three, 2023-02-09=Two, 2022-10-08=One, 2023-02-08=Two, 2022-10-07=Four, 2023-02-07=Three, 2022-10-06=One, 2023-02-06=Zero, 2022-10-05=Two, 2023-02-05=Four, 2022-10-04=Three, 2023-02-04=One, 2022-10-03=Two, 2023-02-03=Two, 2022-10-02=Three, 2023-02-02=One, 2022-10-01=Two, 2023-02-01=Two, 2023-03-31=Four, 2022-11-30=One, 2023-03-30=Two, 2022-11-29=One, 2023-03-29=Three, 2022-11-28=Four, 2023-03-28=Zero, 2022-11-27=Two, 2023-03-27=Two, 2022-11-26=Two, 2023-03-26=Three, 2022-11-25=Three, 2023-03-25=Three, 2022-11-24=One, 2023-03-24=One, 2022-11-23=Two, 2023-03-23=Three, 2022-11-22=One, 2023-03-22=Four, 2022-11-21=Two, 2023-03-21=Two, 2022-11-20=Zero, 2023-03-20=Three, 2022-11-19=Three, 2023-03-19=Zero, 2022-11-18=Three, 2023-03-18=Four, 2022-11-17=One, 2023-03-17=Two, 2022-11-16=Three, 2023-03-16=One, 2022-11-15=Zero, 2023-03-15=One, 2022-11-14=Three, 2023-03-14=Two, 2022-11-13=Two, 2023-03-13=Two, 2022-11-12=Two, 2023-03-12=Zero, 2022-11-11=Zero, 2023-03-11=Two, 2022-11-10=Three, 2023-03-10=Zero, 2022-11-09=Two, 2023-03-09=Three, 2022-11-08=Zero, 2023-03-08=Three, 2022-11-07=One, 2023-03-07=Two, 2022-11-06=Two, 2023-03-06=Three, 2022-11-05=Zero, 2023-03-05=Zero, 2022-11-04=One, 2023-03-04=Two, 2022-11-03=Two, 2023-03-03=Three, 2022-11-02=Zero, 2023-03-02=Three, 2022-11-01=Two, 2023-03-01=Two, 2022-12-31=Two, 2022-12-30=Four, 2023-04-30=Zero, 2022-12-29=Three, 2023-04-29=Zero, 2022-12-28=Zero, 2023-04-28=One, 2022-12-27=Four, 2023-04-27=Two, 2022-12-26=One, 2023-04-26=One, 2022-12-25=Zero, 2023-04-25=Zero, 2022-12-24=Two, 2023-04-24=Two, 2022-12-23=One, 2023-04-23=Two, 2022-12-22=Three, 2023-04-22=Zero, 2022-12-21=Four, 2023-04-21=One, 2022-12-20=Two, 2023-04-20=One, 2022-12-19=One, 2023-04-19=One, 2022-12-18=Three, 2023-04-18=Four, 2022-12-17=Three, 2023-04-17=Two, 2022-12-16=Zero, 2023-04-16=One, 2022-12-15=Zero, 2023-04-15=Zero, 2022-12-14=Four, 2023-04-14=Four, 2022-12-13=Four, 2023-04-13=Zero, 2022-12-12=One, 2023-04-12=Zero, 2022-12-11=Zero, 2023-04-11=Zero, 2022-12-10=Three, 2023-04-10=Three, 2022-12-09=Two, 2023-04-09=Four, 2022-12-08=Zero, 2023-04-08=Four, 2022-12-07=Two, 2023-04-07=Two, 2022-12-06=Four, 2023-04-06=Three, 2022-12-05=One, 2023-04-05=Three, 2022-12-04=Two, 2023-04-04=Two, 2022-12-03=Four, 2023-04-03=Four, 2022-12-02=One, 2023-04-02=Three, 2022-12-01=One, 2023-04-01=Two, 2022-05-31=One, 2022-05-30=Four, 2022-05-29=One, 2022-05-28=Zero, 2022-05-27=Three, 2022-05-26=Three, 2023-05-26=Four, 2023-05-25=Two, 2023-05-24=Three, 2023-05-23=Zero, 2023-05-22=One, 2023-05-21=Three, 2023-05-20=Four, 2023-05-19=One, 2023-05-18=Zero, 2023-05-17=One, 2023-05-16=One, 2023-05-15=Three, 2023-05-14=One, 2023-05-13=Two, 2023-05-12=Three, 2023-05-11=Four, 2023-05-10=One, 2023-05-09=One, 2023-05-08=One, 2023-05-07=One, 2023-05-06=Four, 2023-05-05=One, 2023-05-04=Four, 2023-05-03=One, 2023-05-02=Three, 2023-05-01=Two, 2022-06-30=Zero, 2022-06-29=Zero, 2022-06-28=Zero, 2022-06-27=Four, 2022-06-26=Four, 2022-06-25=Four, 2022-06-24=Zero, 2022-06-23=Four, 2022-06-22=Two, 2022-06-21=Zero, 2022-06-20=Zero, 2022-06-19=Four, 2022-06-18=Two, 2022-06-17=Three, 2022-06-16=One, 2022-06-15=Zero, 2022-06-14=Four, 2022-06-13=One, 2022-06-12=Four, 2022-06-11=Zero, 2022-06-10=Three, 2022-06-09=Four, 2022-06-08=Zero, 2022-06-07=Two, 2022-06-06=Two, 2022-06-05=One, 2022-06-04=One, 2022-06-03=Zero, 2022-06-02=One, 2022-06-01=Zero, 2022-07-31=Two, 2022-07-30=Four, 2022-07-29=One, 2022-07-28=One, 2022-07-27=One, 2022-07-26=Zero, 2022-07-25=Zero, 2022-07-24=Two, 2022-07-23=Zero, 2022-07-22=Two, 2022-07-21=One, 2022-07-20=Zero, 2022-07-19=One, 2022-07-18=One, 2022-07-17=Four, 2022-07-16=Four, 2022-07-15=One, 2022-07-14=Zero, 2022-07-13=Three, 2022-07-12=Two, 2022-07-11=One, 2022-07-10=Four, 2022-07-09=One, 2022-07-08=Four, 2022-07-07=Four, 2022-07-06=One, 2022-07-05=Four, 2022-07-04=Two, 2022-07-03=Two, 2022-07-02=Zero, 2022-07-01=Zero}"