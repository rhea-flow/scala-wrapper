all: $(shell find src -type f) *.jar
	@./gradlew installDist
run:
	@./build/install/scala-dsl/bin/scala-dsl scala_dsl.Adhoc
runHamming:
	@./build/install/scala-dsl/bin/scala-dsl scala_dsl.HammingNumbers
idea: 
	@./gradlew idea
clean: 
	@./gradlew clean 
	
