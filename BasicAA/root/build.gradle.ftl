apply1 plugin: 'com.neenbedankt.android-apt'

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
          classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
}

android {
    buildTypes {
   	 debug {
            applicationIdSuffix ".test"
            minifyEnabled true
            signingConfig signingConfigs.debug
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

apt {
    arguments {
        androidManifestFile variant.outputs[0]?.processResources?.manifestFile
        trace 'true'
        resourcePackageName "${packageName}"
        logLevel 'ERROR'
        logAppenderConsole 'true'
        classSuffix '_'
        //classSuffix is used to change the suffix of enhanced classes like Activities with @EActivity. The default value is _.
        generateFinalClasses 'true'
        //generateFinalClasses is used to set if generated classes should be final or not. The default value is true.
    }
}

dependencies {
	compile "org.androidannotations:androidannotations-api:4.1.0"
    apt "org.androidannotations:androidannotations:4.1.0"
    apt "org.androidannotations:otto:4.1.0"
    compile "org.androidannotations:rest-spring-api:4.1.0"
    apt "org.androidannotations:rest-spring:4.1.0"
    compile "com.android.support:appcompat-v7:24.2.1"
    compile "com.android.support:cardview-v7:24.2.1"
    compile "com.android.support:recyclerview-v7:24.2.1"
    compile "com.android.support:design:24.2.1"
    compile 'com.squareup.okhttp:okhttp:2.7.5'
    compile 'com.nineoldandroids:library:2.4.0'
    compile 'org.springframework.android:spring-android-rest-template:2.0.0.M3'
    compile 'com.github.bumptech.glide:okhttp-integration:1.4.0'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'jp.wasabeef:glide-transformations:2.0.1'
    compile 'com.daimajia.slider:library:1.1.5@aar'
    compile 'com.google.code.gson:gson:2.7'
    compile 'com.squareup:otto:1.3.8'
}
