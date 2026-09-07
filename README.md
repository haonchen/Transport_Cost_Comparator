# Transport Cost Comparator

An Android app for calculating daily and monthly transport expenses, then providing a cost category and practical recommendation.

## Features

- Calculates daily transport cost from distance travelled and cost per kilometre.
- Calculates monthly transport cost and total monthly distance.
- Supports Private Vehicle, Taxi, Bus, Train, and Motorcycle transport types.
- Categorises monthly cost from **Low Cost** to **Excessive** and suggests ways to reduce spending.
- Validates required inputs before saving a calculation.
- Stores completed calculations locally with SQLite so the result can be retrieved after navigation.

## How it works

1. Enter a transport mode, select a transport type, and provide daily distance, cost per kilometre, and travel days.
2. The app calculates:
   - Daily cost = distance per day × cost per kilometre
   - Monthly cost = daily cost × travel days
   - Monthly distance = distance per day × travel days
3. View the calculated totals, cost category, and recommendation.

All currency values are displayed in South African rand (`R`).

## Requirements

- Android Studio (latest stable version recommended)
- JDK 11
- Android SDK with API 37 installed
- Android device or emulator running Android 7.0 (API 24) or later

## Run locally

1. Clone the repository.
2. Open the project in Android Studio.
3. Allow Gradle sync to complete.
4. Choose an emulator or connected device and run the `app` configuration.

Alternatively, build from the command line on Windows:

```powershell
.\gradlew.bat assembleDebug
```

The debug APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

## Tech stack

- Java
- Android SDK and AndroidX
- Material Components
- ConstraintLayout
- SQLite
- Gradle with Kotlin DSL

## Project structure

```text
app/src/main/java/com/example/transportcostcomparator/
├── MainActivity.java          # Home screen
├── InputActivity.java         # Collects and validates transport details
├── ResultsActivity.java       # Shows calculations and recommendations
├── TransportCalculator.java   # Cost and distance calculations
├── Recommendation.java        # Cost categories and recommendations
├── DatabaseHelper.java        # SQLite persistence
└── Transport.java             # Calculation data model
```

## Tests

Run unit tests with:

```powershell
.\gradlew.bat test
```

Run instrumented tests on an emulator or connected device with:

```powershell
.\gradlew.bat connectedAndroidTest
```
