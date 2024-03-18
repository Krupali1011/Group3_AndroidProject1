package com.example.group3project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var FeedArrayList :ArrayList<Feed>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var  feedRecyclerview : RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feedRecyclerview =findViewById(R.id.feed_recyclerview)
        feedRecyclerview.layoutManager = LinearLayoutManager(this)
        FeedArrayList = arrayListOf<Feed>()

        getFeedData()


    }

    private fun getFeedData() {
        databaseReference= FirebaseDatabase.getInstance().getReference("Connections")
        databaseReference.addValueEventListener(object: ValueEventListener {


            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (feedSnapshot in snapshot.children) {
                        val name = feedSnapshot.child("Name").getValue(String::class.java)
                        val image = feedSnapshot.child("Image").getValue(String::class.java)
                        val title = feedSnapshot.child("Title").getValue(String::class.java)
                        val description = feedSnapshot.child("Description").getValue(String::class.java)
                        val postImage = feedSnapshot.child("PostImage").getValue(String::class.java)
                        val feed = Feed(name ?: "", image ?: "", title ?: "", description ?: "", postImage ?: "")
                        FeedArrayList.add(feed)
                    }
                    feedRecyclerview.adapter = FeedAdapter(FeedArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}








