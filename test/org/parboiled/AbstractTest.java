package org.parboiled;

import static org.parboiled.TestUtils.assertEqualsMultiline;
import static org.parboiled.support.ParseTreeUtils.printNodeTree;
import org.parboiled.utils.StringUtils2;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

abstract class AbstractTest {

    public void test(Rule rule, String input, String expectedTree) {
        ParsingResult parsingResult = Parser.parse(rule, input);
        assertTrue(parsingResult.matched, "Did not match input");
        if (!parsingResult.parseErrors.isEmpty()) {
            fail("\n--- ParseErrors ---\n" +
                    StringUtils2.join(parsingResult.parseErrors, "---\n") +
                    "\n--- ParseTree ---\n" +
                    printNodeTree(parsingResult)
            );
        }

        String actualTree = printNodeTree(parsingResult);
        assertEqualsMultiline(actualTree, expectedTree);
    }

    public void testFail(Rule rule, String input, String expectedTree, String expectedErrors) {
        ParsingResult parsingResult = Parser.parse(rule, input);
        assertTrue(parsingResult.matched, "Did not match input");
        String actualTree = printNodeTree(parsingResult);
        assertEqualsMultiline(actualTree, expectedTree);
        assertEqualsMultiline(StringUtils2.join(parsingResult.parseErrors, "---\n"), expectedErrors);
    }

}