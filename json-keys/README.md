# JSON keys

Translate your json values into a key/value system.

**Add to your pom.xml:**
````xml
<repository>
    <id>alka-repo</id>
    <url>https://repo.alka.dev/repository/maven-public/</url>
</repository>

<dependency>
    <groupId>dev.alka.javautils</groupId>
    <artifactId>json-keys</artifactId>
    <version>VERSION</version>
</dependency>
````

Requires Google's Gson.

## Code exemple

````java
import dev.alka.utils.json.JsonKeys;

// Create class with existing JSON string or empty {}
JsonKeys jsonKeys = new JsonKeys("{}");

jsonKeys.createKey("welcome.message", "Hi!");
// JSON Result, with jsonKeys.getJson(): 
// {
//   "welcome": {
//     "message": "Hi!"
//   }
// }

jsonKeys.getKey("welcome.message");
// Result: Hi!

jsonKeys.updateKey("welcome.message", "Good morning!");
// JSON Result: 
// {
//   "welcome": {
//     "message": "Good morning!"
//   }
// }

jsonKeys.deleteKey("welcome.message");
// JSON Result: 
// {}
````

Example with a file:

````java
import dev.alka.utils.json.JsonKeys;

import java.nio.file.Files;

// Get a file content
String fileContent = Files.readString("data.json");
JsonKeys jsonKeys = new JsonKeys(fileContent);

// Do some stuff...
jsonKeys.createKey("this.is.a.new.key","Hi!");
jsonKeys.deleteKey("this.is.a.key");
jsonKeys.updateKey("this.is.an.existing.key","New value");

// Write file
Files.writeString("data.json", jsonKeys.getJson());
````