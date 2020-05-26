package com.huahua.wide.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class ArthasCliGeneraAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);

        VirtualFile[] virtualFiles = anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY);


        Messages.showMessageDialog(project, "TestTitle", "Hello IDEA", Messages.getInformationIcon());
    }

    @Override
    public void update(AnActionEvent anActionEvent) {
        anActionEvent.getPresentation().setVisible(true);
    }

}
