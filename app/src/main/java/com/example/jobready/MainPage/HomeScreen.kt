package com.example.jobready.MainPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.text.style.TextOverflow
import com.example.jobready.ui.theme.JobReadyTheme

@Composable
private fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color(0xFFF5F7FA),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        singleLine = true,
                        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

// New: simple data model and composables for the projects grid
private data class Project(
    val date: String,
    val title: String,
    val category: String,
    val progress: Float // 0f..1f
)

@Composable
private fun ProjectCard(project: Project, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        color = Color(0xFFF3F6FB)
    ) {
        Column(modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()) {
            Text(
                text = project.date,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = project.category,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Progress",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = { project.progress },
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = Color(0xFFE9EEF8)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${(project.progress * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProjectsGrid(projects: List<Project>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = {
            items(projects) { project ->
                ProjectCard(project = project, modifier = Modifier.padding(4.dp))
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val openDrawer = remember { mutableStateOf(false) }
    val searchQuery = remember { mutableStateOf("") }

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
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                // Header: Greeting and Search
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 16.dp)
                ) {
                    Text(
                        text = "Hi Jenifer!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Good Morning",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Use the stable custom SearchBar instead of Material3 TextField
                    SearchBar(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Projects section placed under the search bar
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ongoing Projects",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "view all",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    // sample data
                    val projects = listOf(
                        Project("May 30, 2022", "Mobile App", "E - Commerce", 0.5f),
                        Project("May 30, 2022", "Dashboard", "Hotel", 0.3f),
                        Project("May 30, 2022", "Banner", "Marketing", 0.4f),
                        Project("May 30, 2022", "UI/UX", "Task Manager", 0.6f)
                    )

                    ProjectsGrid(projects = projects)
                }
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
