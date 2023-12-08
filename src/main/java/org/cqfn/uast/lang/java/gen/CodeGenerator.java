/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023 Ivan Kniazkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cqfn.uast.lang.java.gen;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.cqfn.astranaut.core.Node;
import org.cqfn.uast.tree.green.ClassDeclaration;
import org.cqfn.uast.tree.green.ClassType;
import org.cqfn.uast.tree.green.Declarator;
import org.cqfn.uast.tree.green.DeclaratorList;
import org.cqfn.uast.tree.green.Expression;
import org.cqfn.uast.tree.green.ExpressionStatement;
import org.cqfn.uast.tree.green.ExtendsBlock;
import org.cqfn.uast.tree.green.FieldDeclaration;
import org.cqfn.uast.tree.green.FunctionDeclaration;
import org.cqfn.uast.tree.green.ImplementsBlock;
import org.cqfn.uast.tree.green.Modifier;
import org.cqfn.uast.tree.green.ModifierBlock;
import org.cqfn.uast.tree.green.Parameter;
import org.cqfn.uast.tree.green.ParameterBlock;
import org.cqfn.uast.tree.green.Return;
import org.cqfn.uast.tree.green.TypeName;
import org.cqfn.uast.tree.green.VariableDeclaration;
import org.cqfn.uast.tree.java.Synchronized;

/**
 * Code generator for Java constructions (except expressions).
 *
 * @since 0.1.2
 */
public class CodeGenerator {
    /**
     * Map that contains generators.
     */
    private final Map<String, Generator> map;

    /**
     * Source code builder.
     */
    private final CodeBuilder builder;

    /**
     * Constructor.
     * @param builder Source code builder
     */
    public CodeGenerator(final CodeBuilder builder) {
        this.map = Collections.unmodifiableMap(this.init());
        this.builder = builder;
    }

    /**
     * Generates source code from node.
     * @param node A node
     */
    public void generate(final Node node) {
        this.map.get(node.getTypeName()).generate(node);
    }

    /**
     * Initialises map with generators.
     * @return A map
     */
    private Map<String, Generator> init() {
        final Map<String, Generator> gen = new TreeMap<>();
        gen.put(
            "Return",
            node -> {
                final Return stmt = (Return) node;
                if (stmt.getChildCount() == 0) {
                    this.builder.print("return;");
                } else {
                    this.builder.print(
                        String.format(
                            "return %s;",
                            Expressions.INSTANCE.generate(stmt.getExpression())
                        )
                    );
                }
            }
        );
        gen.put(
            "Program",
            node -> {
                for (final Node item : node.getChildrenList()) {
                    this.generate(item);
                }
            }
        );
        gen.put(
            "ClassDeclaration",
            node -> {
                final ClassDeclaration cdecl = (ClassDeclaration) node;
                final StringBuilder header = new StringBuilder();
                header.append(CodeGenerator.generateModifiersList(cdecl.getModifiers()))
                    .append("class ")
                    .append(cdecl.getName().getData())
                    .append(CodeGenerator.generateExtendsBlock(cdecl.getExtendsbl()))
                    .append(CodeGenerator.generateImplementsBlock(cdecl.getImplementsbl()))
                    .append(" {");
                this.builder.print(header.toString());
                this.builder.increaseIndent();
                int idx = 0;
                for (final Node item : cdecl.getBody().getChildrenList()) {
                    if (idx > 0) {
                        this.builder.decreaseIndent();
                        this.builder.print("");
                        this.builder.increaseIndent();
                    }
                    this.generate(item);
                    idx += 1;
                }
                this.builder.decreaseIndent();
                this.builder.print("}");
            }
        );
        gen.putAll(this.initClassMemberDeclarations());
        gen.putAll(this.initStatements());
        return gen;
    }

    /**
     * Initialises map with class member declarations.
     * @return A map
     */
    private Map<String, Generator> initClassMemberDeclarations() {
        final Map<String, Generator> gen = new TreeMap<>();
        gen.put(
            "FieldDeclaration",
            node -> {
                final FieldDeclaration fdecl = (FieldDeclaration) node;
                final StringBuilder code = new StringBuilder();
                code.append(CodeGenerator.generateModifiersList(fdecl.getModifiers()));
                final TypeName type = fdecl.getDatatype();
                if (type == null) {
                    code.append("Object");
                } else {
                    code.append(TypeNames.INSTANCE.generate(type));
                }
                code
                    .append(' ')
                    .append(CodeGenerator.generateDeclaratorsList(fdecl.getDeclarators()))
                    .append(';');
                this.builder.print(code.toString());
            }
        );
        gen.put(
            "FunctionDeclaration",
            node -> {
                final FunctionDeclaration fdecl = (FunctionDeclaration) node;
                final StringBuilder header = new StringBuilder();
                header.append(CodeGenerator.generateModifiersList(fdecl.getModifiers()))
                    .append(TypeNames.INSTANCE.generate(fdecl.getDatatype()))
                    .append(' ')
                    .append(fdecl.getName().getData())
                    .append(CodeGenerator.generateParameterBlock(fdecl.getParameters()))
                    .append(" {");
                this.builder.print(header.toString());
                this.builder.increaseIndent();
                for (final Node item : fdecl.getBody().getChildrenList()) {
                    this.generate(item);
                }
                this.builder.decreaseIndent();
                this.builder.print("}");
            }
        );
        return gen;
    }

    /**
     * Initialises map with class member declarations.
     * @return A map
     */
    private Map<String, Generator> initStatements() {
        final Map<String, Generator> gen = new TreeMap<>();
        gen.put(
            "ExpressionStatement",
            node -> {
                final ExpressionStatement exprstmt = (ExpressionStatement) node;
                this.builder.print(
                    String.format(
                        "%s;",
                        Expressions.INSTANCE.generate(exprstmt.getExpression())
                    )
                );
            }
        );
        gen.put(
            "Synchronized",
            node -> {
                final Synchronized synchr = (Synchronized) node;
                final StringBuilder header = new StringBuilder(50);
                header.append("synchronized (")
                    .append(Expressions.INSTANCE.generate(synchr.getExpression()))
                    .append(") {");
                this.builder.print(header.toString());
                this.builder.increaseIndent();
                for (final Node item : synchr.getBody().getChildrenList()) {
                    this.generate(item);
                }
                this.builder.decreaseIndent();
                this.builder.print("}");
            }
        );
        gen.put(
            "VariableDeclaration",
            node -> {
                final VariableDeclaration vardecl = (VariableDeclaration) node;
                final StringBuilder code = new StringBuilder();
                code.append(CodeGenerator.generateModifiersList(vardecl.getModifiers()));
                final TypeName type = vardecl.getDatatype();
                if (type == null) {
                    code.append("Object");
                } else {
                    code.append(TypeNames.INSTANCE.generate(type));
                }
                code
                    .append(' ')
                    .append(CodeGenerator.generateDeclaratorsList(vardecl.getDeclarators()))
                    .append(';');
                this.builder.print(code.toString());
            }
        );
        return gen;
    }

    /**
     * Generates modifiers list.
     * @param block Modifiers block
     * @return Modifiers list
     */
    private static String generateModifiersList(final ModifierBlock block) {
        final StringBuilder code = new StringBuilder();
        if (block != null) {
            for (int index = 0; index < block.getChildCount(); index = index + 1) {
                final Modifier modifier = block.getModifier(index);
                code.append(modifier.getData()).append(' ');
            }
        }
        return code.toString();
    }

    /**
     * Generates declarators list from node.
     * @param list Node
     * @return Declarators list
     */
    private static String generateDeclaratorsList(final DeclaratorList list) {
        final StringBuilder code = new StringBuilder();
        for (int index = 0; index < list.getChildCount(); index = index + 1) {
            if (index > 0) {
                code.append(", ");
            }
            final Declarator declarator = list.getDeclarator(index);
            code.append(declarator.getName().getData());
            final Expression value = declarator.getValue();
            if (value != null) {
                code.append(" = ").append(Expressions.INSTANCE.generate(value));
            }
        }
        return code.toString();
    }

    /**
     * Generates extends block from node.
     * @param block Node
     * @return Extends block
     */
    private static String generateExtendsBlock(final ExtendsBlock block) {
        final StringBuilder code = new StringBuilder();
        if (block != null) {
            code.append(" extends ");
            for (int index = 0; index < block.getChildCount(); index = index + 1) {
                if (index > 0) {
                    code.append(", ");
                }
                final ClassType type = block.getClassType(index);
                code.append(TypeNames.INSTANCE.generate(type));
            }
        }
        return code.toString();
    }

    /**
     * Generates implements block from node.
     * @param block Node
     * @return Implements block
     */
    private static String generateImplementsBlock(final ImplementsBlock block) {
        final StringBuilder code = new StringBuilder();
        if (block != null) {
            code.append(" implements ");
            for (int index = 0; index < block.getChildCount(); index = index + 1) {
                if (index > 0) {
                    code.append(", ");
                }
                final ClassType type = block.getClassType(index);
                code.append(TypeNames.INSTANCE.generate(type));
            }
        }
        return code.toString();
    }

    /**
     * Generates parameter block from node.
     * @param block Node
     * @return Parameter block
     */
    private static String generateParameterBlock(final ParameterBlock block) {
        final StringBuilder code = new StringBuilder();
        if (block != null) {
            code.append('(');
            for (int index = 0; index < block.getChildCount(); index = index + 1) {
                if (index > 0) {
                    code.append(", ");
                }
                final Parameter parameter = block.getParameter(index);
                code.append(CodeGenerator.generateParameter(parameter));
            }
            code.append(')');
        }
        return code.toString();
    }

    /**
     * Generates parameter from node.
     * @param parameter Node
     * @return Parameter
     */
    private static String generateParameter(final Parameter parameter) {
        final StringBuilder code = new StringBuilder();
        code.append(CodeGenerator.generateModifiersList(parameter.getModifiers()))
            .append(TypeNames.INSTANCE.generate(parameter.getDatatype()))
            .append(' ')
            .append(parameter.getName().getData());
        return code.toString();
    }

    /**
     * Interface for generating Java source code for a specific expression.
     *
     * @since 0.1.2
     */
    private interface Generator {
        /**
         * Generates Java source code for a specific node.
         * @param node A node
         */
        void generate(Node node);
    }
}
