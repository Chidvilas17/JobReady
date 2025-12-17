package com.example.jobready.MainPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jobready.ui.theme.JobReadyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val openDrawer = remember { mutableStateOf(false) }

    // When openDrawer becomes true, launch a coroutine in the composition to open the drawer
    LaunchedEffect(openDrawer.value) {
        if (openDrawer.value) {
            drawerState.open()
            openDrawer.value = false
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Simple static drawer content; add items as needed
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = true,
                    onClick = { /* TODO */ },
                    colors = NavigationDrawerItemDefaults.colors()
                )
                NavigationDrawerItem(
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { /* TODO */ },
                    colors = NavigationDrawerItemDefaults.colors()
                )
            }
        }
    ) {
        Scaffold(
            topBar = { TopNavBar(title = "JobReady", onMenuClick = { openDrawer.value = true }) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Welcome to JobReady")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JobReadyTheme {
        HomeScreen()
    }
}
