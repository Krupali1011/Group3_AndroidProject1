/* @Author -Jojina Thomas */
package com.example.group3project1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CandidateActivity : AppCompatActivity() {
    // ArrayList to store candidate datas.
    private lateinit var  CandidateArrayList :ArrayList<Candidate>
    // database reference
    private lateinit var dataref :DatabaseReference
    // RecyclerView for candidate displaying
    private lateinit var  candidaterecyclerview :RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate)

       // Initializing the RecyclerView and setting the layout manager.
        candidaterecyclerview =findViewById(R.id.candidaterview)
        candidaterecyclerview.layoutManager =LinearLayoutManager(this)


        //store all the candidate data in arraylist
        CandidateArrayList = arrayListOf<Candidate>()

        // Fetch candidate data from Firebase
        getCandidateData()

    }

    private fun getCandidateData() {
        // Get a reference to the Candidate
        dataref= FirebaseDatabase.getInstance().getReference("Candidate")

// Add a ValueEventListener to listen for changes in the data
        dataref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // verify that if data exists in the "Candidate" module
   if (snapshot.exists()){
//       Iterate through the children
       for (candidateSnapshot in snapshot.children){
           // Retrieve candidate data and convert it to a Candidate object
           val Candidate = candidateSnapshot.getValue(Candidate::class.java)
           if (Candidate != null) {
               // Add the candidate to the ArrayList for later display in the RecyclerView
               CandidateArrayList.add(Candidate)
           }
       }
       // Set up the RecyclerView with the candidate data using a custom adapter
       candidaterecyclerview.adapter=MyAdapter(CandidateArrayList)
   }

            }
            //Handle any errors that occur during data retrieval
            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}