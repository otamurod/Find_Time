package uz.otamurod.kmp.findtime

import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class TimeZoneHelperImpl : TimeZoneHelper {
    override fun getTimeZoneStrings(): List<String> {
        return TimeZone.availableZoneIds.sorted()
    }

    override fun currentTime(): String {
        val currentMoment: Instant = Clock.System.now()
        val dateTime: LocalDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

        return formatDateTime(dateTime)
    }

    override fun currentTimeZone(): String {
        val currentTimeZone = TimeZone.currentSystemDefault()
        return currentTimeZone.toString()
    }

    override fun hoursFromTimeZone(otherTimeZoneId: String): Double {
        val currentTimeZone = TimeZone.currentSystemDefault()
        val currentUTCInstant: Instant = Clock.System.now()
        val otherTimeZone = TimeZone.of(otherTimeZoneId)
        val currentDateTime: LocalDateTime = currentUTCInstant.toLocalDateTime(currentTimeZone)
        val currentOtherDateTime: LocalDateTime = currentUTCInstant.toLocalDateTime(otherTimeZone)

        return abs((currentDateTime.hour - currentOtherDateTime.hour) * 1.0)
    }

    override fun getTime(timezoneId: String): String {
        val timeZone = TimeZone.of(timezoneId)
        val currentMoment: Instant = Clock.System.now()
        val dateTime: LocalDateTime = currentMoment.toLocalDateTime(timeZone)

        return formatDateTime(dateTime)
    }

    override fun getDate(timezoneId: String): String {
        val timeZone = TimeZone.of(timezoneId)
        val currentMoment: Instant = Clock.System.now()
        val dateTime: LocalDateTime = currentMoment.toLocalDateTime(timeZone)

        return "${dateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }}, " +
                "${
                    dateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }
                } ${dateTime.date.dayOfMonth}"
    }

    override fun search(startHour: Int, endHour: Int, timezoneStrings: List<String>): List<Int> {
        val goodHours = mutableListOf<Int>()
        val timeRange = IntRange(max(0, startHour), min(23, endHour))
        val currentTimeZone = TimeZone.currentSystemDefault()

        for (hour in timeRange) {
            var isGoodHour = false
            for (zone in timezoneStrings) {
                val timeZone = TimeZone.of(zone)

                if (timeZone == currentTimeZone) {
                    continue
                }

                if (!isValid(
                        timeRange = timeRange,
                        hour = hour,
                        currentTimeZone = currentTimeZone,
                        otherTimeZone = timeZone
                    )
                ) {
                    Napier.d("Hour $hour is not valid for time range")
                    isGoodHour = false
                    break
                } else {
                    Napier.d("Hour $hour is Valid for time range")
                    isGoodHour = true
                }
            }
            if (isGoodHour){
                goodHours.add(hour)
            }
        }

        return goodHours
    }

    private fun isValid(
        timeRange: IntRange,
        hour: Int,
        currentTimeZone: TimeZone,
        otherTimeZone: TimeZone
    ): Boolean {
        if (hour !in timeRange) {
            return false
        }

        val currentUTCInstant: Instant = Clock.System.now()
        val currentOtherDateTime: LocalDateTime = currentUTCInstant.toLocalDateTime(otherTimeZone)
        val otherDateTimeWithHour = LocalDateTime(
            currentOtherDateTime.year,
            currentOtherDateTime.monthNumber,
            currentOtherDateTime.dayOfMonth,
            hour,
            0,
            0,
            0
        )

        val localInstant = otherDateTimeWithHour.toInstant(currentTimeZone)
        val convertedTime = localInstant.toLocalDateTime(otherTimeZone)

        Napier.d("Hour $hour in Time Range ${otherTimeZone.id} is ${convertedTime.hour}")

        return convertedTime.hour in timeRange
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        val stringBuilder = StringBuilder()
        var hour = dateTime.hour
        var minute = dateTime.minute
        var amPm = " am"

        if (hour > 12) {
            amPm = " pm"
            hour -= 12
        }

        stringBuilder.append(hour.toString())
        stringBuilder.append(":")
        if (minute < 10) {
            stringBuilder.append("0")
        }
        stringBuilder.append(minute.toString())
        stringBuilder.append(amPm)

        return stringBuilder.toString()
    }
}