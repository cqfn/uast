/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.example.green.Addition;
import org.uast.uast.example.green.IntegerLiteral;

/**
 * Testing {@link ChildrenMapper} class.
 *
 * @since 1.0
 */
@SuppressWarnings("PMD.TooManyMethods")
public class ChildrenMapperTest {
    /**
     * Testing mapping with all obligatory children.
     */
    @Test
    public void testAllNonOptionalChildrenMapping() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A"),
                new ChildDescriptor("B")
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 1)
            ),
            true
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the mapper with a list of nodes in which the order is mixed up.
     */
    @Test
    public void testIncorrectOrderChildren() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", false),
                new ChildDescriptor("C", true),
                new ChildDescriptor("D", true)
            ),
            Arrays.asList(
                new NodeDescriptor("D", 3),
                new NodeDescriptor("B", 1),
                new NodeDescriptor("A", 0)
            ),
            true
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the mapper with one node that should be not mapped.
     */
    @Test
    public void testOneNodeNotMapped() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A")
            ),
            Arrays.asList(
                new NodeDescriptor("B")
            ),
            false
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the mapper with complex variant of children,
     * where some children are obligatory, some - not.
     */
    @Test
    public void testAllVariousChildrenMapping() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", true),
                new ChildDescriptor("B", false),
                new ChildDescriptor("A", true)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 1),
                new NodeDescriptor("B", 2),
                new NodeDescriptor("A", 3)
            ),
            true
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the case when the descriptor contains two children of the same type, and one
     * of them is optional, but the list of nodes contains only one child of this type.
     */
    @Test
    public void testOnlyNonOptionalFromVariousChildrenMapping() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", true),
                new ChildDescriptor("C", false),
                new ChildDescriptor("B", false),
                new ChildDescriptor("A", true)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("C", 2),
                new NodeDescriptor("B", 3)
            ),
            true
        );
        Assertions.assertTrue(result);
    }

    /**
     * Another complex test case.
     */
    @Test
    public void testNonOptionalAndOptionalFromVariousChildrenMapping() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", true),
                new ChildDescriptor("C", false),
                new ChildDescriptor("B", false),
                new ChildDescriptor("A", true)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 1),
                new NodeDescriptor("C", 2),
                new NodeDescriptor("B", 3)
            ),
            true
        );
        Assertions.assertTrue(result);
    }

    /**
     * Test the case where the descriptor contains two mandatory nodes of the same type
     * and another optional node. However, the node list contains only two nodes of this type.
     * Therefore, these nodes must be distributed to the correct cells.
     */
    @Test
    public void testNonOptionalAndOptionalOfSameTypeMapping() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", true),
                new ChildDescriptor("B", false),
                new ChildDescriptor("B", false)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 2),
                new NodeDescriptor("B", 3)
            ),
            true
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the case where a descriptor contains several pairs of nodes, one required
     * and one optional node of each type.
     */
    @Test
    public void testNonOptionalOfSameTypeAsOptionalMapping() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", true),
                new ChildDescriptor("B", true),
                new ChildDescriptor("C", true),
                new ChildDescriptor("C", false),
                new ChildDescriptor("D", false),
                new ChildDescriptor("D", true)
            ),
            Arrays.asList(
                new NodeDescriptor("C", 3),
                new NodeDescriptor("D", 4)
            ),
            true
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the case where missed a non-optional child.
     */
    @Test
    public void testMissingNonOptionalChildMapping() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", true),
                new ChildDescriptor("B", true),
                new ChildDescriptor("C", false),
                new ChildDescriptor("D", true)
            ),
            Arrays.asList(
                new NodeDescriptor("B", 1),
                new NodeDescriptor("D", 3)
            ),
            false
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the case where the node list has one extra child.
     */
    @Test
    public void testExtraChildMapping() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", true),
                new ChildDescriptor("B", true),
                new ChildDescriptor("C", false)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 1),
                new NodeDescriptor("B", 2)
            ),
            false
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the case where the node list has one node
     * that does not match with descriptors list.
     */
    @Test
    public void testOneNotMatchedNode() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", true),
                new ChildDescriptor("B", true),
                new ChildDescriptor("A", true)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 1),
                new NodeDescriptor("B", 2),
                new NodeDescriptor("C", 3)
            ),
            false
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the case when there are two optional nodes of the same type,
     * accordingly the mapper cannot make the right choice.
     */
    @Test
    public void testSideBySideControversialOptionalNodes() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", true),
                new ChildDescriptor("B", true),
                new ChildDescriptor("A", false)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 1),
                new NodeDescriptor("A", 3)
            ),
            false
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the case when there are three optional nodes of the same type,
     * accordingly the mapper cannot make the right choice.
     */
    @Test
    public void testThreeControversialOptionalNodes() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", true),
                new ChildDescriptor("B", true),
                new ChildDescriptor("B", true),
                new ChildDescriptor("A", false)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 1),
                new NodeDescriptor("B", 2),
                new NodeDescriptor("A", 4)
            ),
            false
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the case when there are several separated optional nodes of the same type,
     * accordingly the mapper cannot make the right choice.
     */
    @Test
    public void testSeparatedControversialOptionalNodes() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", true),
                new ChildDescriptor("C", true),
                new ChildDescriptor("B", true),
                new ChildDescriptor("D", true),
                new ChildDescriptor("A", false)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 1),
                new NodeDescriptor("D", 4),
                new NodeDescriptor("A", 5)
            ),
            false
        );
        Assertions.assertTrue(result);
    }

    /**
     * Testing the case when the node list has more records than the descriptors list.
     */
    @Test
    public void testIncorrectNumberOfChildren() {
        final boolean result = this.testMapping(
            Arrays.asList(
                new ChildDescriptor("A", false),
                new ChildDescriptor("B", true)
            ),
            Arrays.asList(
                new NodeDescriptor("A", 0),
                new NodeDescriptor("B", 1),
                new NodeDescriptor("C", 2)
            ),
            false
        );
        Assertions.assertTrue(result);
    }

    /**
     * Mapper testing when nodes in the list have a type that is inherited
     * from the type specified in the descriptor.
     */
    @Test
    public void testMappingWithInheritance() {
        final String type = "Expression";
        final List<ChildDescriptor> descriptors = Arrays.asList(
            new ChildDescriptor(type),
            new ChildDescriptor(type)
        );
        final ChildrenMapper mapper = new ChildrenMapper(descriptors);
        IntegerLiteral.Constructor icr = new IntegerLiteral.Constructor();
        icr.setData("7");
        final Node first = icr.createNode();
        icr = new IntegerLiteral.Constructor();
        icr.setData("11");
        final Node second = icr.createNode();
        final Addition.Constructor acr = new Addition.Constructor();
        acr.setChildrenList(Arrays.asList(first, second));
        final Node addition = acr.createNode();
        icr = new IntegerLiteral.Constructor();
        icr.setData("13");
        final Node third = icr.createNode();
        final Node[] mapping = new Node[2];
        final boolean result = mapper.map(mapping, Arrays.asList(addition, third));
        Assertions.assertTrue(result);
        Assertions.assertEquals(addition, mapping[0]);
        Assertions.assertEquals(third, mapping[1]);
    }

    /**
     * Common test for the {@link ChildrenMapper} class.
     * @param types The list of types
     * @param nodes The list of nodes
     * @param success If expected success mapping
     * @return Result of testing, {@Code true} if test passed
     */
    private boolean testMapping(final List<ChildDescriptor> types,
        final List<NodeDescriptor> nodes,
        final boolean success) {
        final List<Node> children = new ArrayList<>(nodes.size());
        for (final NodeDescriptor node : nodes) {
            final DraftNode.Constructor ctor = new DraftNode.Constructor();
            ctor.setName(node.getTypeName());
            ctor.setData(node.getIndex());
            children.add(ctor.createNode());
        }
        final Node[] mapping = new Node[types.size()];
        final ChildrenMapper mapper = new ChildrenMapper(types);
        boolean result = mapper.map(mapping, children);
        if (success && result) {
            for (int index = 0; index < mapping.length; index = index + 1) {
                if (mapping[index] != null && Integer.parseInt(mapping[index].getData()) != index) {
                    result = false;
                    break;
                }
            }
        } else if (!success) {
            result = !result;
        }
        return result;
    }

    /**
     * Node descriptor for testing.
     *
     * @since 1.0
     */
    private static final class NodeDescriptor {
        /**
         * Name of the node type.
         */
        private final String type;

        /**
         * Expected index after mapping.
         */
        private final int index;

        /**
         * Constructor.
         * @param type Name of the node type
         * @param index Expected index after mapping
         */
        NodeDescriptor(final String type, final int index) {
            this.type = type;
            this.index = index;
        }

        /**
         * Constructor.
         * @param type Name of the node type
         */
        NodeDescriptor(final String type) {
            this(type, -1);
        }

        /**
         * Returns the name of the node type.
         * @return The name
         */
        public String getTypeName() {
            return this.type;
        }

        /**
         * Returns expected index after mapping.
         * @return The index (as a string)
         */
        public String getIndex() {
            return String.valueOf(this.index);
        }

        @Override
        public String toString() {
            return new StringBuilder()
                .append(this.type)
                .append(':')
                .append(this.index)
                .toString();
        }
    }
}
