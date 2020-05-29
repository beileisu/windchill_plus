package com.huahua.wide.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import org.jetbrains.annotations.NotNull;

public class ArthasCliGeneraAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        ArthasCliDialogFactory.getInstance(anActionEvent);
    }

    @Override
    public void update(AnActionEvent anActionEvent) {
        Presentation presentation = anActionEvent.getPresentation();
        presentation.setText("Arthas Cli");
        presentation.setVisible(true);
    }

}
