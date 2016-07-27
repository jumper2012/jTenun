package kerjapraktek.jtenun;

import java.util.ArrayList;

public class TenunData {

  public static String[] tenunNameArray = {"Bintang Maratur", "Harungguan", "Sibolang", "Ragi Idup", "RagiHotang I", "RagiHotang II", "Sadum", "Sitoluhunto" };

  public static ArrayList<Tenun> placeList() {
    ArrayList<Tenun> list = new ArrayList<>();
    for (int i = 0; i < tenunNameArray.length; i++) {
      Tenun tenun = new Tenun();
      tenun.name = tenunNameArray[i];
      tenun.imageName = tenunNameArray[i].replaceAll("\\s+", "").toLowerCase();
      if (i == 2 || i == 5) {
        tenun.isFav = true;
      }
      list.add(tenun);
    }
    return (list);
  }
}
