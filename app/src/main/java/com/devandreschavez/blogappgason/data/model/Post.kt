package com.devandreschavez.blogappgason.data.model

import com.google.firebase.Timestamp

data class Post(
    val profilePicture: String = "",
    val profileName: String = "",
    val posTimeStamp: Timestamp? = null,
    val profileImage: String = ""
)
