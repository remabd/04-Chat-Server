# 04-Chat

Ce repository implémente un serveur socket en Java, il permet la connexion en simultannée de plusieurs membres, peut les répartir en plusieurs channels et broadcast les messages au sein d'un channel.
Ce repository fonctionne en pair avec [04-Chat-Client](https://github.com/remabd/04-Chat-Client)

# Build and run

After cloning:
```
cd 04-Chat
javac -d bin src/chat/*.java
java -cp bin chat.Main
```

or
```
cd 04-Chat
sh buildAndRun.sh
```
