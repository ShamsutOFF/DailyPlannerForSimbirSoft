package com.example.dailyplannerforsimbirsoft

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.dailyplannerforsimbirsoft.ui.theme.DailyPlannerForSimbirSoftTheme
import java.text.DateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyPlannerForSimbirSoftTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column() {
                        DateFAB()
                        DisplayNotes {
                            Toast.makeText(baseContext, it.name, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }

    @Composable
    private fun DateFAB() {
        val myDateFormat: DateFormat = java.text.SimpleDateFormat("dd.MMM.yyyy")
        val pickedDate =
            remember { mutableStateOf(myDateFormat.format(System.currentTimeMillis())) }
        var cal = Calendar.getInstance()
        val year2 = remember { mutableStateOf(cal.get(Calendar.YEAR)) }
        val month2 = remember {
            mutableStateOf(cal.get(Calendar.MONTH))
        }
        val day2 = remember {
            mutableStateOf(cal.get(Calendar.DAY_OF_MONTH))
        }
        val dpd = DatePickerDialog(
            this, { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MMM.yyyy"
                val sdf = SimpleDateFormat(myFormat)
                pickedDate.value = sdf.format(cal.time)
            },
            year2.value, month2.value, day2.value
        )
        ExtendedFloatingActionButton(
            icon = {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = "Выбрать дату"
                )
            },
            text = { Text(pickedDate.value) },
            onClick = {
                dpd.show()
            })
    }

    @Composable
    private fun DisplayNotes(selectedItem: (Note) -> Unit) {
        val notes = remember { NotesList.notes }
        LazyColumn(
            content = {
                items(items = notes, itemContent = {
                    NoteListItem(
                        note = it,
                        selectedItem = selectedItem
                    )
                })
            },
            contentPadding = PaddingValues(16.dp, 8.dp)
        )
    }
}