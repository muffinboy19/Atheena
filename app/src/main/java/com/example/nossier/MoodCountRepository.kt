package com.example.nossier

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object MoodCountRepository {
    private val moodCountMap = HashMap<String, MutableLiveData<Int>>()

    init {
        // Initialize mood counts with initial values
        moodCountMap["Happy"] = MutableLiveData(0)
        moodCountMap["Sad"] = MutableLiveData(0)
        moodCountMap["Joyful"] = MutableLiveData(0)
        moodCountMap["Extreme Sad"] = MutableLiveData(0)
        moodCountMap["Normal"] = MutableLiveData(0)
        // Initialize other mood types as needed
    }

    fun getMoodCount(mood: String): LiveData<Int> {
        return moodCountMap[mood]!!
    }

    fun incrementMoodCount(mood: String) {
        val countLiveData = moodCountMap[mood]
        countLiveData?.value = countLiveData?.value?.plus(1)
    }
}
