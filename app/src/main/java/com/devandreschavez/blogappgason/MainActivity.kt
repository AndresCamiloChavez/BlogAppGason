package com.devandreschavez.blogappgason

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var imgView: ImageView
    private var launchResponse = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == RESULT_OK){
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            imgView.setImageBitmap(imageBitmap)
            uploadPicture(imageBitmap)
        }else{
            Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         *imgView = findViewById(R.id.imgView)
        val btnCamera = findViewById<Button>(R.id.btnCamera)
        btnCamera.setOnClickListener {
        dispatchTakePictureIntent()
        }
         */


    }

    private fun firebaseUso(): Unit {
        val db = FirebaseFirestore.getInstance()

        db.collection("ciudades").document("LA").addSnapshotListener{ value, e ->

            value?.let {
                val ciudad = it.toObject(Ciudad::class.java)
                Log.d("Firebase", "DocumentSnapshot data: ${it.data}")
                Log.d("Firebase", "Color: ${ciudad?.population} , population ${ciudad?.color}")
            }
        }

        db.collection("ciudades").document("LA").get().addOnSuccessListener { document ->
            document?.let {
                //data devuelve todo

                val ciudad = document.toObject(Ciudad::class.java)
                Log.d("Firebase", "DocumentSnapshot data: ${document.data}")
                val color = document.getString("color")
                val population = document.getLong("population")
                Log.d("Firebase", "Color: $color , population $population")
            }
        }.addOnFailureListener { error ->
            Log.e("Firebase", error.toString())
        }

        //ingresar información

        db.collection("ciudades").document("NY").set(Ciudad(3123, "Verde")).addOnSuccessListener {
            Log.d("Firebase", "Se guardó la ciudad correctamente")
        }.addOnFailureListener { error ->
            Log.e("Firebase", error.toString())
        }
    }

    private fun dispatchTakePictureIntent(){
        //cualquier aplicación de la playstore que pueda tomar una foto
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            launchResponse.launch(takePictureIntent)
        }catch (e: ActivityNotFoundException){
            Toast.makeText(this, "No se encontro una app para tomar la foto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadPicture(bitmap: Bitmap){
        val storageRef = FirebaseStorage.getInstance().reference
        val imageRef = storageRef.child("imagenes/${UUID.randomUUID()}.jpg")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val data = baos.toByteArray()
        val uploadTask = imageRef.putBytes(data)
        uploadTask.continueWithTask { task ->
            if(!task.isSuccessful){
                task.exception?.let { exception ->
                    throw exception
                }
            }
            //retorna esto!!
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if(task.isSuccessful){
                val downloadUrl = task.result.toString()
                Log.d("STORAGE", "uploadPicture $downloadUrl")
            }
        }

    }
}

data class Ciudad(val population: Int = 0, val color: String = "")