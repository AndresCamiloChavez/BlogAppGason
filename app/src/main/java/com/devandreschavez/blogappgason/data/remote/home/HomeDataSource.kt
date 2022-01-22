package com.devandreschavez.blogappgason.data.remote.home

import com.devandreschavez.blogappgason.core.Resource
import com.devandreschavez.blogappgason.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeDataSource {

    suspend fun getLastestPost(): Resource<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot =
            FirebaseFirestore.getInstance().collection("posts").get()
                .await().documents.forEach { document ->
                document.toObject(Post::class.java)?.let { post -> postList.add(post) }
            }
        return Resource.Success(postList)
    }
}