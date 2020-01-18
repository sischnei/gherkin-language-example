package org.example;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.example.parser.CompositeStepsParser;
import org.example.psi.CompositeStepsFile;
import org.example.psi.CompositeStepsTypes;
import org.jetbrains.annotations.NotNull;

public class CompositeStepsParserDefinition implements com.intellij.lang.ParserDefinition {
    private static final TokenSet WHITE_SPACES = TokenSet.create(com.intellij.psi.TokenType.WHITE_SPACE);
    private static final IFileElementType FILE = new IFileElementType(CompositeStepsLanguage.INSTANCE);

    @NotNull
    @Override
    public com.intellij.lexer.Lexer createLexer(final com.intellij.openapi.project.Project project) {
        return new CompositeStepsLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public com.intellij.lang.PsiParser createParser(final com.intellij.openapi.project.Project project) {
        return new CompositeStepsParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public PsiFile createFile(final com.intellij.psi.FileViewProvider viewProvider) {
        return new CompositeStepsFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(final ASTNode left, final ASTNode right) {
        return SpaceRequirements.MUST_LINE_BREAK;
    }

    @NotNull
    @Override
    @SuppressWarnings("findbugs:RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE")
    public com.intellij.psi.PsiElement createElement(final ASTNode node) {
        return CompositeStepsTypes.Factory.createElement(node);
    }
}
