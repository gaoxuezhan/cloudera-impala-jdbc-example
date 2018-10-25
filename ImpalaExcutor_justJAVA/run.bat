javac ImpalaExecutor.java
java -Xbootclasspath/a:lib/hive_metastore.jar;lib/hive_service.jar;lib/ImpalaJDBC41.jar;lib/libfb303-0.9.0.jar;lib/libthrift-0.9.0.jar;lib/log4j-1.2.14.jar;lib/ql.jar;lib/slf4j-api-1.5.11.jar;lib/slf4j-log4j12-1.5.11.jar;lib/TCLIServiceClient.jar;lib/zookeeper-3.4.6.jar ImpalaExecutor
pause
