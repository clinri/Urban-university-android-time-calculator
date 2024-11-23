package ru.borisov.timecalculator

class TimeOperation(first: String, second: String) {

    private val resultFirstSeconds: Int = convertStringToSeconds(first)
    private val resultSecondSeconds: Int = convertStringToSeconds(second)

    private fun formatToString(seconds: Int): String {
        val hours = seconds / SECONDS_IN_HOUR
        val minutes = (seconds % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE
        val seconds = seconds % SECONDS_IN_MINUTE
        return "${hours}h${minutes}m${seconds}s"
    }

    private fun convertStringToSeconds(string: String): Int {
        val hoursSplit = try {
            val split = string.split("h")
            Pair(split[0], split[1])
        } catch (e: Exception) {
            Pair("0", string)
        } as Pair<String, String>
        val hours = hoursSplit.first.toInt()
        val minutesSplit = try {
            val split = hoursSplit.second.split("m")
            Pair(split[0], split[1])
        } catch (e: Exception) {
            Pair("0", hoursSplit.second)
        } as Pair<String, String>
        val minutes = minutesSplit.first.toInt()
        val seconds = try {
            val split = minutesSplit.second.split("s")
            split[0].toInt()
        } catch (e: Exception) {
            0
        } as Int
        return hours * SECONDS_IN_HOUR + minutes * SECONDS_IN_MINUTE + seconds
    }

    fun plus() = formatToString(resultFirstSeconds + resultSecondSeconds)
    fun minus() = formatToString(resultFirstSeconds - resultSecondSeconds)

    companion object {
        const val SECONDS_IN_HOUR = 3600
        const val SECONDS_IN_MINUTE = 60
    }
}