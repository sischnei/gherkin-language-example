package org.example;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleServiceManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.example.psi.CompositeStepsCompositeDefinition;
import org.example.psi.CompositeStepsFile;
import org.jetbrains.annotations.NotNull;

public class CompositeStepDefinitionLookupService {

    private final Module intellijModule;

    public CompositeStepDefinitionLookupService(final Module intellijModule) {
        this.intellijModule = intellijModule;
    }

    public static CompositeStepDefinitionLookupService getInstance(final @NotNull Module module) {
        return ModuleServiceManager.getService(module, CompositeStepDefinitionLookupService.class);
    }

    List<StepDefinition> findAllStepDefinitions() {
        // The build search scope allows to find a list of all ".steps" files referenced by the IDEA project, including files on disk as well as
        // files contained in jars. The "false" parameter defines whether tests shall be included, which shouldn't be the case.
        GlobalSearchScope moduleWithDependenciesAndLibrariesScope = intellijModule.getModuleWithDependenciesAndLibrariesScope(false);
        GlobalSearchScope moduleContentScope = intellijModule.getModuleContentScope();
        GlobalSearchScope globalSearchScope = moduleWithDependenciesAndLibrariesScope.uniteWith(moduleContentScope);

        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(CompositeStepsFileType.INSTANCE, globalSearchScope);
        List<StepDefinition> compositeStepDefinitions = new ArrayList<>();
        for (VirtualFile virtualFile : virtualFiles) {
            CompositeStepsFile stepsFile = (CompositeStepsFile) PsiManager.getInstance(intellijModule.getProject()).findFile(virtualFile);
            if (stepsFile != null) {
                List<StepDefinition> compositeDefinitions = Arrays.stream(stepsFile.getChildren())
                                                                  .map(comp -> PsiTreeUtil.getChildrenOfType(
                                                                          comp, CompositeStepsCompositeDefinition.class))
                                                                  .filter(Objects::nonNull)
                                                                  .map(children -> children[0])
                                                                  .map(StepDefinition::fromCompositeStepDefinition)
                                                                  .collect(Collectors.toList());

                compositeStepDefinitions.addAll(compositeDefinitions);
            }
        }
        return compositeStepDefinitions;
    }
}
