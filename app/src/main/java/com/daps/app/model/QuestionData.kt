package com.daps.app.model

import com.google.gson.annotations.SerializedName

data class Question(@SerializedName("@text") val text: String, @SerializedName("option") val options: List<Option>)
data class GetQuestionsResponse(val question: Question)
data class Option(@SerializedName("@text") val text: String, @SerializedName("question") val question: Question)
data class PostResponses(@SerializedName("responses") val responses: List<String>)
