# Build utils
A collection of utility classes for Java builds.

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