package com.example.exception

class CustomException(val errorCode: ErrorCode) : Exception(errorCode.reason)
