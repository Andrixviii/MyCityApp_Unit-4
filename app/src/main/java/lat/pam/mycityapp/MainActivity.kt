package lat.pam.mycityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import lat.pam.mycityapp.ui.theme.MyCityAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityAppTheme {
                NavigationApp()
            }
        }
    }
}

@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("category/{categoryName}") { backStackEntry ->
            CategoryScreen(
                navController = navController,
                categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            )
        }
        composable(
            "detail/{recommendationName}/{imageId}",
            arguments = listOf(
                navArgument("recommendationName") { type = NavType.StringType },
                navArgument("imageId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val recommendationName = backStackEntry.arguments?.getString("recommendationName") ?: ""
            val imageId = backStackEntry.arguments?.getInt("imageId") ?: 0
            RecommendationDetailScreen(navController, recommendationName, imageId)
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val categories = listOf("Cafe", "Restoran", "Taman")
    val categoryImages = listOf(
        R.drawable.category_image1,
        R.drawable.category_image2,
        R.drawable.category_image3

    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My City") })
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            itemsIndexed(categories) { index, category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("category/$category")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = categoryImages[index]),
                        contentDescription = "Category Image",
                        modifier = Modifier.size(40.dp).padding(end = 8.dp)
                    )
                    Text(text = category)
                }
                Divider()
            }
        }
    }
}

@Composable
fun CategoryScreen(navController: NavController, categoryName: String) {
    val recommendations = listOf(
        "Sadrasa Kitchen and Bar", "Hummingbird Eatery & Space", "Monomono"

    )
    val recommendationImages = listOf(
        R.drawable.recommendation_image1,
        R.drawable.recommendation_image2,
        R.drawable.recommendation_image3

    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            itemsIndexed(recommendations) { index, recommendation ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            // Navigasi ke layar detail dengan mengirimkan nama rekomendasi dan ID gambar
                            navController.navigate("detail/$recommendation/${recommendationImages[index]}")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = recommendationImages[index]),
                        contentDescription = "Recommendation Image",
                        modifier = Modifier.size(40.dp).padding(end = 8.dp)
                    )
                    Text(text = recommendation)
                }
                Divider()
            }
        }
    }
}

@Composable
fun RecommendationDetailScreen(navController: NavController, recommendationName: String, imageId: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recommendationName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Detail Image",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Details of $recommendationName")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "an innovative eatery designed to enjoy traditional recipes with a modern twist or indulge in international favourites. Take your time to explore the flavors of a quick breakfast, casual business lunch or celebratory dinner with friends.")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Whatever the occasion, whatever the time, Sadrasa Kitchen & Bar is always ready.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCityAppTheme {
        NavigationApp()
    }
}
