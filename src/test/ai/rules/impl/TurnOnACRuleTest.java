package test.ai.rules.impl;

import main.ai.engine.KnowledgeBase;
import main.ai.rules.impl.TurnOnACRule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TurnOnACRuleTest {
    private KnowledgeBase kb;
    private TurnOnACRule rule;

    @Before
    public void setUp() {
        kb = new KnowledgeBase();
        rule = new TurnOnACRule();
    }

    @Test
    public void testEvaluateTrue() {
        kb.setFact("temperature", 30);
        kb.setFact("ac_status", "off");
        assertTrue(rule.evaluate(kb));
    }

    @Test
    public void testEvaluateFalse() {
        kb.setFact("temperature", 20);
        kb.setFact("ac_status", "off");
        assertFalse(rule.evaluate(kb));
    }

    @Test
    public void testExecute() {
        kb.setFact("ac_status", "off");
        rule.execute(kb);
        assertEquals("on", kb.getFact("ac_status"));
    }
}