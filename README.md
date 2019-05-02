# pedidos
Pedidos Spring Ionic

#Build

To generate a release build for Android, we can use the following cordova cli command:
```
$ ionic cordova build --release android
```

Let’s generate our private key using the keytool command that comes with the JDK.
Note: Make sure to save this file somewhere safe, if you lose it you won’t be able to submit updates to your app!
```
$ keytool -genkey -v -keystore my-release-key.keystore -alias alias_name -keyalg RSA -keysize 2048 -validity 10000
```

To sign the unsigned APK, run the jarsigner tool which is also included in the JDK:
```
$ jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore my-release-key.keystore HelloWorld-release-unsigned.apk alias_name
```

This signs the apk in place. Finally, we need to run the zip align tool to optimize the APK. The zipalign tool can be found in /path/to/Android/sdk/build-tools/VERSION/zipalign. For example, on OS X with Android Studio installed, zipalign is in ~/Library/Android/sdk/build-tools/VERSION/zipalign:
```
$ zipalign -v 4 HelloWorld-release-unsigned.apk HelloWorld.apk
```
