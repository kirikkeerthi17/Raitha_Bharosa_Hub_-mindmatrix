# 🌾 Raitha-Bharosa Hub
## Smart Sowing Assistance System for Farmers

Raitha-Bharosa Hub is an Android-based agricultural assistance application developed using modern Android technologies, Generative AI concepts, cloud integration, and data analytics. The application helps farmers make data-driven decisions related to sowing, fertilizer usage, and crop management using real-time weather information and soil nutrient analysis.

The project was developed as part of an internship program focused on Android Application Development using Generative AI and Data Analytics.

---

# 🔗 GitHub Repository

[Raitha-Bharosa Hub Repository](https://github.com/kirikkeerthi17/Raitha_Bharosa_Hub_-mindmatrix?utm_source=chatgpt.com)

---

# 📦 APK Download

APK available in GitHub Releases section:

[Download APK Release](https://github.com/kirikkeerthi17/Raitha_Bharosa_Hub_-mindmatrix/releases?utm_source=chatgpt.com)

---

# 📱 Application Overview

The application integrates:
- 🌦 Real-time Weather API
- 🌱 Soil Nutrient Analysis (NPK)
- 🤖 AI-based Agricultural Chatbot
- 📊 Dynamic Sowing Index
- ☁ Firebase Authentication
- 💾 Room Database
- 🗓 Krishi Calendar
- 🌐 Kannada & English Language Support

The goal of the application is to improve agricultural decision-making and support smart farming practices.

---

# 🚀 Features

## 🌦 Weather Monitoring
- Real-time weather data using OpenWeatherMap API
- Temperature monitoring
- Humidity tracking
- Rainfall probability analysis
- Dynamic weather-based recommendations

## 🌱 Soil Analysis
- Manual NPK value entry
- Soil nutrient evaluation
- Fertilizer recommendation system
- Soil history storage using Room Database

## 📊 Sowing Index
- Dynamic suitability score generation
- Weather + Soil combined analytics
- Color-based sowing recommendations
- Real-time dashboard updates

## 🤖 AI Agricultural Chatbot
- Agricultural guidance system
- Kannada & English support
- Crop-specific recommendations
- Rule-based AI responses

## 🗓 Krishi Calendar
- 7-day farming activity planner
- Weather-based agricultural alerts
- Irrigation & fertilizer planning

## ☁ Firebase Integration
- User Authentication
- Cloud Backup
- Firestore Integration
- User Session Management

---

# 🧰 Tech Stack

## Frontend
- Kotlin
- Jetpack Compose
- Material Design 3

## Architecture
- MVVM (Model-View-ViewModel)

## Backend & APIs
- Retrofit
- OpenWeatherMap API
- Firebase Authentication
- Firebase Firestore

## Database
- Room Database

## Tools & Platforms
- Android Studio
- Git & GitHub
- Logcat
- Gradle

---

# 📂 Project Structure

```plaintext
app/
│
├── ui/
│   ├── screens/
│   ├── components/
│   └── theme/
│
├── viewmodel/
│
├── repository/
│
├── data/
│   ├── local/
│   └── models/
│
├── network/
│
├── navigation/
│
├── utils/
│
└── MainActivity.kt
```

---

# 🧠 MVVM Architecture

The application follows the MVVM architecture pattern.

## Model
Handles:
- API responses
- Database entities
- Data classes

## ViewModel
Handles:
- UI state management
- Sowing Index calculation
- Weather processing
- Business logic

## View
Handles:
- Jetpack Compose UI
- User interaction
- State observation

---

# 📊 Sowing Index Logic

The Sowing Index is calculated using:
- Temperature
- Humidity
- Rainfall Probability
- Soil Nutrient Balance

### Example Logic
- Optimal temperature → Increase score
- High rainfall probability → Reduce score
- Balanced NPK → Positive recommendation

### Output Categories
- ✅ Good for Sowing
- ⚠ Moderate Conditions
- ❌ Not Suitable for Sowing

---

# ☁ Firebase Integration

Firebase services are used for:
- Authentication
- Cloud synchronization
- User profile backup
- Session persistence

---

# 💾 Room Database

Room Database is implemented for:
- Soil history storage
- User preferences
- Crop selection
- Offline functionality

---

# 📸 Application Modules

## Dashboard
Displays:
- Weather information
- Sowing Index
- Agricultural recommendations

## Soil Analysis
Allows:
- NPK entry
- Soil evaluation
- Fertilizer suggestions

## Chatbot
Provides:
- AI-based agricultural guidance
- Crop recommendations
- Kannada & English interaction

## Krishi Calendar
Displays:
- Weekly farming schedules
- Weather-based alerts

## Settings
Supports:
- Language switching
- Crop selection
- Profile management

---

# ⚙ Setup Instructions

## 1️⃣ Clone Repository

```bash
git clone https://github.com/kirikkeerthi17/Raitha_Bharosa_Hub_-mindmatrix.git
```

## 2️⃣ Open in Android Studio

- Open Android Studio
- Select "Open Existing Project"
- Choose cloned project folder

## 3️⃣ Sync Gradle

Allow Gradle sync to complete.

## 4️⃣ Add API Key

Add your OpenWeatherMap API key inside:

```plaintext
local.properties
```

Example:

```plaintext
WEATHER_API_KEY=YOUR_API_KEY
```

## 5️⃣ Run Application

Run using:
- Android Emulator
OR
- Physical Android Device

---

# 📸 Screenshots

- Dashboard Screen
- Soil Analysis Screen
- Krishi Calendar
- Chatbot Interface
- Settings Screen

---

# 🎯 Objectives

- Improve farming decision-making
- Reduce sowing uncertainty
- Promote precision farming
- Provide multilingual agricultural support
- Combine AI and analytics for agriculture

---

# 🔮 Future Enhancements

- IoT Sensor Integration
- Push Notifications
- Advanced AI Prediction Models
- Voice-Based Assistant
- Google Play Store Deployment
- Additional Language Support

---

# 🧪 Testing

Testing performed:
- Unit Testing
- Integration Testing
- API Testing
- Real Device Testing
- Performance Testing

---

# 📈 Performance

- Dashboard loading under 2 seconds
- Smooth navigation using Jetpack Compose
- Optimized API calls using Coroutines
- Stable Room Database operations

---

# 👨‍💻 Developed By

Keerthi Kumar

---

# 🏢 Internship Organization

MindMatrix.io (CL Infotech Pvt. Ltd.)

Domain:
Android App Development using Gen AI & Data Analytics

---

# 📜 License

This project is developed for educational and internship purposes only.

---

# 🌾 Smart Farming Through Technology
