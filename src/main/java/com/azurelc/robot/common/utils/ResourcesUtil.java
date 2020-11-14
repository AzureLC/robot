package com.azurelc.robot.common.utils;

import java.io.InputStream;

public final class ResourcesUtil {
    private ResourcesUtil() {
    }

    public static InputStream getResourceAsStream(String path) {
        String tempPath = path;
        if (!path.startsWith("/")) {
            tempPath = "/" + path;
        }
        return ResourcesUtil.class.getResourceAsStream(tempPath);
    }
}
