package org.example;

import com.intellij.psi.PsiElement;
import org.example.psi.CompositeStepsCompositeDefinition;
import org.jbehave.core.steps.StepType;

public class StepDefinition {

    private final StepType stepType;
    private final String textWithoutStepType;
    private final PsiElement declarationPsiElement;

    public StepDefinition(final StepType stepType, final String textWithoutStepType, final PsiElement declarationPsiElement) {
        this.stepType = stepType;
        this.textWithoutStepType = textWithoutStepType;
        this.declarationPsiElement = declarationPsiElement;
    }

    public static StepDefinition fromCompositeStepDefinition(final CompositeStepsCompositeDefinition compositeDefinition) {
        String keyword = compositeDefinition.getGwtKeyword().getText();
        StepType stepType = StepType.valueOf(keyword.toUpperCase());
        return new StepDefinition(stepType, compositeDefinition.getCompositeDefinitionWithoutKeyword().getText(), compositeDefinition);
    }

    public StepType getStepType() {
        return stepType;
    }

    public String getTextWithoutStepType() {
        return textWithoutStepType;
    }

    public PsiElement getDeclarationPsiElement() {
        return declarationPsiElement;
    }
}
