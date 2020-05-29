package com.huahua.wide.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import sun.awt.SunToolkit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ArthasCliDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTextField wchomeText;
    private JButton generaCliButton;
    private JEditorPane outCliPanel;
    private JTextPane selectFilesPanel;

    private static final String mccli = "mc -c 18b4aac2 --encoding UTF-8 -d wcHome/codebase filepath";
    private static final String redefine = "redefine -c 18b4aac2 classpath";

    public ArthasCliDialog(AnActionEvent event) {
        initPane();

        wchomeText.setText("D:/PTC/Windchill_11.0/Windchill");

        ArrayList<String> selectFilePath = getSelectFilePath(event);
        selectFilesPanel.setText(StringUtils.join(selectFilePath, "\n"));

        ArrayList<String> file_cli_list = buildArthasCli(selectFilePath, event);
        outCliPanel.setText(StringUtils.join(file_cli_list, "\n"));

        generaCliButton.addActionListener(e -> {
            onGeneraCliButton(event);
        });
        buttonCancel.addActionListener(e -> {
            dispose();
        });
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        initDialog(event);
    }

    private void initDialog(AnActionEvent event) {
        super.setSize(800, 400);
        super.setUndecorated(true);
        setLocation(event);
        super.setVisible(true);
        super.requestFocus();
    }

    private ArrayList<String> getSelectFilePath(AnActionEvent event) {
        ArrayList<VirtualFile> patcherFiles = Lists.newArrayList();
        ArrayList<String> pathList = Lists.newArrayList();

        VirtualFile[] virtualFileList = event.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY);
        for (VirtualFile virtualFile : virtualFileList) {
            getChildVirtualFiles(virtualFile, patcherFiles);
        }

        patcherFiles.forEach(item -> pathList.add(item.getPath()));

        return pathList;
    }

    private ArrayList<String> buildArthasCli(ArrayList<String> selectFilePath, AnActionEvent event) {
        ArrayList<String> file_cli_list = Lists.newArrayList();

        String basePath = event.getProject().getBasePath();

        for (String filePath : selectFilePath) {
            String wc_src_file = StringUtils.replace(filePath, basePath, wchomeText.getText());
            String wc_codebase_file = StringUtils.replace(wc_src_file, "src", "codebase");
            wc_codebase_file = StringUtils.replace(wc_src_file, "java", "class");

            String file_mc_cli = StringUtils.replace(mccli, "wcHome", wchomeText.getText());
            file_mc_cli = StringUtils.replace(file_mc_cli, "filepath", wc_src_file);
            file_cli_list.add(file_mc_cli);

            String file_redefine_cli = StringUtils.replace(redefine, "classpath", wc_codebase_file);
            file_cli_list.add(file_redefine_cli);
        }

        return file_cli_list;
    }

    private void getChildVirtualFiles(VirtualFile virtualFile, ArrayList<VirtualFile> patcherFiles) {
        if (virtualFile.isDirectory()) {
            for (VirtualFile file : Arrays.asList(virtualFile.getChildren())) {
                getChildVirtualFiles(file, patcherFiles);
            }
        } else {
            patcherFiles.add(virtualFile);
        }
    }

    /**
     * 初始化 Pane 内容
     */
    private void initPane() {
        setTitle("Arthas Cli");
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

    private void onGeneraCliButton(AnActionEvent event) {
        System.out.println("GeneraCli");
        ArrayList<String> selectFilePath = getSelectFilePath(event);
        ArrayList<String> file_cli_list = buildArthasCli(selectFilePath, event);

        outCliPanel.setText(StringUtils.join(file_cli_list, "\n"));

    }

}
