package com.laimiux.lce

fun <Success> Result<Success>.asCT(): CT<Success> {
    return fold(
        onSuccess = { CT.content(it) },
        onFailure = { CT.error(it) },
    )
}