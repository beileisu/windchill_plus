package com.huahua.wide.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import sun.awt.SunToolkit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ArthasCliDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTextField wchomeText;
    private JButton generaCliButton;
    private JEditorPane outCliPanel;
    private JTextPane selectFilesPanel;

    public ArthasCliDialog(AnActionEvent event) {
        initPane();
        generaCliButton.addActionListener(e -> {
            onGeneraCliButton();
            dispose();
        });
        buttonCancel.addActionListener(e -> {
            onCancel();
            dispose();
        });
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        initDialog(event);
    }

    private void initDialog(AnActionEvent event) {
        super.setSize(600, 400);
        super.setUndecorated(true);
        setLocation(event);
        super.setVisible(true);
        super.requestFocus();
    }

    /**
     * 初始化 Pane 内容
     */
    private void initPane() {
        setTitle("Demo");
        setContentPane(contentPane);
        setModalityType(ModalityType.APPLICATION_MODAL);
        getRootPane().setDefaultButton(buttonCancel);

    }

    /**
     * 不同屏幕获取对话框居中位置
     */
    private void setLocation(AnActionEvent event) {
        // 获取当前程序组件
        Component component = event.getData(PlatformDataKeys.CONTEXT_COMPONENT);
        //获取当前组件窗口信息
        Window componentWindow = SunToolkit.getContainingWindow(component);
        super.setLocationRelativeTo(componentWindow);
    }

    private void onGeneraCliButton() {
        System.out.println("GeneraCli");
        dispose();
    }

    private void onCancel() {
        System.out.println("Cancel");
        dispose();
    }

}
