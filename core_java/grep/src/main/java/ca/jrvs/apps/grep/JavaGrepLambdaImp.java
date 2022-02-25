package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JavaGrepLambdaImp extends JavaGrepImp {

    public JavaGrepLambdaImp(String regex, String rootPath, String outFile) {
        super(regex, rootPath, outFile);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: JavaGrep <regex> <root-path> <out-file>");
        }
        JavaGrepLambdaImp grep = new JavaGrepLambdaImp(args[0], args[1], args[2]);
        try {
            grep.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<File> listFiles(String rootDir) {
        Stream<File> fileStream = Stream.of(Objects
                .requireNonNull(new File(rootDir).listFiles()));
        return fileStream
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException {
        List<String> lineStream = null;
        try {
            Stream<String> lines = Files.lines(inputFile.toPath());
            lineStream = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineStream;
    }
}
