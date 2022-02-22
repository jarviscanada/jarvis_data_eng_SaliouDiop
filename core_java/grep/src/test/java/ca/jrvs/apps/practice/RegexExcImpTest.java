package ca.jrvs.apps.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexExcImpTest {
    // test matchJpeg
    @Test
    void matchJpeg() {
        RegexExcImp regexExcImp = new RegexExcImp();
        assertTrue(regexExcImp.matchJpeg("file.jpeg"));
        assertTrue(regexExcImp.matchJpeg("file.jpg"));
        assertFalse(regexExcImp.matchJpeg("jpeg123"));
        assertFalse(regexExcImp.matchJpeg("jpeg"));
    }

    @Test
    void matchIp() {
        RegexExcImp regexExcImp = new RegexExcImp();
        assertTrue(regexExcImp.matchIp("192.168.0.1"));
        assertFalse(regexExcImp.matchIp("111.111.00"));
    }

    @Test
    void isEmptyLine() {
        RegexExcImp regexExcImp = new RegexExcImp();
        assertTrue(regexExcImp.isEmptyLine(""));
        assertTrue(regexExcImp.isEmptyLine(" "));
        assertFalse(regexExcImp.isEmptyLine("a"));
        assertFalse(regexExcImp.isEmptyLine("a\n"));
    }
}