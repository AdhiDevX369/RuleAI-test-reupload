package main.ai.engine;

import java.util.concurrent.ConcurrentHashMap;

public class KnowledgeBase {
    private final ConcurrentHashMap<String, Object> facts = new ConcurrentHashMap<>();

    public void setFact(String key, Object value) {
        facts.put(key, value);
    }

    public Object getFact(String key) {
        return facts.get(key);
    }
}