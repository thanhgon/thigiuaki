package com.example.giuakididong

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Product(
    val name: String,
    val material: String,
    val price: String,
    val imageRes: Int,
    val description: String
)

val products = listOf(
    Product("Áo khoác gió 1 lớp mũ liền", "95% Cotton, 5% Spandex", "$12.99", R.drawable.aonam, "Áo khoác gió công nghệ Smart-Tech, cản gió, chống thấm nước."),
    Product("Áo khoác da lộn basic cổ cao ", "100% Polyester", "$15.99", R.drawable.aonam2, "Áo khoác gió công nghệ Smart-Tech, cản gió, chống thấm nước."),
    Product("Áo khoác Puffer cổ cao", "95% Rayon, 5% Spandex", "$20.99", R.drawable.aonam3, "Áo khoác gió công nghệ Smart-Tech, cản gió, chống thấm nước."),
    Product("Áo Phông dài tay giữ nhiệt", "Lightweight fabric", "$30.99", R.drawable.aonam4, "Áo khoác gió công nghệ Smart-Tech, cản gió, chống thấm nước."),
    Product("DANYOUY Womens T Shirt", "95% Cotton, 5% Spandex", "$12.99", R.drawable.ad, "A comfortable casual t-shirt with soft fabric."),
    Product("Opna Women's Short Sleeve", "100% Polyester", "$15.99", R.drawable.ad, "Lightweight and breathable short sleeve shirt."),
    Product("MBJ Women's Solid Short", "95% Rayon, 5% Spandex", "$20.99", R.drawable.ad, "Stylish and stretchable solid short sleeve."),
    Product("Rain Jacket Women", "Lightweight fabric", "$30.99", R.drawable.ad, "Perfect for rainy days with water-resistant material.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(product: Product, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(product.name) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFEDE7F6))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier.fillMaxWidth().height(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Reviews", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.price, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Xử lý thêm vào giỏ hàng */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("ADD TO CART", color = Color.White)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = product.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = product.material, style = MaterialTheme.typography.bodySmall, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFD700), modifier = Modifier.size(16.dp))
                    Text(text = "4.5", style = MaterialTheme.typography.bodySmall)
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(text = product.price, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onClick() },
                   // shape = CircleShape,
                    modifier = Modifier.size(36.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Detail", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ProductList(onProductClick: (Product) -> Unit) {
    LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
        items(products) { product ->
            ProductItem(product = product, onClick = { onProductClick(product) })
        }
    }
}
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    if (selectedProduct == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("New Arrivals") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFEDE7F6))
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                ProductList { product -> selectedProduct = product }
            }
        }
    } else {
        ProductDetailScreen(product = selectedProduct!!) {
            selectedProduct = null
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}
