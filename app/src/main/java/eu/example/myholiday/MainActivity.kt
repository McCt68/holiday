package eu.example.myholiday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import eu.example.myholiday.model.storedData
import eu.example.myholiday.screens.MainScreen
import eu.example.myholiday.screens.MyViewModel
import eu.example.myholiday.ui.theme.MyHolidayTheme

class MainActivity : ComponentActivity() {

	// Instantiate a viewModel object
	val myViewModel by viewModels<MyViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyHolidayTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colors.background
				) {
					MainScreen(myViewModel = myViewModel)

//					// firestore test
//					val myFirestoreInstance = storedData()
//					myFirestoreInstance.CreateCollection()
//
//					// Use update method from repository to update a field in the database document tester
//
//					myFirestoreInstance.updateData("Hansen", amount = 1000)
				}
			}
		}
	}
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	MyHolidayTheme {

	}
}