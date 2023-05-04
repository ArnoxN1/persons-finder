package com.persons.finder.presentation

/**
 * common result return
 */
class R<T> {
    var code: String? = null
    var msg: String? = null

    // use private set only allows data to be modified by fun ok()
    var data: T? = null
        private set

    constructor(code: String?, msg: String?, data: T) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     *  functions in companion object is like static methods in Java
     *  public static <T> R<T> ok(T data)
     *  */
    companion object {
        fun <T> ok(data: T): R<T> {
            return R("200", "Success", data);
        }

        fun <T> ok(): R<T?> {
            return R("200", "Success", null);
        }

        fun fail(error: String): R<String> {
            return R("500", "request failed", error);
        }
    }
}