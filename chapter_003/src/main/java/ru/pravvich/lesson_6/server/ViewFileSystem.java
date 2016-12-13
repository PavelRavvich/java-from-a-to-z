package ru.pravvich.lesson_6.server;

import java.io.File;

import static java.lang.String.format;

class ViewFileSystem {

    // метод для просмотра содержания каталога
    String[] seeCatalog(File catalog) {
        if (catalog.isDirectory()) {
            String[] list = catalog.list();
            assert list != null;
            for (int i = 0; i != list.length; i++) {
                if (new File(format("%s/%s",
                        catalog.getAbsolutePath(), list[i])).isDirectory()
                        ) {

                    list[i] = format("%s/", list[i]);
                }
            }

            String[] listWithInfo = new String[list.length + 3];
            listWithInfo[0] = "В каталоге :";
            listWithInfo[1] = format("%s/", catalog.getAbsolutePath());
            listWithInfo[2] = "Содержаться объекты :";

            for (int i = 3, j = 0; i != listWithInfo.length; i++, j++) {
                listWithInfo[i] = list[j];
            }

            return listWithInfo;
        } else {
            return new String[0];
        }
    }

    // метод для перемещения из каталога в нижний каталог
    File moveDown(File thisPosition, String newPosition) {
        File target = new File(format("%s/%s", thisPosition.getAbsolutePath(), newPosition));
        boolean existsSub = target.exists();
        System.out.println(format("%s/%s", thisPosition.getAbsolutePath(), newPosition));
        if (existsSub && thisPosition.exists() && thisPosition.isDirectory()) {
            return new File(format("%s/%s", thisPosition.getAbsolutePath(), newPosition));
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
        return new File(new String(sb));
    }
}