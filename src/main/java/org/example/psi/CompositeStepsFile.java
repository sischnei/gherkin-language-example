package org.example.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.example.CompositeStepsFileType;
import org.example.CompositeStepsLanguage;
import org.jetbrains.annotations.NotNull;

public class CompositeStepsFile extends PsiFileBase {

    public CompositeStepsFile(final @NotNull FileViewProvider viewProvider) {
        super(viewProvider, CompositeStepsLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return CompositeStepsFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Composite Steps File";
    }
}