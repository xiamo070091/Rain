package com.tangml.rain.chunk;

import java.util.ArrayList;

public class Chunk {
    private ArrayList<Chunk> chunks;
    public void addChunk(Chunk chunk) {
        chunks.add(chunk);
    }
    public void removeChunk(Chunk chunk) {
        chunks.remove(chunk);
    }
    public boolean isChunk(Chunk chunk) {
        //遍历chunks
        for (Chunk c : chunks) {
            if (c.equals(chunk)) {
                return true;
            }
        }
        return false;
    }
}
