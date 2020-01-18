package org.example;

import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompositeStepsFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return null;
    }

    @Override
    public boolean canFindUsagesFor(final @NotNull PsiElement psiElement) {
        return true;
    }

    @Nullable
    @Override
    public String getHelpId(final @NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(final @NotNull PsiElement element) {
        return "toBeDefinedType";
    }

    @NotNull
    @Override
    public String getDescriptiveName(final @NotNull PsiElement element) {
        return "descriptiveName";
    }

    @NotNull
    @Override
    public String getNodeText(final @NotNull PsiElement element, final boolean useFullName) {
        return "nodeText";
    }
}