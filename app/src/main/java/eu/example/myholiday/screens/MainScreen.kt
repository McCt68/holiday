package eu.example.myholiday.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.example.myholiday.MyTextField
import eu.example.myholiday.ShowTotalSavings
import eu.example.myholiday.ShowTotalSavingsFromFirestore


//
@Composable
fun MainScreen(myViewModel: MyViewModel) {

	Scaffold {
		Column(
			modifier = Modifier.fillMaxSize(),
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			ShowTotalSavings(myViewModel.calculatedSum.toString())

			Divider(
				modifier = Modifier
					.fillMaxWidth()
					.height(8.dp)
			)
			MyTextField(
				label = "Juster beløb",
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
				singleLine = true,
				// This value is shown in the text field
				value = myViewModel.adjustSavings,
				// This should update the state of value field
				onValueChanged = { myViewModel.onMySavingsChanged(it) })
			Divider(
				modifier = Modifier
					.fillMaxWidth()
					.height(8.dp)
			)

			Row(
				horizontalArrangement = Arrangement.SpaceEvenly,
				verticalAlignment = Alignment.CenterVertically
			) {
				Button(onClick = { myViewModel.changeTotalSum(myViewModel.adjustSavings) }) {
					Text(text = "tilføj beløb")

				}
				Button(onClick = { /*TODO*/ }) {
					Text(text = "Fratræk beløb")

				}

			}
			Divider(
				modifier = Modifier
					.fillMaxWidth()
					.height(8.dp)
			)

			Button(onClick = { myViewModel.clearTotal() }) {
				Text(text = "Clear All")

			}

			Divider(
				modifier = Modifier
					.fillMaxWidth()
					.height(8.dp)
			)

			// With StateFlow - Here I collect the flow from the viewModel
			var collectedFlow = myViewModel.name.collectAsState()
			MyTextField(
				label = "Name from viewModel",
				// value = myViewModel.name.value,
				value = collectedFlow.value,
				singleLine = true,
				onValueChanged = { newText ->
					myViewModel.setName(newText) }

			)

			Divider(
				modifier = Modifier
					.fillMaxWidth()
					.height(8.dp)
			)

			// This does not show the value of name. it just shows Unit
			Text(text = myViewModel.readField().toString())
			
			// Here should be a composable that can show data from firestore -
			// it should show collection users - document holidaySavings - fields name, and savings
			// it should get these values from observing the viewModel, and show them in a Text composable
//			ShowTotalSavingsFromFirestore(totalSaved = "5000", myViewModel.UpdateFireStoreData(
//				name = "Hans",
//				savings = 95000
//			))
			
		}

		// FIRESTORE TESTING
		// Create a firebaseFirestore document - Comment his out, to not have it create a document each time i run
		//myViewModel.CreateFirebaseDocument()

		// Update firestore test
		myViewModel.UpdateFireStoreData("bent", 850)

		myViewModel.ReadTest()


	}
}

@Preview
@Composable
fun myPreview() {
	MainScreen(myViewModel = MyViewModel())
}
