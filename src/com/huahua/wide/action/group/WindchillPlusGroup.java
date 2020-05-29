package com.huahua.wide.action.group;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;
import org.jetbrains.annotations.NotNull;

public class WindchillPlusGroup extends DefaultActionGroup {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        super.actionPerformed(anActionEvent);
    }

    @Override
    public void update(@NotNull AnActionEvent anActionEvent) {
        Presentation presentation = anActionEvent.getPresentation();
        presentation.setVisible(true);
        presentation.setText("Windchill Plus");

    }
}