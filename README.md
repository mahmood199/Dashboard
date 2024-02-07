Summary

A simple dashboard app for OpenInApp assignment

*API :* 
1. [OpenInApp]([https://open-meteo.com/en/docs](https://api.inopenapp.com/api/v1/dashboardNew )) - To get the dashboard data


# Pre-requisite 📝

```properties
  Just clone the repo and run.
  Make sure to use latest Anroid studio and Android Gradle Version > 8.0.0
```

*Environment*
- Built on A.S Iguana
- Gradle Version > 8
- Kotlin version >= 1.9.0


# Technologies 🔨

**Language :** [Kotlin](https://github.com/JetBrains/kotlin)

**Libraries :**
  *UI*
- [Compose](https://developer.android.com/jetpack/compose)
- [Coil](https://coil-kt.github.io/coil/compose/https://coil-kt.github.io/coil/compose/) 
- [Landscapist](https://github.com/skydoves/landscapist) 

  *Data*
- [Ktor](https://github.com/ktorio/ktor.git)
- [Gson](https://github.com/google/gson)
- [Preference Data Store](https://developer.android.com/topic/libraries/architecture/datastore)

   *Tooling/Project setup*
- [Gradle secrets plugin](https://github.com/google/secrets-gradle-plugin)
- [Hilt(DI)](https://developer.android.com/training/dependency-injection/hilt-android)


# Design/Architectural decisions 📐

The project follows common android patterns in modern android codebases. 
P.S - I've done layer wise modularization but feature modularization of this can result in better codebase structure.

**Project Structure**

The folders are split into 6 modules:
 - **Connection**:
   Contains the classes that are required for keeping an eye on mobile network connectivity. This in itself can be a separate module for identifying and observing device connectivity events.

 - **Core-Local**:
   Contains the classes that are required for platform specific code. Like setting up user Preferences. Basically this is the inner most layer of data.

 - **Core-Network**:
   Contains the classes that are required for platform specific code. Like setting up network client. Basically this is the inner most layer of data.

 - **Data**:
   This package contains models, data sources, both local or remote and repositories as well. All data related actions and formatting happens in this layer as well.
   It also contains framework related dependencies to co-ordinate and create instances of data stores like a database or shared preference etc.
   The repository pattern, is used, which mediates data sources and acts as a source of truth to the consumer.

 - **Domain**:
   For modification of data as per UI. These modification dont reflect on data layer. This is fexible in nature
  
 - **App**:

   This is the presentation layer of the app.
   This is further divided into 3 parts.
   1. common - UI that is common and can be used across app with minor changes. Eg:- Toolbar
   2. feature - each screen is considered to be a feature. Each feature has a UI, viewModel and a viewState which is observed my the UI.
   3. theme - Base theming of the application

   Each feature has a UI, viewmodel and a viewState which is observed my the UI. The UI just observes the state and reacts to the changes. It doesn't directly change the state. It request viewmodel to changes the state and react accordingly. Thus maitaining single source of truth. The state observed by UI is a stateFlow of an immutable type. Stateflow ensures that out app has the latest updated data.
   
   Some design patterns that can be seen here are the Observer pattern when consuming the flow -> state flows in the composables and provides a reactive app.

 - **Util**:
   Contains the extension functions for routine task like date formatting and SDK checks.


# Screenshots 📱

|                         Home(Dashboard)                       |
|:---------------------------------------------------------:|
| ![WhatsApp Image 2024-02-07 at 13 08 15_f381925e](https://github.com/mahmood199/Dashboard/assets/58071934/69ad2165-9731-4a9e-b4a8-d8ea2ba4b377) |



|                         Home(Dashboard)                       |
|:---------------------------------------------------------:|
| ![WhatsApp Image 2024-02-07 at 13 08 16_06c01c8a](https://github.com/mahmood199/Dashboard/assets/58071934/4e08a06e-6921-4877-b644-60b4eeeb0101) |



# Improvements 🚀
As for every other project there is always some room for improvement.
Additio of these were not possible due to time constraints.
Things that are worth adding
1. Feature wise Modularization.
2. Testing.
3. Adding UseCase as an intermediate between viewModel and repository.
4. Add corresponding UI model class for each remote model class
5. Add Mappers for local and remote model classes.

# LICENSE

```
   Copyright 2024 Mahmood Ahmad

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
```

