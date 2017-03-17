package ru.pravvich.timeMoreClients;

import java.util.ArrayList;
import java.util.List;

class FullHouse {
    TimeZoneCline getFullHouse(List<TimeZoneCline> timeZones) {
        List<TimeZoneCline> list = new ArrayList<>();

        for (int i = 0; (i + 1) < timeZones.size(); i++) {
            TimeZoneCline collective = getCollectiveZone(
                    timeZones.get(i), timeZones.get(i + 1));

            if (collective.getStart() != 0 &&
                    collective.getFinish() != 0) {
                list.add(collective);
            }
        }

        if (list.size() == 1) {
            return list.get(0);
        }

        return getFullHouse(list);
    }

    private TimeZoneCline getCollectiveZone(TimeZoneCline fst, TimeZoneCline snd) {
        float startFst = fst.getStart();
        float finishFst = fst.getFinish();

        float startSnd = snd.getStart();
        float finishSnd = snd.getFinish();

        if (startFst < finishSnd && startSnd < startFst) {
            return new TimeZoneCline(startFst, finishSnd);
        } else if (startSnd < finishFst && startSnd > startFst) {
            return new TimeZoneCline(startSnd, finishFst);
        } else if (startFst == startSnd && finishFst < finishSnd) {
            return new TimeZoneCline(startFst, finishFst);
        } else if (startFst == startSnd && finishFst > finishSnd) {
            return new TimeZoneCline(startSnd, finishSnd);
        } else if (startFst == startSnd && finishFst == finishSnd) {
            return new TimeZoneCline(startFst, finishFst);
        }

        return new TimeZoneCline(0f ,0f);
    }
}
