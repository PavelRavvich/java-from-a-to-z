package ru.pravvich.timeMoreClients;

import java.util.ArrayList;
import java.util.List;

class FullHouse {
    private int count = 1;

    TimeZoneCline getFullHouse(List<TimeZoneCline> timeZones) {
        List<TimeZoneCline> list = new ArrayList<>();

        for (int i = 0; (i + 1) < timeZones.size(); i++) {
            // получаю время двух поситителей. start=вошел; finish=ушел
            float startFst = timeZones.get(i).getStart();
            float finishFst = timeZones.get(i).getFinish();
            float startSnd = timeZones.get(i + 1).getStart();
            float finishSnd = timeZones.get(i + 1).getFinish();

            // тут сливаю зоны по общему времени. если общего нет то добавляю в лист обе
            if (startFst < finishSnd && startSnd < startFst) {
                // создается новая зона и у нее вызывается инклемент колличества
                // поситителей за этот промежуток времени.
                list.add(new TimeZoneCline(startFst, finishSnd).increment());
            } else if (startSnd < finishFst && startSnd > startFst) {
                list.add(new TimeZoneCline(startSnd, finishFst).increment());
            } else if (startFst == startSnd && finishFst < finishSnd) {
                list.add(new TimeZoneCline(startFst, finishFst).increment());
            } else if (startFst == startSnd && finishFst > finishSnd) {
                list.add(new TimeZoneCline(startSnd, finishSnd).increment());
            } else if (startFst == startSnd && finishFst == finishSnd) {
                list.add(new TimeZoneCline(startFst, finishFst).increment());
            } else if (finishFst < startSnd) {
                list.add(timeZones.get(i));
                list.add(timeZones.get(i + 1));
                this.count++;
            } else {
                list.add(timeZones.get(i + 1));
                list.add(timeZones.get(i));
                this.count++;
            }
        }

        if (list.size() == count) {
            TimeZoneCline result = list.get(0);
            for (TimeZoneCline t : list) {
                if (t.getAmountCline() > result.getAmountCline()) {
                    result = t;
                }
            }
            return result;
        }

        return getFullHouse(list);
    }
}
