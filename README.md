# android-redux

[![Build Status](https://cloud.drone.io/api/badges/v7lin/android-redux/status.svg)](https://cloud.drone.io/v7lin/android-redux)
[ ![Download](https://api.bintray.com/packages/v7lin/maven/redux/images/download.svg) ](https://bintray.com/v7lin/maven/redux/_latestVersion)
[ ![Download](https://api.bintray.com/packages/v7lin/maven/redux-android/images/download.svg) ](https://bintray.com/v7lin/maven/redux-android/_latestVersion)

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
