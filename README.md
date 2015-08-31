# 13번 출구 - Knock
Application Description.


# Member
- Developer - 김용성, 윤도현, 민지영
- Designer - 김예빈(M), 박철완, 조영배

# Developer Enviroment
- Client : Android(API 21), Android Studio v1.3.2
- Server : AWS(ec2 - ubuntu) / Node.js + express.js  / mySql

# Used android library
- Gson - Convertor between java object and JSON.  / <a href="https://github.com/google/gson">Link</a>
- Loop Android Asynchronous Http Client - A Callback-Based Http Client Library for Android. / <a href="http://loopj.com/android-async-http/">Link</a>
- Universal Image Loader - Loading, caching and displaying images on Android. / <a href="https://github.com/nostra13/Android-Universal-Image-Loader">Link</a>

# Client dependencies code
```java
compile 'com.android.support:appcompat-v7:22.2.1'
compile 'com.google.android.gms:play-services-gcm:7.5.+'
compile 'com.google.code.gson:gson:2.2.+'
compile 'com.loopj.android:android-async-http:1.4.8'
compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.4'
```

# Module in Server
- connect-multiparty - Multipart parsing middleware for connect using multiparty.
- easyimage - A promise-based, user-friendly module for processing images in Node.js.
- node-gcm - Node.JS wrapper library-port for Google Cloud Messaging for Android.
- mysql - A node.js driver for mysql.
