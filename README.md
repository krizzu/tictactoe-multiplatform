# TicTacToe

Multi-platform Tic-tac-toe game, available for Web, Android and iOS. 


## About

This multi-platform project consist of several platform sub-projects:

- Android (`androidApp/`)
- iOS (`iosApp/`)
- JS (`shared/src/jsMain`)

Platform shared code is located in `shared/src/commonMain`.


## Development


### Android

Use Android Studio (3.5.3 or later) and open `androidApp` module. 

### iOS

In `iosApp/` directory, install pods with `pod install`.
Use Xcode and open `iosApp/TicTacToe.xcworkspace`.

#### Useful gradle tasks

- *podspec*

Generates `shared.podspec` for use in Cocoapods project
 

### JS

Here's the list of gradle tasks to get you up and running. Use gradle wrapper to run:

`./gradlew TASK_NAME`

To get bundling and web page refreshing in sync, you have to run two tasks in parallel:

- `jsBrowserRun`
- `compileKotlinJs -t`

This will start a browser at `localhost:8080`, with page refresh on save.


#### Useful gradle tasks

- *jsBrowserWebpack* 

Build webpack bundle, placing it in `docs/` directory.

- *jsBrowserRun*

Run webpack dev server.

- *compileKotlinJs*

Compiles Kotlin code in JS code. No need to use it if not actively developing.

- *jsTest*

Run All JavaScript tests.
 
 
 