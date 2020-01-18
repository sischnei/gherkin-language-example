package org.example;

import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveResult;
import org.example.psi.CompositeStepsStep;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompositeDefinitionReference extends PsiReferenceBase<CompositeStepsStep> implements PsiPolyVariantReference {

    private static final RegexPrefixCapturingPatternParser PARSER = new RegexPrefixCapturingPatternParser();

    /**
     * Ctor.
     *
     * @param element   the source of the reference
     * @param textRange the range (relative) within the source that should be highlighted in the IDE.
     */
    public CompositeDefinitionReference(final @NotNull CompositeStepsStep element, final TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(final boolean incompleteCode) {
        CompositeStepDefinitionLookupService index = CompositeStepDefinitionLookupService.getInstance(ModuleUtilCore.findModuleForPsiElement(myElement));
        String myStepText = myElement.getStepText().getText();
        return index.findAllStepDefinitions()
                    .stream()
                    .filter(stepDefinition -> PARSER.parseStep(stepDefinition.getStepType(), stepDefinition.getTextWithoutStepType()).matches(myStepText))
                    .map(stepDefinition -> new PsiElementResolveResult(stepDefinition.getDeclarationPsiElement()))
                    .toArray(ResolveResult[]::new);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }
}