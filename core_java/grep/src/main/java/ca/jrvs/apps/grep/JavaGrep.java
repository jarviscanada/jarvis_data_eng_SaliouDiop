package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {
    /**
     * Top level search workflow.
     * @throws IOException;
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all files.
     * @param rootDir root directory
     * @return all files in the directory
     */
    List<File> listFiles(String rootDir);

    /**
     * Read a file and return all lines.
     * @param inputFile file to read
     * @return all lines in the file
     * @throws IllegalArgumentException if a given input file is not a file
     */
    List<String> readLines(File inputFile) throws IllegalArgumentException;

    /**
     * Check if a line contains the regex pattern (passed byt the user).
     * @param line input string
     * @return true if the line contains the pattern, false otherwise
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file.
     *
     * Explore: FileOutputStream, OutputStreamWriter, BufferedWriter
     * @param lines matched lines
     * @throws IOException if an I/O error occurs
     */
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();
    void setRootPath(String rootPath);

    String getRegex();
    void setRegex(String regex);

    String getOutFile();
    void setOutFile(String outputFile);
}