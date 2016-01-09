all: $(shell find src -type f) *.jar
	./gradlew installDist
runListener:
	./build/install/scala-dsl/bin/scala-dsl scala_dsl.Listener
runTalker:
	./build/install/scala-dsl/bin/scala-dsl scala_dsl.Talker
idea: 
	./gradlew idea
clean: 
	./gradlew clean 
	
