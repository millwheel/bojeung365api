package com.example.bojeung365api.util;

public final class FileUtils {

    public static String extractExtension(String fileName) {
        return (fileName != null && fileName.contains("."))
                ? fileName.substring(fileName.lastIndexOf('.'))
                : "";
    }
}
