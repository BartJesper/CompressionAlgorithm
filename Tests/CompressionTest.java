import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompressionTest {
    private String[] test1 = {"AAAAABBB#####", "A±5B±3#±5"};
    private String[] test2 = {"AAAAA±BBB±±#####ABABABABAB", "A±5±±1B±3±±2#±5A±1B±1A±1B±1A±1B±1A±1B±1A±1B±1"};

    private String[] test3 = {"AAAAAAAAAAA±BC", "A±11±±1B±1C±1"};

    @Test
    public void compressTest1() {
        assertEquals(Compression.compress(test1[0]), test1[1]);
    }

    @Test
    public void compressTest2() {
        assertEquals(Compression.compress(test2[0]), test2[1]);
    }

    @Test
    public void compressTest3() {
        assertEquals(Compression.compress(test3[0]), test3[1]);
    }

    @Test
    public void decompressTest1() {
        assertEquals(Compression.decompress(test1[1]), test1[0]);
    }

    @Test
    public void decompressTest2() {
        assertEquals(Compression.decompress(test2[1]), test2[0]);
    }

    @Test
    public void decompressTest3() {
        assertEquals(Compression.decompress(test3[1]), test3[0]);
    }
}