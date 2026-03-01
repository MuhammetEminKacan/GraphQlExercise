package com.mek.graphqlexercise.presentation.company_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CompanyScreen(viewModel: CompanyViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.onIntent(CompanyIntent.LoadCompanyInfo)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error != null -> {
                Text(
                    text = state.error ?: "",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }

            state.company != null -> {

                val company = state.company!!

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {

                    // HEADER
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        Color(0xFF4A6CF7),
                                        Color(0xFF9C27B0)
                                    )
                                )
                            )
                            .padding(24.dp)
                    ) {
                        Column {
                            Text(
                                text = company.name,
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Şirket Bilgileri",
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // COMPANY INFO CARD
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {

                            InfoRow(Icons.Default.CorporateFare, "Şirket Adı", company.name)
                            InfoRow(Icons.Default.Person, "CEO", company.ceo)
                            InfoRow(Icons.Default.PersonPin, "Kurucu", company.founder)
                            InfoRow(Icons.Default.CalendarMonth, "Kuruluş Yılı", company.founded.toString())
                            InfoRow(Icons.Default.Face, "Çalışan Sayısı", company.employees.toString())
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // LINKS CARD
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {

                            Text(
                                text = "Sosyal Medya & Bağlantılar",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            company.website?.let {
                                LinkRow(
                                    title = "Website",
                                    url = it
                                ) {
                                    context.startActivity(
                                        Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                    )
                                }
                            }

                            company.twitter?.let {
                                LinkRow(
                                    title = "Twitter",
                                    url = it
                                ) {
                                    context.startActivity(
                                        Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                    )
                                }
                            }

                            company.flickr?.let {
                                LinkRow(
                                    title = "Flickr",
                                    url = it
                                ) {
                                    context.startActivity(
                                        Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                    )
                                }
                            }

                            company.elonTwitter?.let {
                                LinkRow(
                                    title = "Elon Twitter",
                                    url = it
                                ) {
                                    context.startActivity(
                                        Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun LinkRow(
    title: String,
    url: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )

        Icon(
            imageVector = Icons.Default.OpenInNew,
            contentDescription = null
        )
    }
}