## Create Maven project

Kiểm tra phiên bản
```
$ mvn -version
Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)
Maven home: /Users/techmaster/.sdkman/candidates/maven/current
Java version: 16.0.2, vendor: Oracle Corporation, runtime: /Users/techmaster/.sdkman/candidates/java/16.0.2-open
Default locale: en_VN, platform encoding: UTF-8
OS name: "mac os x", version: "10.13.6", arch: "x86_64", family: "mac"
```

```
$ mvn archetype:generate -DgroupId=vn.techmaster.tinyrest -DartifactId=tinyrest -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

Bổ xung thư viện gson vào pom.xml
```xml
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.9</version>
</dependency>
```