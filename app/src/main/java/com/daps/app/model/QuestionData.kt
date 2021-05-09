package com.daps.app.model

import com.google.gson.annotations.SerializedName

data class Question(@SerializedName("@text") val text: String, @SerializedName("option") val options: List<Question>)
data class GetQuestionsResponse(val question: Question)