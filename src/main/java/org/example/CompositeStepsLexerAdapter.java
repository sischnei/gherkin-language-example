package org.example;

import com.intellij.lexer.FlexAdapter;

public class CompositeStepsLexerAdapter extends FlexAdapter {

    public CompositeStepsLexerAdapter() {
        super(new CompositeStepsLexer(null));
    }
}
