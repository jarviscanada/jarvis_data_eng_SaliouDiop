package ca.jrvs.apps.grep;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaGrepImpTest {

    @Test
    void main() throws IOException {

    }

    @Test
    void getRegex() {
        JavaGrepImp grep = new JavaGrepImp( ".*Romeo.*Juliet.*", "src/test/resources", "src/test/resources/out.txt" );
        assertEquals( grep.getRegex(), ".*Romeo.*Juliet.*" );
    }

    @Test
    void setRegex() {
        JavaGrepImp grep = new JavaGrepImp( ".*Romeo.*Juliet.*", "src/test/resources", "src/test/resources/out.txt" );
        grep.setRegex( ".*Romeo" );
        assertEquals( grep.getRegex(), ".*Romeo" );
    }

    @Test
    void process() {

    }

    @Test
    void listFiles() {
        String rootDir = "/home/saliou/Documents/test";
        JavaGrepImp grep = new JavaGrepImp( ".*Romeo", rootDir, "src/test/resources/out.txt" );
        List<File> files = new ArrayList<>();
        files.add( new File( "/home/saliou/Documents/test/test1.txt" ) );
        files.add( new File( "/home/saliou/Documents/test/test2.txt" ) );
        assertEquals( grep.listFiles(rootDir), files );
    }

    @Test
    void readLines() {
    }

    @Test
    void containsPattern() {
    }

    @Test
    void writeToFile() {
        JavaGrepImp grep = new JavaGrepImp( ".*Romeo.*Juliet.*", "src/test/resources", "src/test/resources/out.txt" );
        List<String> lines = Arrays.asList( "test1", "test2", "test3" );
        try {
            grep.writeToFile( lines );
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals( grep.getOutFile(), "src/test/resources/out.txt" );

    }

    @Test
    void getRootPath() {
        JavaGrepImp grep = new JavaGrepImp( ".*Romeo.*Juliet.*", "src/test/resources", "src/test/resources/out.txt" );
        assertEquals( grep.getRootPath(), "src/test/resources" );
    }

    @Test
    void setRootPath() {
        JavaGrepImp grep = new JavaGrepImp( ".*Romeo.*Juliet.*", "src/test/resources", "src/test/resources/out.txt" );
        grep.setRootPath( "src/test/resources/test" );
        assertEquals( grep.getRootPath(), "src/test/resources/test" );
    }

    @Test
    void getOutFile() {
        JavaGrepImp grep = new JavaGrepImp( ".*Romeo.*Juliet.*", "src/test/resources", "src/test/resources/out.txt" );
        assertEquals( grep.getOutFile(), "src/test/resources/out.txt" );
    }

    @Test
    void setOutFile() {
        JavaGrepImp grep = new JavaGrepImp( ".*Romeo.*Juliet.*", "src/test/resources", "src/test/resources/out.txt" );
        grep.setOutFile( "src/test/resources/test.txt" );
        assertEquals( grep.getOutFile(), "src/test/resources/test.txt" );
    }
}