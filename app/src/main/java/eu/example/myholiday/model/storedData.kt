package eu.example.myholiday.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import com.google.firebase.firestore.FirebaseFirestore

class storedData() {

	val db = FirebaseFirestore.getInstance()

	// Create a collection in firestore
	@Composable
	fun CreateNewCollection(){

		val db = FirebaseFirestore.getInstance()

		// Pass data onto the firestore db, it must be of type hashmap
		val user = hashMapOf(
			"first" to "Michael",
			"last" to "C",
			"Opsparing" to 10000
		)

		// add the user data to the collection
		db.collection("users")
			.add(user)
			.addOnSuccessListener {
				Log.d("FB", "onCreate: ${it.id}")
			}

			.addOnFailureListener {
				Log.d("FB", "onCreate: ${it}")
			}
	}

	// Update fields in document or create if they don't exist
	fun updateData(name: String, savings: Int){
		val updater = db.collection("users").document("holidaySavings")
		updater.update("name", name)
		updater.update("savings", savings)

	}

	// Read data from Firestore
	fun readFirestoreData(){
		db.collection("users")
			.get()
			.addOnSuccessListener { result ->
				for (document in result) {
					Log.d("read", "${document.id} => ${document.data}")
				}
			}
			.addOnFailureListener { exception ->
				Log.w("read", "Error getting documents.", exception)
			}
	}

	// READ DATA TEST
	fun readDataTest(){
		val docRef = db.collection("users").document("holidaySavings")
		docRef.get()
			.addOnSuccessListener { document ->
				if (document != null) {
					Log.d("Rtest", "DocumentSnapshot data: ${document.data}")
				} else {
					Log.d("Rtest", "No such document")
				}
			}
			.addOnFailureListener { exception ->
				Log.d("Rfail", "get failed with ", exception)
			}
	}

	// Another way to transform firestore data to kotlin ??
	fun transformFireStoreToKotlin(): String{
		var toReturn: String = ""
		val docRef = db.collection("users").document("holidaySavings")
		docRef.get().addOnSuccessListener { documentSnapshot ->
			var sendStringBack = documentSnapshot.get("name")
			toReturn = documentSnapshot.get("name").toString()
			// need to understand this better ??
			// val city = documentSnapshot.toObject<City>()
			// val city = documentSnapshot.

		}
		return toReturn
	}


}