package test.ai.rules.impl;

import main.ai.engine.KnowledgeBase;
import main.ai.rules.impl.TurnOffACRule;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TurnOffACRuleTest {
    private KnowledgeBase kb;
    private TurnOffACRule rule;

    @Before
    public void setUp() {
        kb = new KnowledgeBase();
        rule = new TurnOffACRule();
    }

    @Test
    public void testEvaluateTrue() {
        kb.setFact("temperature", 20);
        kb.setFact("ac_status", "on");
        assertTrue(rule.evaluate(kb));
    }

    @Test
    public void testEvaluateFalseTemperatureHigh() {
        kb.setFact("temperature", 25);
        kb.setFact("ac_status", "on");
        assertFalse(rule.evaluate(kb));
    }

    @Test
    public void testEvaluateFalseACOff() {
        kb.setFact("temperature", 20);
        kb.setFact("ac_status", "off");
        assertFalse(rule.evaluate(kb));
    }

    @Test
    public void testExecute() {
        kb.setFact("ac_status", "on");
        rule.execute(kb);
        assertEquals("off", kb.getFact("ac_status"));
    }
}