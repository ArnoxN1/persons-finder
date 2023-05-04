package com.persons.finder.exceptions

import java.lang.RuntimeException

/**
 * () after class name is more like a main constructor with necessary parameters
 */
class CustomizeException (message: String?) : RuntimeException (message) {
}