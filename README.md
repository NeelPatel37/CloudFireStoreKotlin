<h2>Firebase</h2>

<h4>Add Firebase Dependency</h4>

<h4>Project Level Build Gradle File</h4>
<pre>
id("com.google.gms.google-services") version "4.4.2" apply false
</pre>

<h4>Root Level Build Gradle File</h4>
<pre>
implementation(platform("com.google.firebase:firebase-bom:33.10.0"))
implementation("com.google.firebase:firebase-analytics")
</pre>

<h4>FireStore Database Dependency</h4>
<pre>
     implementation("com.google.firebase:firebase-firestore")
</pre>

<h4>Firebase Cloud Database </h4>
<p>Store data into document and collection </p>


<h4>Create Firebase Cloud Database</h4>
<pre>
    val db = Firebase.firestore
</pre>

<h4>Write Data into Firebase cloud</h4>
<pre>
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
</pre>

<h4>Read Single Data from Firebase Cloude Databse</h4>
<pre>
val docRef=db.collection("Users").document("user1")
        docRef.get().addOnSuccessListener {document->
            binding.texview.text= document.data?.get("name").toString()
        }
</pre>

<h4>Read All Data from Firebase Cloude Databse</h4>
<pre>
db.collection("Users").get().addOnSuccessListener { result->
            for (document in result){
                val name=document.get("name").toString()
                val lastname=document.get("lastName").toString()
                val birthDate=document.get("Birth Date").toString()

                val user=User(name,lastname,birthDate)
                userList.add(user)
                userListAdapter.notifyDataSetChanged()
</pre>

<h4> Update Data from Firebase Cloude Databse</h4>
<pre>
 db.collection("Users")
            .document("user1")
            .update("name","XYZ")
</pre>

<h4> Delete Data from Firebase Cloude Databse</h4>
<pre>
    db.collection("Users")
            .document("user2")
            .delete()
</pre>




