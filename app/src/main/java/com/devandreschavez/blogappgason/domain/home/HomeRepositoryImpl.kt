package com.devandreschavez.blogappgason.domain.home

import com.devandreschavez.blogappgason.core.Result
import com.devandreschavez.blogappgason.data.model.Post
import com.devandreschavez.blogappgason.data.remote.home.HomeDataSource

class HomeRepositoryImpl(private val homeDataSource: HomeDataSource): HomeRepository{
    override suspend fun getLatestPosts(): Result<List<Post>> = homeDataSource.getLastestPost()
}