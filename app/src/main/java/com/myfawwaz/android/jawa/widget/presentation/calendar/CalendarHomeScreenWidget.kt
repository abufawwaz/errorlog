package com.myfawwaz.android.jawa.widget.presentation.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.myfawwaz.android.jawa.widget.app.getString
import com.myfawwaz.android.jawa.widget.domain.model.CalendarEvent
import com.myfawwaz.android.jawa.widget.presentation.glance_widgets.AddEventAction
import com.myfawwaz.android.jawa.widget.presentation.glance_widgets.GoToSettingsAction
import com.myfawwaz.android.jawa.widget.presentation.glance_widgets.NavigateToCalendarAction
import com.myfawwaz.android.jawa.widget.presentation.glance_widgets.RefreshCalendarAction
import com.myfawwaz.app.mybrain.R

@Composable
fun CalendarHomeScreenWidget(
    events: Map<String, List<CalendarEvent>>,
    permissionGranted: Boolean
) {
    Box(
        modifier = GlanceModifier
            .fillMaxWidth()
            .background(ImageProvider(R.drawable.large_item_rounded_corner_shape))
            .cornerRadius(25.dp)
    ) {
        Column(
            modifier = GlanceModifier
                .padding(8.dp)
        ) {
            Row(
                GlanceModifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    getString(R.string.calendar),
                    style = TextStyle(
                        color = ColorProvider(Color.White),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = GlanceModifier
                        .padding(horizontal = 8.dp)
                        .clickable(onClick = actionRunCallback<NavigateToCalendarAction>())
                    ,
                )
                Row(
                    modifier = GlanceModifier
                        .clickable(onClick = actionRunCallback<NavigateToCalendarAction>())
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Image(
                        modifier = GlanceModifier
                            .size(22.dp)
                            .clickable(actionRunCallback<RefreshCalendarAction>()),
                        provider = ImageProvider(R.drawable.ic_refresh),
                        contentDescription = "refresh"
                    )
                    Spacer(GlanceModifier.width(12.dp))
                    Image(
                        modifier = GlanceModifier
                            .size(22.dp)
                            .clickable(actionRunCallback<AddEventAction>())
                        ,
                        provider = ImageProvider(R.drawable.ic_add),
                        contentDescription = "add event",
                    )
                }
            }
            Spacer(GlanceModifier.height(8.dp))
            if (permissionGranted) {
                LazyColumn(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(ImageProvider(R.drawable.large_inner_item_rounded_corner_shape))
                        .cornerRadius(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (events.isEmpty()) {
                        item {
                            Text(
                                text = getString(R.string.no_events),
                                modifier = GlanceModifier.padding(16.dp),
                                style = TextStyle(
                                    color = ColorProvider(Color.White),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 18.sp
                                )
                            )
                        }
                    } else {
                        item { Spacer(GlanceModifier.height(6.dp)) }
                        events.forEach { (day, events) ->
                            item {
                                Column(
                                    modifier = GlanceModifier
                                        .fillMaxWidth()
                                        .padding(start = 4.dp, end = 4.dp)
                                ) {
                                    Text(
                                        text = day.substring(0, day.indexOf(",")),
                                        style = TextStyle(
                                            color = ColorProvider(Color.White),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp
                                        ),
                                        modifier = GlanceModifier.padding(bottom = 3.dp)
                                    )
                                    events.forEach { event ->
                                        CalendarEventWidgetItem(event = event)
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = GlanceModifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = getString(R.string.no_read_calendar_permission_message),
                        modifier = GlanceModifier.padding(16.dp),
                        style = TextStyle(
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(GlanceModifier.height(4.dp))
                    Button(
                        text = getString(R.string.go_to_settings),
                        onClick = actionRunCallback<GoToSettingsAction>()
                    )
                    Spacer(GlanceModifier.height(4.dp))
                    Text(
                        text = getString(R.string.calendar_widget_refresh_message),
                        modifier = GlanceModifier.padding(12.dp),
                        style = TextStyle(
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}