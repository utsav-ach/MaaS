# Mobile-as-a-Server (MaaS): Day 1 Development Record

## Project Overview
- **Project**: Mobile-as-a-Server (MaaS) — An Edge Web Hosting Platform
- **Academic Context**: TU BCA 6th Semester — Project II
- **Development Chunk**: Day 1 of ~40–50 Chunks

---

## Day 1 Objective
Establish the foundational Android project structure and create the first working management application shell for MaaS using Java, XML layouts, Android Studio, and Gradle. The shell establishes the visual and architectural identity of an edge server-management console.

---

## What Was Implemented
1. **Application Shell & Identity**:
   - Application named `MaaS` (`Mobile-as-a-Server`) configured with custom branding, vector assets, and dark server-console styling.
   - Dedicated technical dashboard presenting server status, system metrics, services, network, and ALTO orchestrator sections.

2. **Modular Architecture & Navigation**:
   - `MainActivity` equipped with `DrawerLayout`, `MaterialToolbar`, and `NavigationView`.
   - Wired navigation across all 9 planned MaaS core modules:
     1. Dashboard (`DashboardFragment`)
     2. Server Environment (`ServerFragment`)
     3. Applications (`ApplicationsFragment`)
     4. Services Daemon (`ServicesFragment`)
     5. Network & Tunnel (`NetworkFragment`)
     6. Live Telemetry (`MonitoringFragment`)
     7. ALTO Engine (`AltoFragment`)
     8. Server Logs (`LogsFragment`)
     9. Configuration (`SettingsFragment`)
   - `BasePlaceholderFragment` providing informational architectural placeholders for future modules.
   - Quick-access chip launchpad directly on the dashboard.

3. **Data State Models (Separation of Concerns)**:
   - `ServerStatusState`: Holds server online state, status headline, descriptions, and runtime targets.
   - `SystemMetricsState`: Holds CPU, RAM, temperature, battery placeholders, and status labels.
   - `ServicesState`: Holds daemon counters and service running flags.
   - `NetworkState`: Holds IP address, Cloudflare Tunnel URL, and connectivity state.
   - `AltoState`: Holds ALTO status, policy strings, and optimization states.

4. **Resource Organization**:
   - Complete palette in `colors.xml` tailored for technical server monitoring (dark background, status badges).
   - Clean strings in `strings.xml` without hardcoded text in layouts.
   - Custom dimensions in `dimens.xml` and styles in `themes.xml`.
   - Vector drawables for hardware metrics, server stacks, status dots, and navigation icons.

---

## Project Structure
```
MaaS/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/maas/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── AltoState.java
│   │   │   │   │   ├── NetworkState.java
│   │   │   │   │   ├── ServerStatusState.java
│   │   │   │   │   ├── ServicesState.java
│   │   │   │   │   └── SystemMetricsState.java
│   │   │   │   └── ui/
│   │   │   │       ├── alto/AltoFragment.java
│   │   │   │       ├── apps/ApplicationsFragment.java
│   │   │   │       ├── dashboard/DashboardFragment.java
│   │   │   │       ├── logs/LogsFragment.java
│   │   │   │       ├── monitoring/MonitoringFragment.java
│   │   │   │       ├── network/NetworkFragment.java
│   │   │   │       ├── placeholder/BasePlaceholderFragment.java
│   │   │   │       ├── server/ServerFragment.java
│   │   │   │       ├── services/ServicesFragment.java
│   │   │   │       └── settings/SettingsFragment.java
│   │   │   ├── res/
│   │   │   │   ├── drawable/          (vectors, badges, icons)
│   │   │   │   ├── layout/            (activity_main, fragment_dashboard, etc.)
│   │   │   │   ├── menu/              (drawer_menu, top_app_bar_menu)
│   │   │   │   └── values/            (colors, strings, themes, dimens)
│   │   │   └── AndroidManifest.xml
│   │   └── test/java/com/example/maas/
│   │       └── ModelBaselineTest.java
│   ├── build.gradle
│   └── proguard-rules.pro
├── documentation/
│   └── DAY-01.md
├── build.gradle
├── settings.gradle
├── gradle.properties
├── local.properties
└── .gitignore
```

---

## Technologies Used
- **Language**: Java 17 / 21
- **UI Framework**: Android XML Layouts + Material Components 1.11.0
- **Build System**: Gradle 8.10.2 + Android Gradle Plugin 8.7.2
- **Testing**: JUnit 4.13.2
- **Target Platform**: Android SDK 34 (Android 14), Min SDK 26 (Android 8.0+)

---

## Current Limitations & Static Baseline
- Server status is statically initialized to "Offline / Not Connected".
- System telemetry metrics (CPU, RAM, Core Temp, Battery) display placeholder baseline states (`— %`, `— / — GB`, `— °C`).
- Network details display placeholder baseline states ("Unavailable", "Not Configured").
- ALTO state displays "Not Active".
- Navigation to secondary modules renders structured placeholder views describing future module capabilities.

---

## What Is Intentionally NOT Implemented Yet
Per Day 1 scope, the following backend/server components are deferred to subsequent development chunks:
- Ubuntu rootfs / PRoot installation
- Termux API / bridge integration
- Cloudflare Tunnel (`cloudflared`) configuration
- Nginx web server & virtual hosts
- MariaDB / MySQL database installation
- Live hardware telemetry collection (procfs, BatteryManager, thermal zones)
- ALTO load-shedding and throttling algorithms
- Production authentication and security hardening

---

## Next Development Target (Day 2)
Establish the underlying execution runtime foundation and explore/prepare the Termux / PRoot Linux container environment that will host Nginx and web services beneath the MaaS management app.
