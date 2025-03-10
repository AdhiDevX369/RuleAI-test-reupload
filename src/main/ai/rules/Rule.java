package main.ai.rules;

import main.ai.engine.KnowledgeBase;

public interface Rule {
    int getPriority();
    boolean evaluate(KnowledgeBase kb);
    void execute(KnowledgeBase kb);
}