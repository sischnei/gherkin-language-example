package org.example.psi;

import com.intellij.psi.tree.IElementType;
import org.example.CompositeStepsLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CompositeStepsElementType extends IElementType {

    public CompositeStepsElementType(final @NotNull @NonNls String debugName) {
        super(debugName, CompositeStepsLanguage.INSTANCE);
    }
}
