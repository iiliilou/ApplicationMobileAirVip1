package com.example.applicationmobileairvip.util;

import com.example.applicationmobileairvip.model.Vol;

import java.util.ArrayList;
import java.util.List;

public class VolReserveManager {

    private static final List<Vol> volsReserves = new ArrayList<>();

    public static void ajouterVol(Vol vol) {
        volsReserves.add(vol);
    }

    public static List<Vol> getVolsReserves() {
        return volsReserves;
    }

    public static void clear() {
        volsReserves.clear();
    }
}
