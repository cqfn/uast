/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Mapping of the list of nodes by positions based on node types.
 *
 * @since 1.0
 */
public class ChildrenMapper {
    /**
     * The list of child descriptors.
     */
    private final List<ChildDescriptor> descriptors;

    /**
     * Required positions (must be filled in).
     */
    private PositionSet required;

    /**
     * All possible positions.
     */
    private PositionSet possible;

    /**
     * Number of unused cells of each type.
     */
    private Map<String, Integer> unused;

    /**
     * The appropriate types from the descriptors for each node.
     */
    private Map<Node, String> suitable;

    /**
     * Constructor.
     * @param descriptors The list of child descriptors.
     */
    public ChildrenMapper(final List<ChildDescriptor> descriptors) {
        this.descriptors = descriptors;
    }

    /**
     * Maps the list of nodes by positions.
     * The algorithm fills an array in which each node is placed at a suitable position for it.
     * @param destination The array in which each node is placed at a suitable position
     * @param source The source list of nodes
     * @return Mapping result, {@code true} if such a mapping is possible (array was filled)
     */
    public boolean map(final Node[] destination, final List<Node> source) {
        boolean result = false;
        final int capacity = this.descriptors.size();
        final int count = source.size();
        if (capacity == 0 && count == 0) {
            result = true;
        } else if (capacity >= count) {
            assert destination.length == capacity;
            this.required = new PositionSet(false);
            if (count >= this.required.getCount()) {
                result = this.fullMapping(destination, source);
            }
        }
        return result;
    }

    /**
     * Full mapper that performs matching in two passes.
     * @param destination The array in which each node is placed at a suitable position
     * @param source The source list of nodes
     * @return Mapping result, {@code true} if such a mapping is possible (array was filled)
     */
    private boolean fullMapping(final Node[] destination, final List<Node> source) {
        boolean result;
        do {
            result = this.calculate(source);
            if (!result) {
                break;
            }
            final Node[] array = new Node[source.size()];
            source.toArray(array);
            final int unprocessed = this.bindAllUniqueNodes(destination, array);
            if (unprocessed < 0) {
                result = false;
                break;
            } else if (unprocessed == 0) {
                result = this.required.getCount() == 0;
                break;
            }
            result = this.bindAllNodes(destination, array) && this.required.getCount() == 0;
        } while (false);
        return result;
    }

    /**
     * Counts the number of nodes of each type and calculates the suitable type names.
     * @param nodes The list of nodes
     * @return Calculation result, {@code true} if structures have been filled
     */
    private boolean calculate(final List<Node> nodes) {
        boolean result = true;
        this.possible = new PositionSet(true);
        this.unused = new TreeMap<>();
        this.suitable = new HashMap<>();
        for (final Node node : nodes) {
            final String type = this.possible.findSuitableBaseType(node);
            if (type.isEmpty()) {
                result = false;
                break;
            }
            final Integer count = this.unused.computeIfAbsent(type, x -> 0);
            this.unused.put(type, count + 1);
            this.suitable.put(node, type);
        }
        return result;
    }

    /**
     * Binds all nodes, the type of which occurs once.
     * @param destination Array in which each node is placed at a suitable position
     * @param source Array of source nodes
     * @return Number of unprocessed nodes, or -1 if binding failed
     */
    private int bindAllUniqueNodes(final Node[] destination, final Node... source) {
        int count = source.length;
        for (int index = 0; index < source.length; index = index + 1) {
            final Node node = source[index];
            final String type = this.suitable.get(node);
            if (this.unused.get(type) == 1) {
                count = count - 1;
                List<Integer> positions = this.required.getPositionsByType(type);
                if (positions == null) {
                    positions = this.possible.getPositionsByType(type);
                }
                if (positions.size() > 1) {
                    count = -1;
                    break;
                }
                final Integer position = positions.get(0);
                destination[position] = node;
                source[index] = EmptyTree.INSTANCE;
                this.required.removePosition(type, position);
                this.possible.removePosition(type, position);
                this.unused.put(type, 0);
            }
        }
        return count;
    }

    /**
     * Binds all nodes (given their order).
     * @param destination Array in which each node is placed at a suitable position
     * @param source Array of source nodes
     * @return Binding result, {@code true} if all nodes were bind
     */
    private boolean bindAllNodes(final Node[] destination, final Node... source) {
        boolean result = true;
        for (final Node node : source) {
            if (!(node instanceof EmptyTree)) {
                final String type = this.suitable.get(node);
                final List<Integer> allowed = this.possible.getPositionsByType(type);
                if (allowed == null) {
                    result = false;
                    break;
                }
                final int unprocessed = this.unused.get(type);
                final List<Integer> obligatory = this.required.getPositionsByType(type);
                final Integer position;
                if (obligatory == null && allowed.size() > 1) {
                    result = false;
                    break;
                } else if (obligatory == null || unprocessed > obligatory.size()) {
                    position = allowed.get(0);
                } else  {
                    position = obligatory.get(0);
                }
                destination[position] = node;
                this.required.removePosition(type, position);
                this.possible.removePosition(type, position);
                this.unused.put(type, unprocessed - 1);
            }
        }
        return result;
    }

    /**
     * The set of types with a relationship of possible position for a node of each such type.
     * @since 1.0
     */
    private final class PositionSet {
        /**
         * Also consider optional items.
         */
        private final boolean optional;

        /**
         * The set of positions arranged by type name.
         */
        private Map<String, List<Integer>> positions;

        /**
         * The number of positions.
         */
        private int count;

        /**
         * Constructor.
         * @param optional Also consider optional items
         */
        private PositionSet(final boolean optional) {
            this.optional = optional;
        }

        /**
         * Returns the number of positions.
         * @return The number of positions
         */
        public int getCount() {
            return this.count;
        }

        /**
         * Returns list of positions by type name.
         * @param type The type name
         * @return The list of positions
         */
        public List<Integer> getPositionsByType(final String type) {
            this.init();
            return this.positions.get(type);
        }

        /**
         * Looks for suitable base type that can be mapped.
         * @param node The node
         * @return Base type or an empty string if node can't be mapped
         */
        public String findSuitableBaseType(final Node node) {
            this.init();
            String result = "";
            final Type type = node.getType();
            final String name = type.getName();
            if (this.positions.containsKey(name)) {
                result = name;
            } else {
                for (final String group : this.positions.keySet()) {
                    if (type.belongsToGroup(group)) {
                        result = group;
                        break;
                    }
                }
            }
            return result;
        }

        /**
         * Removes position from the set.
         * @param type The type name
         * @param index The position index
         */
        public void removePosition(final String type, final Integer index) {
            this.init();
            final List<Integer> list = this.positions.get(type);
            if (list != null && list.remove(index)) {
                this.count = this.count - 1;
                if (list.isEmpty()) {
                    this.positions.remove(type);
                }
            }
        }

        /**
         * Initializes the set of positions.
         */
        private void init() {
            if (this.positions == null) {
                this.positions = new TreeMap<>();
                this.count = 0;
                int index = 0;
                for (final ChildDescriptor descriptor : ChildrenMapper.this.descriptors) {
                    boolean allowed = true;
                    if (!this.optional && descriptor.isOptional()) {
                        allowed = false;
                    }
                    if (allowed) {
                        final String type = descriptor.getType();
                        final List<Integer> indexes = this.positions.computeIfAbsent(
                            type, x -> new ArrayList<>(2)
                        );
                        indexes.add(index);
                        this.count = this.count + 1;
                    }
                    index = index + 1;
                }
            }
        }
    }
}
