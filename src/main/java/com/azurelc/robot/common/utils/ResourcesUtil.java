package com.azurelc.robot.common.utils;

import java.net.URL;
import org.apache.commons.lang3.StringUtils;

public final class ResourcesUtil {
    private ResourcesUtil() {
    }

    public static URL getResourcesURL(String path) {
        String tempPath = path;
        if (!StringUtils.startsWith(path, "/")) {
            tempPath = "/" + path;
        }
        return ResourcesUtil.class.getResource(tempPath);
    }
}
