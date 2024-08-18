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

public class BuildMeta {

    private String version;
    private String buildTime;
    private String gitRevision;

    public BuildMeta() {}

    public BuildMeta(String version, String buildTime, String gitRevision) {
        this.version = version;
        this.buildTime = buildTime;
        this.gitRevision = gitRevision;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getGitRevision() {
        return gitRevision;
    }

    public void setGitRevision(String gitRevision) {
        this.gitRevision = gitRevision;
    }

    @Override
    public String toString() {
        return "BuildMeta{" + "version='" + version + '\'' + ", buildTime='" + buildTime + '\'' + ", gitRevision='" + gitRevision + '\'' + '}';
    }

    /**
     * Get complete version string
     *
     * @return Complete version (version-buildTime-gitRevision)
     */
    public String getCompleteVersion() {
        String version = getVersion();
        String buildTime = getBuildTime();
        String gitRevision = getGitRevision();

        if (version == null)
            version = "unknown";

        if (buildTime == null)
            buildTime = "unknown";

        if (gitRevision == null)
            gitRevision = "noGit";

        return version + "-" + buildTime + "-" + gitRevision;
    }

    /**
     * Get version and build time. Will show the build time only if not null or  unknown
     *
     * @return version (00/00/0000 00:00:00 CEST)
     */
    public String getVersionAndBuildTime() {
        String string = version;

        if (buildTime != null)
            if (!buildTime.isEmpty())
                if (!buildTime.isBlank())
                    if (!buildTime.equalsIgnoreCase("unknown"))
                        string = string + " (" + buildTime + ")";

        return string;
    }
}
