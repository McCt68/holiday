package eu.example.myholiday.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import eu.example.myholiday.model.storedData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewModel() : ViewModel() {

	// State - Adjust the savings with a new entry
	var adjustSavings by mutableStateOf("")

	// Event - Method to change to the state of AdjustSavings
	fun onMySavingsChanged(newAmount: String) {
		adjustSavings = newAmount // update value of textField
	}

	// The total amount of savings
	var calculatedSum by mutableStateOf(0)

	// Event - Update totalSavings
	fun changeTotalSum(addAmount: String) {
		calculatedSum = addAmount.toInt() + calculatedSum
		// trying to reset adjustSavings after calculatedSum has been updated
		adjustSavings = ""
	}

	fun calculateSum(newAmount: String): Int {
		var result: Int = calculatedSum + newAmount.toInt()
		return result
	}

	// Clear Total
	fun clearTotal() {
		adjustSavings = ""
		calculatedSum = 0
	}


	// STATEFLOW - must collect the stateFlow in the UI layer
	private val _name =
		MutableStateFlow("") // only used in the viewModel - Can be changed in the viewModel
	val name = _name.asStateFlow() // can be used from the View ( UI) - Read Only

	// for some reason the text don't change when i call this function in the view??
	fun setName(name: String) {
		_name.value = name
	}


	// Create a new Firestore document with function from -
	// storedData CreateNewCollection
	@Composable
	fun CreateFirebaseDocument(){
		val myFirestoreInstance = storedData()
		// myFirestoreInstance.CreateNewCollection()


		myFirestoreInstance.updateData("Bettine", savings = 30000)
	}
	// Use update method from repository to update a field in the database document tester
	@Composable
	fun UpdateFireStoreData(name: String, savings: Int) {
		val myFirestoreInstance = storedData()
		myFirestoreInstance.updateData(name = name, savings = savings)

	}

	// USE THE READ TEST
	@Composable
	fun ReadTest() {
		val myFirestoreInstance = storedData()
		myFirestoreInstance.readDataTest()
	}
}