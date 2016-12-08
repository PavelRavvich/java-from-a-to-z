package ru.pravvich.lesson_6.server;

import java.io.File;

import static java.lang.String.format;

class ViewFileSystem {
    static String path = "/Users/pavel/Desktop/test";

    // метод для просмотра содержания каталога
    void seeCatalog(File catalog) {
        if (catalog.isDirectory()) {
            System.out.println(catalog.getAbsolutePath());
            for (String item : catalog.list()) {
                if (new File(format("%s/%s", catalog.getAbsolutePath(), item)).isDirectory()) {
                    System.out.println(format("%s%s", item, "/"));
                } else {
                    System.out.println(item);
                }
            }
        }
    }

    // метод для перемещения из каталога в нижний каталог
    File moveDown(File thisPosition, String newPosition) {
        File target = new File(format("%s/%s", thisPosition.getAbsolutePath(), newPosition));
        boolean existsSub = target.exists();
        System.out.println(format("%s/%s", thisPosition.getAbsolutePath(), newPosition));
        if (existsSub && thisPosition.exists() && thisPosition.isDirectory()) {
            return new File(path = format("%s/%s", thisPosition.getAbsolutePath(), newPosition));
        } else {
            System.out.println("Объект чтения не существует.");
            return new File(thisPosition.getAbsolutePath());
        }
    }

    // метод для перемещения из каталога в верхний каталог /a/b/c -> /a/b
    File moveUp(File dir) {
        String[] arrPath = dir.getAbsolutePath().split("");
        int indexUnwanted = 0;
        for (int i = (arrPath.length - 1); i >= 0; i--) {
            if (!arrPath[i].equals("/")) {
                indexUnwanted++;
            } else {
                // итерируемся до первого слеша с конца и break
                indexUnwanted++;
                break;
            }
        }

        // добавляем без последнего /папка
        String[] res = new String[arrPath.length - indexUnwanted];
        System.arraycopy(arrPath, 0, res, 0, arrPath.length - indexUnwanted);
        StringBuilder sb = new StringBuilder();
        for (String re : res) {
            sb.append(re);
        }
        return new File(path = new String(sb));
    }
}