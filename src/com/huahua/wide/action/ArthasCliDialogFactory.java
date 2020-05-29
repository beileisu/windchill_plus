package com.huahua.wide.action;

import com.intellij.openapi.actionSystem.AnActionEvent;

public class ArthasCliDialogFactory {
    public static ArthasCliDialog getInstance(AnActionEvent event) {
        ArthasCliDialog arthasCliDialog = new ArthasCliDialog(event);
        return arthasCliDialog;
    }
}
