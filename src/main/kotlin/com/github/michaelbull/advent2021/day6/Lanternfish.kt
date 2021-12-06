package com.github.michaelbull.advent2021.day6

private val TIMER_RANGE = 0L..8L
private val TIMER_COUNT = TIMER_RANGE.count()

@JvmInline
value class LanternfishSchool(private val timers: List<Long>) {

    init {
        require(timers.size == TIMER_COUNT) {
            "timers.size must be $TIMER_COUNT, but was ${timers.size}"
        }
    }

    val count: Long
        get() = timers.sum()

    fun countAt(days: Int): Long {
        return ageSequence().elementAt(days).count
    }

    private fun age(): LanternfishSchool {
        return LanternfishSchool(timers.indices.map(::age))
    }

    private fun age(day: Int): Long {
        return when (day) {
            6 -> timers[0] + timers[day + 1]
            8 -> timers[0]
            else -> timers[day + 1]
        }
    }

    private fun ageSequence(): Sequence<LanternfishSchool> {
        return generateSequence(this, LanternfishSchool::age)
    }
}

fun String.toLanternfishSchool(): LanternfishSchool {
    val initial = this.split(",").map(String::toLong)

    val timers = TIMER_RANGE.map { timer ->
        initial.count { it == timer }.toLong()
    }

    return LanternfishSchool(timers)
}
