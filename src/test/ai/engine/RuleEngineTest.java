package test.ai.engine;

import main.ai.engine.KnowledgeBase;
import main.ai.engine.RuleEngine;
import main.ai.rules.Rule;
import main.ai.rules.impl.TurnOffACRule;
import main.ai.rules.impl.TurnOnACRule;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class RuleEngineTest {
    private KnowledgeBase kb;
    private List<Rule> rules;
    private RuleEngine engine;

    @Before
    public void setUp() {
        kb = new KnowledgeBase();
        rules = Arrays.asList(new TurnOnACRule(), new TurnOffACRule());
        engine = new RuleEngine(rules, kb);
    }

    @Test
    public void testRunTurnOnAC() {
        kb.setFact("temperature", 30);
        kb.setFact("ac_status", "off");
        engine.run();
        assertEquals("on", kb.getFact("ac_status"));
    }

    @Test
    public void testRunTurnOffAC() {
        kb.setFact("temperature", 20);
        kb.setFact("ac_status", "on");
        engine.run();
        assertEquals("off", kb.getFact("ac_status"));
    }

    @Test
    public void testRunNoAction() {
        kb.setFact("temperature", 22);
        kb.setFact("ac_status", "off");
        engine.run();
        assertEquals("off", kb.getFact("ac_status"));
    }
}