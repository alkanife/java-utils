// MIT License
//
// Copyright (c) 2024 Arthur Beau ("Alkanife", "Alka") @ https://alka.dev
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
package dev.alka.utils.builds;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.stream.Collectors;

public class BuildUtils {

    /**
     * Retrieves the meta of a build from its meta.properties file.
     *
     * @param artifactId ArtifactId of the project
     * @return The build meta, or null
     */
    public static BuildMeta readBuild(String artifactId) {
        return readBuildFromPropertiesPath("/" + artifactId + "-meta.properties");
    }

    /**
     * Retrieves the meta of a build from its meta.properties file.
     *
     * @param propertiesPath Path to the properties file
     * @return The build meta, or null
     */
    public static BuildMeta readBuildFromPropertiesPath(String propertiesPath) {
        String version;
        String buildTime;
        String gitRevision;

        try {
            String content = readResourceAsString(propertiesPath);

            Properties properties = new Properties();
            properties.load(new StringReader(content));

            // Parse properties
            version = properties.getProperty("build-version", "unknown");
            buildTime = properties.getProperty("build-time", "unknown");
            gitRevision = properties.getProperty("git-revision", "none");

            // Handle maven fails
            if (version.equalsIgnoreCase("${project.version}"))
                version = "unknown";

            if (version.equalsIgnoreCase("${build.time}"))
                buildTime = "unknown";

            if (version.equalsIgnoreCase("${git.revision}"))
                gitRevision = "none";
        } catch (Exception exception) {
            return null;
        }

        return new BuildMeta(version, buildTime, gitRevision);
    }

    /**
     * Read resource as string
     *
     * @param resourcePath Path of the resource (ex. "/build-date.txt")
     * @return A string containing the contents of the resource
     * @throws Exception If the resource was not found or is not readable
     */
    public static String readResourceAsString(String resourcePath) throws Exception {
        try (InputStream inputStream = BuildUtils.class.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    /**
     * Get if a version is a snapshot.
     *
     * @param version Version
     * @return True if version contains "snapshot", "dev", or "preview"
     */
    public static boolean isSnapshot(String version) {
        String vLower = version.toLowerCase();

        return vLower.contains("snapshot")
                || vLower.contains("dev")
                || vLower.contains("preview");
    }
}
