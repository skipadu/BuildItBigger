# BuildItBigger

## Overview

* Starting point for this project was this Udacity's [FinalProject](https://github.com/udacity/ud867/tree/master/FinalProject)
* Shows joke when pressing a button
* Google Cloud Endpoints supplies jokes from the created Java library using AsyncTask
* Jokes are shown in the created Android library's activity
* Different build flavour for free (ads are shown) and paid version
* Showing loading indicator while waiting for response from the GCE

## Requirements
* Requires that Google Cloud Endpoints are [installed locally](https://cloud.google.com/sdk/docs/)

## Gradle tasks
Here is listed some of the most useful Gradle tasks to use:

* To start the GCE, run all tests and stop the GCE -> **runTestsWithGCEOn** 
* Install free version to your device -> **installFreeDebug** or **installFreeRelease**
* Install paid version to your device -> **installPaidDebug** or **installPaidRelease**

<img src="https://github.com/skipadu/BuildItBigger/raw/master/GCE-server-gradle-tasks.png?raw=true" width="200" alt="GCE server's Gradle tasks">

* To just start GCE -> **appengineStart**
* To just stop GCE ->  **appengineStop**

## Libraries
In addition to existing libraries that were in the base-project, following libraries were added:

* [Espresso](https://developer.android.com/training/testing/espresso/) to test UI
* [Timber](https://github.com/JakeWharton/timber) for logging

## Other
* Icons were created with [Sketch](https://www.sketchapp.com/) and using the [Icon Font](https://github.com/keremciu/sketch-iconfont) plugin on it. Icon is from material-bundle

* Looked example of [here](http://www.tutorialspoint.com/android/android_loading_spinner.htm) how to use ProgressBar

## Screenshots

### Free
Showing ad

<img src="https://github.com/skipadu/BuildItBigger/raw/master/screenshots/main_free.png?raw=true" width="200" alt="Main - Free"> <img src="https://github.com/skipadu/BuildItBigger/raw/master/screenshots/joke_free.png?raw=true" width="200" alt="Show joke - Free"> 

### Paid
Ad free

<img src="https://github.com/skipadu/BuildItBigger/raw/master/screenshots/main_paid.png?raw=true" width="200" alt="Main - Paid">