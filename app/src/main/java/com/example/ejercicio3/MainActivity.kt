package com.example.ejercicio3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview


data class Contact(
    val name: String,
    val phoneNumber: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactListScreen()
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactListScreen() {

    val contacts = listOf(
        Contact("Alice", "123-456-7890"),
        Contact("Irene", "555-222-3333"),
        Contact("Gabriel", "555-333-4444"),
        Contact("Frank", "555-111-2222"),
        Contact("Javier", "555-666-7777"),
        Contact("Eva", "555-555-5555"),
        Contact("David", "555-987-6543"),
        Contact("Charlie", "555-123-4567"),
        Contact("Hannah", "555-888-9999"),
        Contact("Bob", "987-654-3210"),
        Contact("Laura", "927-654-3210"),
        Contact("Sara", "987-656-3210")
    )


    val sortedContacts = contacts.sortedBy { it.name }


    val groupedContacts = sortedContacts.groupBy { it.name.first().toUpperCase() }

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Contactos",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            groupedContacts.forEach { (initial, contactList) ->
                stickyHeader {
                    SectionHeader(initial)
                }

                items(contactList) { contact ->
                    ContactItem(contact)
                }
            }
        }
    }
}


@Composable
fun SectionHeader(initial: Char) {
    Text(
        text = initial.toString(),
        style = MaterialTheme.typography.titleMedium.copy(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray.copy(alpha = 0.2f))
            .padding(16.dp)
    )
}

@Composable
fun ContactItem(contact: Contact) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = contact.phoneNumber,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListScreenPreview() {
    ContactListScreen()
}



