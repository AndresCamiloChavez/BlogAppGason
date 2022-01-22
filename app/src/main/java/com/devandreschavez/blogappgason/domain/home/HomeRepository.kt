package com.devandreschavez.blogappgason.domain.home

import com.devandreschavez.blogappgason.core.Resource
import com.devandreschavez.blogappgason.data.model.Post

interface HomeRepository {

    suspend fun getLatestPosts(): Resource<List<Post>>
}