# Build utils
A collection of utility classes for Java builds.

**Add to your pom.xml:**
````xml
<repository>
    <id>alka-repo</id>
    <url>https://repo.alka.dev/repository/maven-public/</url>
</repository>

<dependency>
    <groupId>dev.alka</groupId>
    <artifactId>build-utils</artifactId>
    <version>VERSION</version>
</dependency>
````

## Build meta
All my projects are built to have a properties file containing the version, build date, and git revision.

````java
private BuildMeta getBuildMetaFromFile() {
    // With the artifact-id of the project
    buildMeta = BuildUtils.readBuild("build-utils");
    
    // Or by manually specifying the path to the properties file
    buildMeta = BuildUtils.readBuildFromPropertiesPath("/build-utils-meta.properties");

    return buildMeta;
}
````