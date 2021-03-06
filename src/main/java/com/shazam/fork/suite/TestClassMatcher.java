/*
 * Copyright 2015 Shazam Entertainment Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.shazam.fork.suite;

import java.util.regex.Pattern;

public class TestClassMatcher {
    private final Pattern packagePattern;
    private final Pattern classPattern;

    public TestClassMatcher(Pattern packagePattern, Pattern classPattern) {
        this.packagePattern = packagePattern;
        this.classPattern = classPattern;
    }

    boolean matchesPatterns(String typeDescriptor) {
        String packageName = getPackageName(typeDescriptor);
        String className = getClassName(typeDescriptor);
        return packagePattern.matcher(packageName).matches() && classPattern.matcher(className).matches();
    }

    private String getClassName(String typeDescriptor) {
        int finalSlashIndex = getFinalSlashIndex(typeDescriptor);
        return typeDescriptor.substring(finalSlashIndex + 1, typeDescriptor.length() - 1);
    }

    private String getPackageName(String typeDescriptor) {
        int finalSlashIndex = getFinalSlashIndex(typeDescriptor);
        return typeDescriptor.substring(1, finalSlashIndex).replace('/', '.');
    }

    private int getFinalSlashIndex(String typeDescriptor) {
        return typeDescriptor.lastIndexOf('/');
    }
}
