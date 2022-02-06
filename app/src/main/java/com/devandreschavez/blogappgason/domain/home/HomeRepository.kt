package com.devandreschavez.blogappgason.domain.home

import com.devandreschavez.blogappgason.core.Result
import com.devandreschavez.blogappgason.data.model.Post

interface HomeRepository {

    suspend fun getLatestPosts(): Result<List<Post>>
}