package lat.pam.mycityapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RecommendationScreen(
    navController: NavController,
    category: String
) {
    val viewModel = RecommendationsViewModel()
    val recommendations = viewModel.recommendations.value[category] ?: emptyList()
    Column {
        Text(text = category, fontSize = 24.sp)
        LazyColumn {
            items(recommendations) { recommendation ->
                Text(text = recommendation.name, fontSize = 18.sp)
                Text(text = recommendation.description, fontSize = 14.sp)
            }
        }
    }
}
