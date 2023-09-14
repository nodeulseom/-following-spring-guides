if [ "$1" == "jvm" ]; then
  DEMO_COMMAND="$JAVA_HOME/bin/java -jar build/libs/spring-native-demo-0.0.1-SNAPSHOT.jar -Xmx64m"
  echo "Switched to JIT: $DEMO_COMMAND"
elif [ "$1" == "native" ]; then
  DEMO_COMMAND="./build/native/nativeCompile/spring-native-demo -Xmx256m"
  echo "Switched to AOT native executable: $DEMO_COMMAND"
elif [ "$1" == "optimized" ]; then
  DEMO_COMMAND="./build/native/nativeCompile/spring-native-demo -Xmx256m"
  echo "Switched to AOT with PGO optimized executable: $DEMO_COMMAND"
elif [ "$1" == "native-constrained" ]; then
  DEMO_COMMAND="./build/native/nativeCompile/spring-native-demo -Xmx64m"
else
  echo "USAGE: $0 [jvm|native|instrumented|optimized]"
  exit 255
fi