package ru.pravvich.lesson_7PlusCommand.command;

import ru.pravvich.lesson_7PlusCommand.Find;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.format;

public class RecordResult implements Command {

    @Override
    public String getName() {
        return "-o";
    }

    @Override
    public void invoke(Scanner scanner) {
        if (Find.getTarget() != null) {
            System.out.println(HelpText.NAMEDIRFORSAVE.getText());
            String path = scanner.nextLine();
            System.out.println(HelpText.ENTERFILENAME.getText());
            String name = scanner.nextLine();
            // получаем каталог куда сохранять
            if (this.exist(path, name)) {
                this.write(Find.getTarget(), format("%s%s",path,name));
                System.out.println(format("%s%s%s",HelpText.RECORDRESULT.getText(),path,name));
            } else {
                System.out.println(HelpText.ACTIONNOEXSIST.getText());
            }
        }
    }

    private boolean exist(String path, String name) {
        String[] pathArr = path.split("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i != (pathArr.length - 1); i++) {
            sb.append(pathArr[i]);
        }
        String dir = new String(sb);
        //проверяем существует ли директория && проверяем нет ли уже такого файла
        return new File(dir).isDirectory() && !(new File(format("%s%s", path, name)).exists());
    }

    // пишем текст в файл
    private void write(ArrayList<String> paths, String repo) {
        try (FileOutputStream out = new FileOutputStream(repo)) {
            for (String path : paths) {
                out.write(format("%s\n",path).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
