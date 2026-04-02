# OmniShell AI

![Build](https://github.com/abdulraheemnohri/OmniShell-AI/actions/workflows/android-ci.yml/badge.svg)
![License](https://img.shields.io/github/license/abdulraheemnohri/OmniShell-AI)
![Release](https://img.shields.io/github/v/release/abdulraheemnohri/OmniShell-AI)
![Platform](https://img.shields.io/badge/platform-Android-brightgreen)

OmniShell AI is an all-in-one Android application that unifies the capabilities of **Termux**, **OpenClaw**, and **Ollama**. Built natively in Kotlin, it leverages **Chaquopy** to execute Python scripts for advanced AI tasks and shell command automation.

## 🚀 Key Features

### 💻 Termux Module
- **Shell Command Execution**: Run shell commands directly via Python subprocess.
- **Environment Automation**: Automate package installation, updates, and setup.
- **Full Output Logging**: Track all execution logs with real-time feedback.

### 🤖 OpenClaw Module
- **AI Automation**: Integrated assistant scripts for complex task automation.
- **Plugin System**: Modular architecture to extend capabilities easily.
- **Workflow Context**: Future-ready for AI-powered suggestions and contextual automation.

### 🧠 Ollama Module
- **Local AI Inference**: Interface with local AI models.
- **Python Wrapper**: Multi-platform model execution support.
- **Centralized Logging**: Detailed logs for model inputs, outputs, and errors.

### 📊 Unified Dashboard
- **Modern UI**: Clean, Material Design interface for seamless module interaction.
- **Persistent Logging**: SQLite (Room) based logging system that keeps history across app restarts.
- **Colored Status Display**: Quick visual feedback with ✅ Success, ❌ Error, and ⚠️ Warning indicators.
- **Dark/Light Mode**: Full support for system-wide appearance settings.

## 📡 OTA Updates
OmniShell AI features an integrated Over-The-Air (OTA) update system. It checks for new releases and provides easy installation to keep you up to date with the latest features and security patches.

## 🛠 Tech Stack

- **Android Native**: Kotlin / Java (Min SDK 30)
- **Python Integration**: Chaquopy 16.0.0 (Python 3.10)
- **Database**: Room Persistence Library (SQLite)
- **DevOps**: "Pro Max" Pipeline with AI Code Review, CodeQL, and Auto-Releases.

## 📂 Project Structure

```text
OmniShellAI/
├── app/
│   ├── src/main/java/com/omnishellai/  # Kotlin Controllers & UI
│   │   ├── data/                      # Room Database & Repository
│   │   └── DashboardActivity.kt
│   ├── src/main/python/                # Python Modules
│   ├── build.gradle.kts                # Android Build Config
│   └── python.gradle                   # Chaquopy Specific Config
└── .github/workflows/                  # CI/CD Workflows
```

## 🏗 Build & Test

### Local Build
1. Clone the repository.
2. Open in Android Studio.
3. Ensure JDK 21 is configured.
4. Build using `./gradlew assembleDebug`.

### CI/CD Workflows
- **Android CI/CD**: Automatically runs tests and builds APK + AAB on every push.
- **AI Code Review**: Automated feedback on pull requests.
- **Release APK**: Automated GitHub Releases on tag pushes.
- **Security**: Continuous CodeQL and Dependency scanning.

---
*Created with ❤️ for the OmniShell AI community.*
