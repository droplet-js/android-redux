# redux && redux-android

### snapshot

````
ext {
    latestVersion = '1.0.0-SNAPSHOT'
}

allprojects {
    repositories {
        ...
        maven {
            url 'https://oss.jfrog.org/artifactory/oss-snapshot-local'
        }
        ...
    }
}
````

### release

````
ext {
    latestVersion = '1.0.0'
}

allprojects {
    repositories {
        ...
        jcenter()
        ...
    }
}
````

### usage

java
````
...
dependencies {
    ...
    implementation "io.github.v7lin:redux:${latestVersion}"
    ...
}
...
````

android
````
...
dependencies {
    ...
    implementation "io.github.v7lin:redux-android:${latestVersion}"
    ...
}
...
````

### example

[android example](./app/src/main/java/io/github/v7lin/redux/MainActivity.java)