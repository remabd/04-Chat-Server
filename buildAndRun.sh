#! /bin/sh
javac -d classes $(find src -name "*.java") && cd classes && java chat.Main
