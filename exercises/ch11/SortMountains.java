import java.util.*;

public class SortMountains {
public static void main(String [] args) {
new SortMountains().go();
}
public void go() {
List<Mountain> mountains = new ArrayList<>();
mountains.add(new Mountain("Longs", 14255));
mountains.add(new Mountain("Elbert", 14433));
mountains.add(new Mountain("Maroon", 14156));
mountains.add(new Mountain("Castle", 14265));
System.out.println("as entered:\n" + mountains);
mountains.sort((one, two)-> one.getName().compareTo(two.getName()));
System.out.println("by name:\n" + mountains);
mountains.sort((one, two)-> two.getHeight() - one.getHeight());
System.out.println("by height:\n" + mountains);

}
}

class Mountain {
private int height;
private String name;

Mountain(String nameValue, int heightValue) {
this.height = heightValue;
this.name = nameValue;
}
public int getHeight() {
return height;
}
public String getName() {
return name;
}

public String toString() {
  return name + " " + height;
}
}
