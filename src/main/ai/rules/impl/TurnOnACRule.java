package main.ai.rules.impl;

import main.ai.engine.KnowledgeBase;
import main.ai.rules.Rule;
import main.ai.util.LoggerUtil;

import java.util.logging.Logger;

public class TurnOnACRule implements Rule {
    private static final Logger logger = LoggerUtil.getLogger(TurnOnACRule.class.getName());

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean evaluate(KnowledgeBase kb) {
        try {
            Integer temp = (Integer) kb.getFact("temperature");
            String acStatus = (String) kb.getFact("ac_status");
            return temp != null && temp > 25 && "off".equals(acStatus);
        } catch (ClassCastException e) {
            logger.warning("Invalid fact type: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void execute(KnowledgeBase kb) {
        logger.info("Turning on the air conditioner.");
        kb.setFact("ac_status", "on");
    }
}