package lat.pam.mycityapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CategoriesScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Places to Visit",
            fontSize = 20.sp,
            modifier = Modifier.clickable { navController.navigate("places") }
        )
        Text(
            text = "Activities",
            fontSize = 20.sp,
            modifier = Modifier.clickable { navController.navigate("activities") }
        )
    }
}