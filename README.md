# commons
Well-known bike

### Usage with Maven
#### pom.xml
```xml
    <repositories>
        <repository>
            <id>commons-mvn-repo</id>
            <url>https://raw.github.com/pavelkalinin/commons/mvn-repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
    
    <dependencies>
        <dependency>
            <groupId>xyz.enhorse</groupId>
            <artifactId>commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
        
