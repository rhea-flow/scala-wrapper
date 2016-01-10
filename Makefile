all: $(shell find src -type f) *.jar
	@./gradlew installDist
run:
	@./build/install/scala-dsl/bin/scala-dsl scala_dsl.DSL
idea: 
	@./gradlew idea
clean: 
	@./gradlew clean 
	
