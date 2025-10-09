# Nativeblocks E-commerce Android Demo

A comprehensive Android e-commerce demo application showcasing Nativeblocks SDK integration
patterns, server-driven UI, and deep link campaign handling.

## 🎯 Features Demonstrated

- **SDK Initialization & Configuration** - Cloud-based setup with API key authentication
- **Server-Driven UI** - Dynamic UI components loaded from Nativeblocks cloud
- **Campaign Management** - Deep link driven campaigns with dynamic parameters
- **Landing Pages** - Remote landing page loading and display
- **Global Parameters** - Campaign and user context parameter management
- **Event Logging** - Custom logging implementation for analytics
- **Lifecycle Management** - Proper SDK initialization and cleanup

## 🏗️ Architecture & Flow

### 1. SDK Initialization (MainActivity.kt)

The app initializes Nativeblocks in `MainActivity.onCreate()` before any UI setup. It configures the
SDK with cloud edition, API endpoint, and development mode settings. Foundation UI components are
registered, and a custom event logger is provided for analytics tracking.

**Key Concepts:**

- Initialize early in app lifecycle
- Cloud edition for remote configuration
- Foundation components provide UI building blocks
- Custom logger captures all SDK events

### 2. Foundation Provider (MainActivity.kt)

Registers Nativeblocks Foundation UI components that can be used in server-driven layouts. This
enables the SDK to render standard UI elements like buttons, text fields, and containers from server
definitions.

### 3. Custom Event Logger

Implements `INativeLogger` interface to capture SDK events in structured JSON format. Logs include
event level, name, message, and parameters. This integration point allows forwarding events to
analytics platforms like Firebase, Mixpanel, or custom solutions.

### 4. Parameter Management (NativeblocksRoutes.kt)

Centralized parameter management system that handles campaign tracking and user segmentation.
Parameters are organized into two categories:

**Campaign Parameters:** Include campaign ID, source (organic/deeplink), platform identifier, and
session timestamp.

**User Segmentation Parameters:** Track user type, location, purchase history, app usage frequency,
and session type for server-side targeting.

### 5. Global Parameter Setting (HomeScreen.kt)

When the home screen launches, it sets global parameters using `LaunchedEffect`. Campaign and user
context parameters are combined and sent to the SDK. These parameters enable server-side targeting
and personalization. If a campaign ID exists, the offer dialog is triggered.

### 6. Server-Driven UI (NativeblocksOfferDialog.kt)

Demonstrates loading and rendering server-defined UI content. The component manages loading,
success, and error states. It calls `getLanding()` to fetch a landing page definition from the
server, then uses `NativeblocksFrame` to render the content. Error handling ensures graceful
degradation if server content fails to load.

**Flow:**

1. Set loading state
2. Request landing page from server
3. Handle success/failure responses
4. Render content or show fallback

### 7. Deep Link Handling (MainActivity.kt)

Processes incoming deep links to extract campaign information. Supports both custom URL schemes (
`ecommerce://`) and universal links (`https://`). Extracts campaign ID from query parameters and
navigates to the appropriate screen with campaign context.

**Supported Formats:**

- `ecommerce://campaign?id=flash_sale`
- `https://nativeblocks.ecommerce.app/campaign?id=flash_sale`

### 8. Lifecycle Management (MainActivity.kt:54-57)

Properly cleans up SDK resources in `onDestroy()` by calling `NativeblocksManager.destroy()`. This
prevents memory leaks and ensures proper resource management.

## 🔗 Deep Link Testing

Test campaign deep links using ADB:

### Flash Sale Campaign

```bash
adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://campaign?id=flash_sale" io.nativeblocks.nativeblocks.ecommerce
```

### Weekend Special

```bash
adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://campaign?id=weekend" io.nativeblocks.nativeblocks.ecommerce
```

### Clearance Sale

```bash
adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://campaign?id=clearance" io.nativeblocks.nativeblocks.ecommerce
```

### New User Offer

```bash
adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://campaign?id=new_user" io.nativeblocks.nativeblocks.ecommerce
```

## 🏃‍♂️ Setup Instructions

### Prerequisites

- Android Studio Iguana or later
- Android SDK 26+ (minimum) / 36 (target)
- Nativeblocks API key

### Configuration Steps

**Get and update Nativeblocks API Key**
    - Sign up at [Nativeblocks Dashboard](https://dashboard.nativeblocks.io)
    - Create a new project
    - Copy your API key
   ```kotlin
   apiKey = "your-actual-api-key-here"
   ```

## 📊 Parameter Schema

### Campaign Parameters

- `campaignId` - Unique campaign identifier
- `source` - "organic" or "deeplink"
- `platform` - "android"
- `sessionTimestamp` - Session start time

### User Segmentation Parameters

- `userType` - "organic" or "deeplink"
- `userId` - Optional user identifier
- `location` - Optional user location
- `previousPurchases` - Purchase history count
- `appOpenCount` - App usage frequency
- `sessionType` - "new" or "returning"

## 🎨 Server-Driven UI Flow

The app uses `getLanding()` to fetch landing page definitions from the server and
`NativeblocksFrame` to render the content. Components handle loading, success, and error states with
appropriate UI feedback.

## 🔧 Best Practices Demonstrated

1. **Centralized Parameter Management** - Single source of truth for campaign parameters
2. **Error Handling** - Graceful degradation when server content fails
3. **Loading States** - Proper UI feedback during content loading
4. **Memory Management** - Proper SDK cleanup in lifecycle callbacks
5. **Deep Link Routing** - Seamless campaign navigation from external sources
6. **User Segmentation** - Dynamic user targeting based on behavior

## 📖 Docs

- [Nativeblocks Documentation](https://nativeblocks.io/docs/get-started/introduction/)
- [Android SDK Reference](https://nativeblocks.io/references/android/core/intro/)

---

This demo serves as a production-ready reference for integrating Nativeblocks SDK with advanced
campaign management, server-driven UI, and comprehensive parameter tracking.