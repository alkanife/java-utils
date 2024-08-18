# CLI utils
A collection of utility classes for my CLI projects.

## Pretty usage
This allows you to generate a vertical command usage, with a any `Map<String, String>`

**Code**
````java
import dev.alka.utils.cli.PrettyUsage;

import java.util.HashMap;

private void printUsage() {
    PrettyUsage prettyUsage = new PrettyUsage();
    prettyUsage.setPaddingLeft(0); // Default value
    prettyUsage.setPaddingRight(3); // Default value
    
    // By creating a map
    HashMap<String, String> commands = new HashMap<>();
    commands.put("-help, -h", "Get help");
    commands.put("-version, -v", "Get version");
    prettyUsage.importValues(commands);
    
    // Importing manually
    prettyUsage.importValue("-start", "Start server");
    
    // Print
    System.out.println("Program usage: ");
    System.out.println("============== ");
    for (String command : prettyUsage.getLines()) {
        System.out.println(command);
    }

}
````

**Result**
````
Program usage:
============== 
-help, -h      Get help
-version, -v   Get version
-start         Start server
````