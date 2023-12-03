package data

data class Post(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String,
    var updateTime: Long,
    var isCreatedLocal: Boolean = false,
)