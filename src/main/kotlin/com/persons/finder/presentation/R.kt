package com.persons.finder.presentation

class R<T> {
    var code: String? = null
    var msg: String? = null

    // only allows data to be modified by fun ok()
    var data: T? = null
        private set

    constructor(code: String?, msg: String?, data: T) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /* functions in companion object is like static methods in Java*/
    companion object {
        fun <T> ok(data: T): R<T> {
            return R("200", "Success", data);
        }

        fun <T> ok(): R<T?> {
            return R("200", "Success", null);
        }

        fun <T> fail(error: String): R<String> {
            return R("500", "request failed", error);
        }
    }
}