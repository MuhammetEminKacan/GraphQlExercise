package com.mek.graphqlexercise.presentation.launch_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mek.graphqlexercise.domain.model.LaunchDetail
import com.mek.graphqlexercise.presentation.launch_list.formatDate

@Composable
fun LaunchDetailScreen(
    launchId: String,
    viewModel: LaunchDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(LaunchDetailIntent.LoadLaunchDetail(launchId))
    }

    when {
        state.isLoading -> CircularProgressIndicator()
        state.error != null -> Text(text = state.error!!)
        state.launch != null -> LaunchDetailContent(state.launch!!)
    }
}

@Composable
fun LaunchDetailContent(launch: LaunchDetail) {

    Column(modifier = Modifier.padding(16.dp)) {

        Text(text = launch.missionName, style = MaterialTheme.typography.titleLarge)
        Text(text = formatDate(launch.launchDate), style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = launch.details ?: "Detay yok", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Rocket: ${launch.rocketName} (${launch.rocketType})", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Flicker images
        if (launch.imageUrls.isNotEmpty()) {
            LazyRow {
                items(launch.imageUrls) { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = "Launch Image",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(end = 8.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        } else {
            Text(text = "Bu launch için fotoğraf yok", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Links
        launch.articleLink?.let { link ->
            Text(
                text = "Article",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    // link açma işlemi burada yapılabilir
                }
            )
        }

        launch.videoLink?.let { link ->
            Text(
                text = "Video",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    // link açma işlemi burada yapılabilir
                }
            )
        }
    }
}