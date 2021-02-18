FROM openjdk:15
COPY . out/production/InternshipAssignments/dictionaryimplementation
WORKDIR /out/production/InternshipAssignments/dictionaryimplementation
COPY out/logfiles/logs.log src/main/logs.log
RUN javac src/main/java/dictionaryimplementation/Dictionary.java
CMD ["java","src/main/java/dictionaryimplementation/Dictionary.java"]