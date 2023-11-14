package data

data class Post(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String,
    var creationTime: Long,
    var updateTime: Long,
    var isUploaded: Boolean = false
)