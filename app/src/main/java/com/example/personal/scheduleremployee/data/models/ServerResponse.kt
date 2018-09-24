package com.example.personal.scheduleremployee.data.models

/**
 * Created by coral on 17/11/2017.
 */

class ServerResponse<T>(
        var state: Boolean = false,
        var error: String? = null,
        var data: T? = null,
        var sizeList: Int = 0
)