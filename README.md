Maven
````xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
````

````xml
<dependency>
    <groupId>com.github.teraprath</groupId>
    <artifactId>MineTagAPI</artifactId>
    <version>{VERSION}</version>
</dependency>
````

Gradle
````groovy
repositories {
    maven { url 'https://jitpack.io' }
}
````
````
dependencies {
    implementation 'com.github.teraprath:MineTagAPI:{VERSION}'
}
````
