plugins {
	id("com.android.application")
	kotlin("android")
	kotlin("kapt")
	id("dagger.hilt.android.plugin")
	id("androidx.navigation.safeargs.kotlin")
}

android {
	compileSdk = 32

	defaultConfig {
		applicationId = "ir.sika.karpardaz"
		minSdk = 21
		targetSdk = 32
		versionCode = 1
		versionName = "0.1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		javaCompileOptions {
			annotationProcessorOptions {
				arguments += mapOf(
					"room.schemaLocation" to "$projectDir/schemas",
					"room.incremental" to "true"
				)
			}
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	packagingOptions {
		resources.merges.add("build-data.properties")
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
	buildFeatures {
		viewBinding = true
	}
}

kapt {
	correctErrorTypes = true
}

dependencies {

	implementation("androidx.core:core-ktx:1.7.0")
	implementation("androidx.appcompat:appcompat:1.4.1")
	implementation("com.google.android.material:material:1.5.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.3")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
	implementation("androidx.lifecycle:lifecycle-common-java8:2.4.1")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
	implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
	implementation("androidx.navigation:navigation-fragment-ktx:2.4.1")
	implementation("androidx.navigation:navigation-ui-ktx:2.4.1")
	implementation("androidx.fragment:fragment-ktx:1.4.1")

	implementation("com.google.dagger:hilt-android:2.38.1")
	implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
	kapt("com.google.dagger:hilt-android-compiler:2.38.1")

	implementation("androidx.room:room-runtime:2.4.2")
	annotationProcessor("androidx.room:room-compiler:2.4.2")
	kapt("androidx.room:room-compiler:2.4.2")
	implementation("androidx.room:room-ktx:2.4.2")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")

	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.3")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}