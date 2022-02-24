package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepImp implements JavaGrep {

    final Logger logger = Logger.getLogger(JavaGrepImp.class);

    private String regex;
    private String rootPath;
    private String outFile;

    public JavaGrepImp(String regex, String rootPath, String outFile) {
        this.regex = regex;
        this.rootPath = rootPath;
        this.outFile = outFile;
    }

    public static void main(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: JavaGrep <regex> <root-path> <out-file>");
        }

        BasicConfigurator.configure();
        JavaGrepImp javaGrepImp = new JavaGrepImp(args[0], args[1], args[2]);

        try {
            javaGrepImp.process();
        } catch (IOException e) {
            javaGrepImp.logger.error("IOException: " + e.getMessage());
        }
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public void process() throws IOException {
        List<File> files = listFiles(rootPath);
        for (File file : files) {
            List<String> lines = readLines(file);
            List<String> matchedLines = findMatchedLines(lines);
            writeToFile(matchedLines);
        }
    }

    private List<String> findMatchedLines(List<String> lines) {
        List<String> matchedLines = new ArrayList<>();
        for (String line : lines) {
            if (containsPattern(line)) {
                matchedLines.add(line);
            }
        }
        return matchedLines;
    }

    @Override
    public List<File> listFiles(String rootDir) {
        File root = new File(rootDir);
        List<File> files = new ArrayList<>();
        if (!root.isDirectory()) {
            throw new IllegalArgumentException("Given root path is not a directory");
        }
        File[] listFiles = root.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile())
                    files.add(file);
            }
        }
        return files;
    }

    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(inputFile.toPath());
        } catch (IOException e) {
            logger.error("IOException: " + e.getMessage());
        }
        return lines;
    }

    @Override
    public boolean containsPattern(String line) {
        return line.matches(regex);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.getOutFile()));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
        logger.debug("Wrote to file: " + this.getOutFile());
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

}