package com.xiaolong.rootapptool.utils;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class Shell {
    private static Shell shell;
    private Process process;

    public static Shell getInstance() {
        if (shell == null) shell = new Shell();
        return shell;
    }

    private Shell() {
        su();
    }

    public void su() {
        try {
            process = Runtime.getRuntime().exec("su");
        } catch (Exception e) {
            L.e(e.getMessage());
        }
    }

    public void execShellCmd(String cmd) {
        try {
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream);
            dataOutputStream.writeBytes(cmd);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            L.e(t.getMessage());
        }
    }
}
