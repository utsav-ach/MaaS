# Mobile-as-a-Server (MaaS): Day 2 Development Record

## Project Overview
- **Project**: Mobile-as-a-Server (MaaS) — An Edge Web Hosting Platform
- **Academic Context**: TU BCA 6th Semester — Project II
- **Development Chunk**: Day 2 of ~40–50 Chunks

---

## 1. Day 2 Objective
Convert the initial MaaS Android dashboard shell from Day 1 into a proper modular Android application architecture. The Android application will serve as the technical management and control layer for the underlying edge Linux server environment. Day 2 establishes the modular project layout, navigation patterns, clean data models, error/state handling, and the server-control abstraction layer—decoupling the UI from future backend execution without implementing the real server engine yet.

---

## 2. Existing Day 1 Architecture
In Day 1, the baseline application shell was introduced with:
- A single `MainActivity` containing a `DrawerLayout`, `NavigationView`, and `MaterialToolbar`.
- A monolithic initial `DashboardFragment` presenting static card sections for Server, Telemetry, Services, Network, and ALTO.
- Direct instantiation of initial state objects within the fragment without an abstraction layer.
- Simple placeholder fragments (`BasePlaceholderFragment`) linked via navigation drawer and chip launchpad.
- Initial model baseline (`ServerStatusState`, `SystemMetricsState`, `ServicesState`, `NetworkState`, `AltoState`).

---

## 3. New Android Architecture
Day 2 establishes a 3-tier architectural separation:
```
┌─────────────────────────────────────────────────────────────┐
│                       UI Layer                              │
│   MainActivity, Navigation Drawer, 9 Module Fragments      │
│   (Dashboard, Server, Applications, Services, Network,      │
│    Monitoring, ALTO, Logs, Settings)                        │
└──────────────────────────────┬──────────────────────────────┘
                               │ Calls methods / queries data
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                Application / Service Layer                  │
│   ServerControlService (Interface)                          │
│   ServerControlManager (Singleton Implementation Bridge)    │
│   Constants & Utilities                                     │
└──────────────────────────────┬──────────────────────────────┘
                               │ Produces & Manages
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                     Data / Model Layer                      │
│   ServerStatus, ApplicationInfo, ServiceInfo, NetworkInfo,  │
│   MonitoringData, AltoStatus, ServerConnectionState (Enum)  │
└─────────────────────────────────────────────────────────────┘
```

The UI layer no longer creates hardcoded data or directly executes mock operations. Instead, it queries the centralized `ServerControlService` abstraction, preserving maintainability for future container and daemon integration.

---

## 4. Module List
The MaaS Android management console encompasses 9 foundational modules:

1. **Dashboard** (`ui.dashboard.DashboardFragment`): Centralized command view showing edge node health, server connectivity, active daemons, network routing, and quick module navigation.
2. **Server** (`ui.server.ServerFragment`): Linux container environment manager (PRoot/Termux Ubuntu 22.04 LTS), container lifecycle, rootfs setup, and process control.
3. **Applications** (`ui.applications.ApplicationsFragment`): Hosted web applications manager (static websites, PHP/Node.js stacks, virtual hosts).
4. **Services** (`ui.services.ServicesFragment`): Background daemon supervisor (Nginx reverse proxy, MariaDB database, Cloudflare worker).
5. **Network** (`ui.network.NetworkFragment`): Ingress routing and Cloudflare Zero Trust Tunnel connector without public IP requirements.
6. **Monitoring** (`ui.monitoring.MonitoringFragment`): Live hardware telemetry collector (CPU load, RAM consumption, battery thermal zones).
7. **ALTO** (`ui.alto.AltoFragment`): Adaptive Load Throttling and Optimization engine preventing mobile host failure via thermal-aware traffic management.
8. **Logs** (`ui.logs.LogsFragment`): Streaming system journal and web server access/error log viewer.
9. **Settings** (`ui.settings.SettingsFragment`): Global system preferences, listener ports, storage paths, and background execution policies.

---

## 5. Navigation Approach
A **Navigation Drawer (`DrawerLayout` + `NavigationView`)** combined with a top `MaterialToolbar` hamburger toggle and quick-access launchpad chips is implemented.
- **Drawer Navigation**: Categorized into *Core Management*, *Operations & Optimization*, and *System*.
- **Quick-Access Chips**: Placed directly on the dashboard for direct one-tap jumps to any module.
- **Up / Back Navigation**: Placeholder screens include a dedicated *Return to Dashboard* action, and Android hardware/gesture back press returns gracefully to the Dashboard before exiting.
- **Selection Synchronization**: Drawer active item states stay synchronized when navigating via launchpad chips or programmatic transitions.

---

## 6. Data Models Created
Sensible, extensible Java data models were created to serve as future contracts:

| Model Class | Key Fields | Purpose |
|---|---|---|
| `ServerStatus` | `status (ServerConnectionState)`, `uptime`, `address`, `description` | Runtime state and address of edge server |
| `ApplicationInfo` | `name`, `status`, `port` | Metadata for hosted web applications |
| `ServiceInfo` | `name`, `status`, `port` | Lifecycle details for server daemons |
| `NetworkInfo` | `localAddress`, `publicAddress`, `tunnelStatus` | Edge host routing and ingress states |
| `MonitoringData` | `cpuUsage`, `memoryUsage`, `temperature`, `batteryLevel`, `isMonitored` | Device hardware metrics and thermal sensors |
| `AltoStatus` | `enabled`, `state`, `loadScore`, `policy` | ALTO optimization engine state |
| `ServerConnectionState` (Enum) | `CONNECTED`, `DISCONNECTED`, `CONNECTING`, `ERROR`, `NOT_CONFIGURED` | Communication status between Android UI & server |

---

## 7. Server-Control Abstraction
The `com.example.maas.service.ServerControlService` interface abstracts all upcoming Linux environment interactions:

```java
public interface ServerControlService {
    ServerStatus getServerStatus();
    boolean startService(String serviceName);
    boolean stopService(String serviceName);
    boolean restartService(String serviceName);
    List<ApplicationInfo> getApplications();
    List<ServiceInfo> getServices();
    NetworkInfo getNetworkStatus();
    MonitoringData getMonitoringData();
    AltoStatus getAltoStatus();
    ServerConnectionState getConnectionState();
}
```

The concrete implementation `ServerControlManager` serves as the centralized singleton. In Day 2, it delivers honest, baseline statuses (`NOT_CONFIGURED`, `Not Connected`, `Not Active`, `Not Configured`) and returns safe uninitialized responses for service execution requests.

---

## 8. Current Limitations
- **No live server process**: No Termux or PRoot container process is running in the background yet.
- **Static baseline reporting**: Metrics and network statuses report unconfigured / offline / unmonitored baseline states honestly.
- **Service control operations**: `startService()`, `stopService()`, and `restartService()` return `false` as expected for the current architectural phase.
- **Module screens**: Modules 2–9 display clean architectural placeholders indicating purpose, status, and target runtime.

---

## 9. What Is Intentionally NOT Implemented Yet
As strictly bounded by Day 2 scope, the following backend/engine components are intentionally deferred:
- Termux / PRoot Linux container installation
- Ubuntu rootfs filesystem extraction
- Shell command execution (`Runtime.exec`, ProcessBuilder, or socket communication)
- Nginx web server installation or configuration
- MariaDB / MySQL database daemon
- Cloudflare Tunnel daemon (`cloudflared`) binary execution
- Real hardware telemetry collection (/proc/stat, BatteryManager, thermal zones)
- ALTO heuristic algorithms and traffic-shedding logic
- SQLite / Room database storage
- Remote authentication or deployment engines

---

## 10. Day 2 Completion Status
All Day 2 requirements have been verified and completed:
- [x] Existing Day 1 app functionality fully preserved.
- [x] Clean modular Android architecture established.
- [x] All 9 core MaaS modules architected and navigable.
- [x] Navigation Drawer and chip navigation functional without errors.
- [x] Foundational Java data models (`ServerStatus`, `ApplicationInfo`, `ServiceInfo`, `NetworkInfo`, `MonitoringData`, `AltoStatus`) created.
- [x] State/error handling enum (`ServerConnectionState`) created.
- [x] Server-control abstraction (`ServerControlService` & `ServerControlManager`) implemented.
- [x] UI decoupled from data models through the service layer.
- [x] Honest placeholder states ("Not Connected", "Not Active", "Not Configured") displayed.
- [x] Unit test suites (`ModelBaselineTest`, `Day2ArchitectureTest`) passing (100% pass rate).
- [x] Application successfully compiles to debug APK via Gradle.
- [x] `documentation/DAY-02.md` generated.
