package com.basma.data.local.products

import androidx.room.TypeConverter
import com.basma.data.remote.categories.models.CategoryResponse
import com.google.gson.Gson

class TypeConverter {
    //converts the CategoryResponse object to a JSON string using the Gson library
    @TypeConverter
    fun fromCategoryResponseToJson(categoryResponse: CategoryResponse?): String? {
        return Gson().toJson(categoryResponse)
    }

    @TypeConverter
    fun fromJsonToCategoryResponse(json: String?): CategoryResponse? {
        return Gson().fromJson(json, CategoryResponse::class.java)
    }
}