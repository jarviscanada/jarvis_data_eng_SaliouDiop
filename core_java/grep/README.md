# Introduction

The purpose of this project is to mimic Linux grep commands. The program will read all the files in the current directory and every directory below the current one looking in each file for a given regular expression. The output is written to an output file in the root directory.

This project is written in Java (using Streams, Lambda, and RegEx pattern matching), Maven for packaging, and Docker for easy deployment. It was tested using JUnit Library.

# Quick Start

To use this program, follow this steps :

1. Run the program using Docker

- Pull the docker image

  ```
  $ docker pull salihou/java-grep
  ```

- Run the container

  ```
  $ docker run salihou/java-grep <regex> <rootPath> <outfile>
  ```

#Implemenation

## Pseudocode

```
    for file in listFiles(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            matchedLines.add(line)
    writeToFile(matchedLines)
```

## Performance Issue

The JavaGrepImp class uses Lists to store all the values and readAllLines(Path, Charset) method to read its entire contents in one pass. It takes care of opening and closing the stream, but is not intended for handling large files. This can cause an `OutOfMemoryError` exception.

To solve this Stream API was used to implement `JavaGrepLambdaImp`. The interface can also be updated to use the stream in the `readLines` and `listFiles` methods.

# Test

The testing was done using JUnit to test all the methods (12 units tests) of the application and a sample text file with different regex.

# Deployment

The application was dockerized using the alpine JDK image, copying the JAR file and configuring the entry point to execute the Java process with the JAR file. The image was uploaded to Docker Hub for easy access.

# Improvement

1. Split the file into two parts and process them using Parallel Streams.
2. Add an option to add line numbers into the output file
3. An option to display the output in stdout instead of writing to a file.
