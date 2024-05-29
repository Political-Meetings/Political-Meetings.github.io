package fr.xibalba.politicalMeetings.utils

import kotlin.js.Promise

fun <T> Promise<Promise<T>>.flatten(): Promise<T> {
    return this.then { it }
}