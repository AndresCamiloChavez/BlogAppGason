package com.devandreschavez.blogappgason.domain.home

import com.devandreschavez.blogappgason.core.Resource
import com.devandreschavez.blogappgason.data.model.Post
import com.devandreschavez.blogappgason.data.remote.home.HomeDataSource

class HomeRepositoryImpl(private val homeDataSource: HomeDataSource): HomeRepository{
    override suspend fun getLatestPosts(): Resource<List<Post>> = homeDataSource.getLastestPost()
}