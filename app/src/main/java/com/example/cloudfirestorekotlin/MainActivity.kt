package com.example.cloudfirestorekotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cloudfirestorekotlin.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val userList:ArrayList<User> = arrayListOf()
    private lateinit var userListAdapter: UserListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        FirebaseApp.initializeApp(this)

        setContentView(binding.root)
        init()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun init(){
        val db = Firebase.firestore
        setUpAdapter()

        //write data into firestore
        val user1= hashMapOf(
            "name" to "Neel",
            "lastName" to "Patel",
            "Birth Date" to "May 26,2002"
        )
        val user2= hashMapOf(
            "name" to "Raj",
            "lastName" to "Patel",
            "Birth Date" to "June 29,2002"
        )

        val user_collection=db.collection("Users")
        user_collection.document("user1").set(user1)
        user_collection.document("user2").set(user2)


        //read data into firestore

        //read only single data
//        val docRef=db.collection("Users").document("user1")
//
//        docRef.get().addOnSuccessListener {document->
//            binding.texview.text= document.data?.get("name").toString()
//        }


        //read all the data from the firestore
        db.collection("Users").get().addOnSuccessListener { result->
            for (document in result){
                val name=document.get("name").toString()
                val lastname=document.get("lastName").toString()
                val birthDate=document.get("Birth Date").toString()

                val user=User(name,lastname,birthDate)
                userList.add(user)
                userListAdapter.notifyDataSetChanged()

            }

        }

        // update value into the firestore

        db.collection("Users")
            .document("user1")
            .update("name","Ravi")

        // delete value into the fire store
        db.collection("Users")
            .document("user2")
            .delete()



    }

    private fun setUpAdapter(){
        userListAdapter=UserListAdapter(userList)
        binding.rvUserList.adapter=userListAdapter
    }
}