# android-redux

[![Build Status](https://cloud.drone.io/api/badges/v7lin/android-redux/status.svg)](https://cloud.drone.io/v7lin/android-redux)
[![GitHub tag](https://img.shields.io/github/tag/v7lin/android-redux.svg)](https://github.com/v7lin/android-redux/releases)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

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
