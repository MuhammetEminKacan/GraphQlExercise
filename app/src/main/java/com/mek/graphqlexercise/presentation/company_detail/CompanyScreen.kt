package com.mek.graphqlexercise.presentation.company_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun CompanyScreen(viewModel: CompanyViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(CompanyIntent.LoadCompanyInfo)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> Text(text = state.error!!)
            state.company != null -> {
                val c = state.company!!
                Text("Company: ${c.name}")
                Text("CEO: ${c.ceo}")
                Text("Founder: ${c.founder}")
                Text("Employees: ${c.employees}")
                Text("Founded: ${c.founded}")

                c.website?.let { Text("Website: $it") }
                c.twitter?.let { Text("Twitter: $it") }
                c.flickr?.let { Text("Flickr: $it") }
                c.elonTwitter?.let { Text("Elon Twitter: $it") }
            }
        }
    }
}