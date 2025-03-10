package main.ai.engine;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import main.ai.rules.Rule;
import main.ai.util.LoggerUtil;

public class RuleEngine {
    private static final Logger logger = LoggerUtil.getLogger(RuleEngine.class.getName());
    private List<Rule> rules;
    private KnowledgeBase kb;

    public RuleEngine(List<Rule> rules, KnowledgeBase kb) {
        this.rules = new ArrayList<>(rules);
        this.rules.sort(Comparator.comparingInt(Rule::getPriority).reversed());
        this.kb = kb;
    }

    public void run() {
        boolean fired;
        do {
            fired = false;
            for (Rule rule : rules) {
                try {
                    if (rule.evaluate(kb)) {
                        logger.info("Firing rule: " + rule.getClass().getSimpleName());
                        rule.execute(kb);
                        fired = true;
                    }
                } catch (Exception e) {
                    logger.severe("Error in rule " + rule.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        } while (fired);
    }
}