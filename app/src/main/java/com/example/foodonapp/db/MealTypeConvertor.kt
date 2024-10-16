package com.example.foodonapp.db

import androidx.resourceinspection.annotation.Attribute
import androidx.room.TypeConverter

class MealTypeConvertor {
    @TypeConverter
    fun fromAnyToString(attribute: Any?) : String{
        if (attribute == null)
            return ""
        return attribute as String
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?) : Any{
        if (attribute == null)
            return ""
        return attribute
    }
}