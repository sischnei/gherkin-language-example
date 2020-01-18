package org.example;

import com.intellij.openapi.fileTypes.LanguageFileType;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompositeStepsFileType extends LanguageFileType {
    public static final CompositeStepsFileType INSTANCE = new CompositeStepsFileType();

    private CompositeStepsFileType() {
        super(CompositeStepsLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Composite Steps";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Composite Steps definitions";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "steps";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return null;
    }
}
