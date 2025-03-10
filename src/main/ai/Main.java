package main.ai;

import main.ai.engine.KnowledgeBase;
import main.ai.engine.RuleEngine;
import main.ai.rules.Rule;
import main.ai.rules.impl.TurnOnACRule;
import main.ai.rules.impl.TurnOffACRule;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        KnowledgeBase kb = new KnowledgeBase();
        kb.setFact("temperature",20);
        kb.setFact("ac_status", "off");

        List<Rule> rules = new ArrayList<>();
        rules.add(new TurnOnACRule());
        rules.add(new TurnOffACRule());

        RuleEngine engine = new RuleEngine(rules, kb);

        for (int i = 0; i < 20; i++) {
            System.out.println("Cycle " + i + ": temperature = " + kb.getFact("temperature") +
                    ", ac_status = " + kb.getFact("ac_status"));
            engine.run();
            String acStatus = (String) kb.getFact("ac_status");
            Integer temp = (Integer) kb.getFact("temperature");
            if ("on".equals(acStatus)) {
                kb.setFact("temperature", temp - 1);
            } else {
                kb.setFact("temperature", temp + 1);
            }
        }
    }
}