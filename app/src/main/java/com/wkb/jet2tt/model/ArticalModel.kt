package com.wkb.jet2tt.model

data class ArticalModel(
	val createdAt: String? = null,
	val comments: Int? = null,
	val id: String? = null,
	val media: List<MediaItem?>? = null,
	val user: List<UserItem?>? = null,
	val content: String? = null,
	val likes: Int? = null
)
