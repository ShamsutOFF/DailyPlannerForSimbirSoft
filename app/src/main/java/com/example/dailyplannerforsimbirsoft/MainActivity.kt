package com.example.dailyplannerforsimbirsoft

import android.app.DatePickerDialog
import android.graphics.drawable.shapes.Shape
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyplannerforsimbirsoft.ui.theme.DailyPlannerForSimbirSoftTheme
import com.example.dailyplannerforsimbirsoft.ui.theme.Shapes
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyPlannerForSimbirSoftTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val scope = rememberCoroutineScope()
                    val scaffoldState = rememberScaffoldState()
                    val fabShape = RoundedCornerShape(50)
                    Scaffold(
                        scaffoldState = scaffoldState,
                        drawerContent = {
//                            for (item in items) {
//                                Text(
//                                    text = item,
//                                    fontSize = TXT_SIZE,
//                                    modifier = Modifier.clickable {
//                                        selectedItem.value = item
//                                        scope.launch { scaffoldState.drawerState.close() }
//                                    })
//                            }
                        },
                        topBar = {
//                            TopAppBar() {
//                                IconButton(onClick = {
//                                    scope.launch { scaffoldState.drawerState.open() }
//                                }) {
//                                    Icon(Icons.Filled.Menu, "Меню")
//                                }
//                                Text(text = selectedItem.value, fontSize = TXT_SIZE)
//                                Spacer(modifier = Modifier.weight(1f, true))
//                                IconButton(onClick = { /*TODO*/ }) {
//                                    Icon(
//                                        imageVector = Icons.Filled.Info,
//                                        contentDescription = "Инормация о приложении"
//                                    )
//                                }
//                                IconButton(onClick = { /*TODO*/ }) {
//                                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Поиск")
//                                }
//                            }
                        },
                        bottomBar = {
                            BottomAppBar(cutoutShape = fabShape) {
//                                IconButton(onClick = { }) {
//                                    Icon(
//                                        Icons.Filled.Menu,
//                                        contentDescription = "Меню"
//                                    )
//                                }
//                                Spacer(Modifier.weight(1f, true))
//                                DateFAB()
//                                IconButton(onClick = { }) {
//                                    Icon(
//                                        Icons.Filled.Search,
//                                        contentDescription = "Поиск"
//                                    )
//                                }
                            }
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                shape = fabShape,
                                content = {
                                    Icon(Icons.Filled.Add, contentDescription = "Добавить")
                                },
                                onClick = {
                                    //TODO
                                }
                            )
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true,
                        content = {
                            Column(modifier = Modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                DateFAB()
                                DisplayNotes {
                                    Toast.makeText(baseContext, it.name, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    )
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

                val myFormat = "dd.MMMyyyy"
                val sdf = SimpleDateFormat(myFormat)
                pickedDate.value = sdf.format(cal.time)
            },
            year2.value, month2.value, day2.value
        )

        TextButton(onClick = { dpd.show() }) {
            Row(
                modifier = Modifier
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = "Выбрать дату"
                )
                Text(pickedDate.value, fontSize = 20.sp)
            }
        }
//        ExtendedFloatingActionButton(
//            icon = {
//                Icon(
//                    Icons.Filled.DateRange,
//                    contentDescription = "Выбрать дату"
//                )
//            },
//            text = { Text(pickedDate.value) },
//            onClick = {
//                dpd.show()
//            })
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