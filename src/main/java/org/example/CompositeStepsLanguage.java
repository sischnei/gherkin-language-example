package org.example;

import com.intellij.lang.Language;

public final class CompositeStepsLanguage extends Language {

    public static final CompositeStepsLanguage INSTANCE = new CompositeStepsLanguage();

    private CompositeStepsLanguage() {
        super("CompositeStep");
    }
}
