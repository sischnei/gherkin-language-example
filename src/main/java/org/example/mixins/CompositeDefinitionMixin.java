package org.example.mixins;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.IncorrectOperationException;
import org.example.psi.CompositeStepsCompositeDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class CompositeDefinitionMixin extends ASTWrapperPsiElement implements CompositeStepsCompositeDefinition, PsiNameIdentifierOwner {

    public CompositeDefinitionMixin(@NotNull final ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return this;
    }

    @Override
    public String getName() {
        return getText();
    }

    @Override
    public PsiElement setName(@NotNull final String name) {
        throw new IncorrectOperationException("Rename is not supported!");
    }
}
