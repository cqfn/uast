/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.handlers.json;

import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.uast.uast.base.Builder;
import org.uast.uast.base.EmptyTree;
import org.uast.uast.base.Factory;
import org.uast.uast.base.Node;
import org.uast.uast.lang.FactorySelector;

/**
 * Converts a string contains JSON object to a syntax tree.
 *
 * @since 1.0
 */
public class JsonDeserializer {
    /**
     * The 'language' string.
     */
    private static final String STR_LANGUAGE = "language";

    /**
     * The 'root' string.
     */
    private static final String STR_ROOT = "root";

    /**
     * The 'type' string.
     */
    private static final String STR_TYPE = "type";

    /**
     * The 'data' string.
     */
    private static final String STR_DATA = "data";

    /**
     * The 'children' string.
     */
    private static final String STR_CHILDREN = "children";

    /**
     * String contains JSON object.
     */
    private final String source;

    /**
     * The suitable factory for node creation.
     */
    private Factory factory;

    /**
     * Constructor.
     * @param source String contains JSON object.
     */
    public JsonDeserializer(final String source) {
        this.source = source;
    }

    /**
     * Converts the source string contains JSON object to a syntax tree.
     * @return Root node
     */
    public Node convert() {
        Node result = EmptyTree.INSTANCE;
        JsonElement element = new Gson().fromJson(this.source, JsonElement.class);
        if (element.isJsonObject()) {
            final JsonObject obj = element.getAsJsonObject();
            String language = "";
            if (obj.has(JsonDeserializer.STR_LANGUAGE)) {
                language = obj.get(STR_LANGUAGE).getAsString();
            }
            this.factory = FactorySelector.INSTANCE.select(language);
            if (obj.has(JsonDeserializer.STR_ROOT)) {
                result = convertElement(obj.get(JsonDeserializer.STR_ROOT));
            }
        }
        return result;
    }

    /**
     * Converts JSON element to node.
     * @param element JSON element
     * @return A node
     */
    private Node convertElement(final JsonElement element) {
        Node result = EmptyTree.INSTANCE;
        if (element.isJsonObject()) {
            final JsonObject obj = element.getAsJsonObject();
            result = this.convertObject(obj);
        }
        return result;
    }

    /**
     * Converts JSON object to node.
     * @param obj JSON element
     * @return A node
     */
    private Node convertObject(final JsonObject obj) {
        Node result = EmptyTree.INSTANCE;
        if (obj.has(JsonDeserializer.STR_TYPE)) {
            final String type = obj.get(JsonDeserializer.STR_TYPE).getAsString();
            final Builder builder = this.factory.createBuilder(type);
            fillNodeBuilder(obj, builder);
            if (builder.isValid()) {
                result = builder.createNode();
            }
        }
        return result;
    }

    /**
     * Fills the node builder.
     * @param obj JSON element
     * @param builder The node builder
     */
    private void fillNodeBuilder(final JsonObject obj, final Builder builder) {
        if (obj.has(JsonDeserializer.STR_DATA)) {
            builder.setData(obj.get(JsonDeserializer.STR_DATA).getAsString());
        }
        if (obj.has(JsonDeserializer.STR_CHILDREN)) {
            final JsonElement children = obj.get(JsonDeserializer.STR_CHILDREN);
            if (children.isJsonArray()) {
                final List<Node> list = new LinkedList<>();
                for (final JsonElement child : children.getAsJsonArray()) {
                    list.add(this.convertElement(child));
                }
                builder.setChildrenList(list);
            }
        }
    }
}
