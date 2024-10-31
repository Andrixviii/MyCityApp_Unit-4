package lat.pam.mycityapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RecommendationsViewModel : ViewModel() {

    private val _recommendations = mutableStateOf(
        mapOf(
            "Places to Visit" to listOf(
                Recommendation("Museum of Art", "Explore art from around the world."),
                Recommendation("City Park", "Relax and enjoy the greenery.")
            ),
            "Activities" to listOf(
                Recommendation("City Tour", "Join a guided tour around the city."),
                Recommendation("Food Tasting", "Taste local dishes.")
            )
        )
    )
    val recommendations: State<Map<String, List<Recommendation>>> = _recommendations
}