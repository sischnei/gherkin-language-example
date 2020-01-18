package org.example.mixins;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import org.example.CompositeDefinitionReference;
import org.example.psi.CompositeStepsStep;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("squid:MaximumInheritanceDepth")
public abstract class StepMixin extends ASTWrapperPsiElement implements CompositeStepsStep {

    /**
     * Ctor.
     *
     * @param node the implementing {@link ASTNode}
     */
    public StepMixin(@NotNull final ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        return new CompositeDefinitionReference(this, new TextRange(0, getTextLength()));
    }
}
