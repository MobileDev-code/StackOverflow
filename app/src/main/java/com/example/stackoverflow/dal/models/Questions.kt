package com.example.stackoverflow.dal.models

data class Questions (
    var items: List<Question>,
    var hasMore: Boolean,
    var quotaMax: Long,
    var quotaRemaining: Long
) {
    constructor() : this(ArrayList<Question>(), false, 0,0)
}


data class Question (
    var tags: List<String>,
    var owner: Owner,
    var isAnswered: Boolean,
    var viewCount: Long,
    var answerCount: Long,
    var score: Long,
    var lastActivityDate: Long,
    var creationDate: Long,
    var lastEditDate: Long? = null,
    var questionID: Long,
    var contentLicense: ContentLicense? = null,
    var link: String,
    var title: String,
    var body: String,
    var closedDate: Long? = null,
    var closedReason: String? = null,
    var acceptedAnswerID: Long? = null
){
    // Allow parameterless constructor
    constructor() : this(ArrayList<String>(), Owner(), false, 0,0,0,0, 0,
        null,0,null, "","","",null,null, null)
}

enum class ContentLicense {
    CcBySa40
}

data class Owner (
    var reputation: Long,
    var userID: Long,
    var userType: UserType,
    var profileImage: String,
    var displayName: String,
    var link: String,
    var acceptRate: Long? = null
){
    constructor() : this(0,0,UserType.Registered,"","","",null)
}

enum class UserType {
    Registered
}
