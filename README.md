# Cattastic
 Table of Contents
1. Overview
2. Features
3. Technology Stack
4. Authentication
5. Shelter Map Feature
6. Screenshots
7. Setup and Installation
8. Automated Testing with GitHub Actions
9. Version Control with GitHub
10. Contributing
11. Github link
12. Demo Video
--------------------------------------------------------------------------------------------------------------
 Overview
Cattastic is a mobile application designed for cat lovers. It provides users with detailed information about various cat breeds, nearby cat shelter locations, and offers a secure, personalized user experience. The app is built using modern technologies for user authentication and geolocation services.

 Purpose
The purpose of Cattastic is to offer a fun and educational platform for cat enthusiasts to explore breeds, find shelters, and securely log in using the latest authentication methods.

--------------------------------------------------------------------------------------------------------------

 Features
- Cat Breed Explorer: Users can browse an extensive list of cat breeds and learn about their characteristics, origins, and care requirements.
- Shelter Map: View cat shelter locations on an interactive map using OSMDroid, an open-source map library that enables flexible, customizable map rendering.
- Google, Firebase, and Biometric Sign-In: Provides secure authentication using Google Sign-In, Firebase Authentication, and fingerprint-based biometric login.
- Theme Switching: Toggle between light and dark modes for a personalized user experience.
- Push Notifications: Receive updates about new features, cat-related news, or general notifications.
- Language Support: Users can switch between English and Afrikaans for a more localized experience.

--------------------------------------------------------------------------------------------------------------

 Technology Stack
- Kotlin: The primary language used to build the Android app.
- Firebase Authentication: Used for secure login via email/password and Google Sign-In.
- OSMDroid: Open-source mapping library used to render shelter locations on the map.
- TheCatAPI: External API that provides detailed cat breed information.
- Biometric API: Enables fingerprint-based authentication for enhanced security.

--------------------------------------------------------------------------------------------------------------

 Authentication
Cattastic utilizes secure and modern authentication methods:
1. Google Sign-In: Allows users to sign in with their Google account.
2. Firebase Authentication: Provides secure authentication via email and password.
3. Biometric Authentication: Offers users the ability to log in using their fingerprint, linked to their Firebase account.

 Authentication Flow:
- Users can sign in using their Google account or Firebase email and password.
- If enabled, biometric authentication allows users to log in with their fingerprint for quick access.
- Biometric login is linked to Firebase, ensuring credentials are securely stored and managed.

--------------------------------------------------------------------------------------------------------------

 Shelter Map Feature
The Shelter Map allows users to locate nearby cat shelters using OSMDroid, an open-source map solution that is flexible and customizable.

 Key Features:
- Interactive Maps: OSMDroid enables smooth, interactive map functionality with markers indicating nearby shelters.
--------------------------------------------------------------------------------------------------------------

 Screenshots
Include screenshots for the following features:
1. Login Screen: Displaying the options for Google Sign-In, email/password, and biometric login.

![cat login page](https://github.com/user-attachments/assets/fc1683d3-9e69-49eb-944c-2dd4b595a77e)

2. Cat Breed Explorer: Showing a list of cat breeds and their details.

![cat breeds page](https://github.com/user-attachments/assets/526ef1b8-33e7-4c24-94fa-5cf258c13279)

![cat breed detail page](https://github.com/user-attachments/assets/562ebd4f-27b4-487b-a192-6b182447e524)


3. Shelter Map: OSMDroid map with shelter locations marked.

![cat maps page](https://github.com/user-attachments/assets/60ebaa22-c1b2-46f7-ab06-0be23cb9b1b0)


4. Theme Switcher: Toggle between light and dark modes.

![cat light mode](https://github.com/user-attachments/assets/db6db2ec-4f98-4a8e-a287-b5e578bbe9d0)

![cat dark mode](https://github.com/user-attachments/assets/1275c1a4-5b63-443a-907e-4ce3d157a7cd)


5. Language Switcher: User selecting between English and Afrikaans.

![cat english](https://github.com/user-attachments/assets/b7d29830-d824-4561-acf1-33d2894bc91c)

![cat afrikaans](https://github.com/user-attachments/assets/b000ef03-a381-4942-9b04-41339f710221)

--------------------------------------------------------------------------------------------------------------

 Setup and Installation
Prerequisites:
Before running the app, make sure you have the following installed and set up:
1. Android Studio: Ensure the latest version of [Android Studio](https://developer.android.com/studio) is installed.
2. Firebase Project: You need to set up a [Firebase project](https://firebase.google.com/) and enable Firebase Authentication (email/password, Google Sign-In) to integrate the sign-in functionality.
3. OSMDroid Setup: The app uses OSMDroid for map features, so make sure all permissions and configurations are properly set.

 Steps to Get Started:

 1. Clone the Repository:
First, clone the GitHub repository to your local machine:
```
git clone https://github.com/your-username/cattastic.git
cd cattastic
```

 2. Set Up Firebase:

To properly run the app with Firebase Authentication and other Firebase services, follow these steps:
1. Create a Firebase Project: If you don't have one, go to [Firebase Console](https://console.firebase.google.com/) and create a new project.
2. Enable Authentication: Enable both Email/Password and Google Sign-In authentication methods in the Firebase console under Authentication -> Sign-in methods.
3. Download `google-services.json`:
   - Go to your Firebase Project in the Firebase Console.
   - Click on the Android icon to add an Android app to your project.
   - Follow the setup steps, and download the `google-services.json` file.
   - Place the `google-services.json` file in your `app` directory.

 3. Configure OSMDroid for Maps:
The app uses OSMDroid to display cat shelter locations. Ensure that OSMDroid has access to the required permissions:

1. Open your project’s `AndroidManifest.xml` file and ensure the following permissions are included:
   ```xml
   <uses-permission android:name="android.permission.INTERNET"/>
   <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
   ```

2. If necessary, set up the tile provider for OSMDroid, using the guide from [OSMDroid Wiki](https://github.com/osmdroid/osmdroid/wiki/How-to-use-the-osmdroid-library).

 4. Build and Run the Project:

Once the Firebase and OSMDroid configurations are set up:
1. Open the cloned project in Android Studio.
2. Sync the Gradle files to ensure all dependencies are properly downloaded.
3. Connect a physical device or set up an emulator.
4. Run the app by clicking the green play button in Android Studio.

--------------------------------------------------------------------------------------------------------------

 Automated Testing with GitHub Actions
Cattastic employs GitHub Actions for automated testing and building, ensuring that the code works across different environments, not just locally.

 GitHub Actions Overview:
- Automated Builds: Every time code is pushed to the main branch, a GitHub Action automatically triggers a build of the Android app.
- Automated Testing: Unit and integration tests run automatically to ensure key functionalities like authentication and map features work correctly.
- Continuous Integration: Ensures that each commit is tested and built, catching any potential issues early in the development process.

For more information on configuring GitHub Actions for Android, refer to the [GitHub Marketplace Actions for Android](https://github.com/marketplace/actions/automated-build-android-app-with-github-action).

---

 Version Control with GitHub
The project follows a version-controlled development process using GitHub:
1. Repository Setup: The Cattastic app was initialized with a `README.md` and properly configured for Firebase.
2. Branching Strategy: Feature branches are created for each new feature or bug fix, which are then merged into the main branch after thorough testing.
3. Commit Frequency: Commits are made regularly to document progress and ensure transparency in development.
4. Pull Requests: Code is reviewed through pull requests before being merged into the main branch to maintain code quality.

--------------------------------------------------------------------------------------------------------------

 Contributing
Here are the references in Harvard style:
1.	Android Developers. (2024). Security | Jetpack. Available at: https://developer.android.com/jetpack/androidx/releases/security [Accessed 25 Sep. 2024].
2.	YouTube. (2024). Android Push Notification Using Firebase Cloud Messaging in Kotlin | GeeksforGeeks. Available at: https://www.youtube.com/watch?v=2xoJi-ZHmNI [Accessed 26 Sep. 2024].
3.	YouTube. (2024). Add Multilingual support (Multiple Languages) to your Android App. Available at: https://www.youtube.com/watch?v=ObgmK3BywKI [Accessed 27 Sep. 2024].
4.	Android Developers. (2024). Fragments | Android Developers. Available at: https://developer.android.com/guide/fragments [Accessed 11 Sep. 2024].
5.	YouTube. (2024). Android Studio Navigation Drawer With Fragment and Activity | Custom Navigation Drawer. Available at: https://www.youtube.com/watch?v=5VsRFJjyMjU [Accessed 2 Sep. 2024].
6.	YouTube. (2024). Android Fragments Tutorial: An Introduction. Available at: https://www.youtube.com/watch?v=ceqcalf5_Is [Accessed 3 Sep. 2024].

--------------------------------------------------------------------------------------------------------------
Github Link
[https://github.com/VCWVL/opsc7312-poe-ST10028265]
--------------------------------------------------------------------------------------------------------------
Demo Video
[Video][https://youtu.be/8O_5SEpipYE?feature=shared]


