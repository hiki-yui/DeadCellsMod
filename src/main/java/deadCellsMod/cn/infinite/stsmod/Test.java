package deadCellsMod.cn.infinite.stsmod;


import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        ArrayList<String> attacks = new ArrayList<>();
        attacks.add("123");
        attacks.add("124");
        for (String s:attacks){
            System.out.println(s);
        }
        attacks.remove(1);
        for (String s:attacks){
            System.out.println(s);
        }
      /*  File file = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                .getResource("deadCellsMod/cn/infinite/stsmod/cards/")).getFile());
        for (File f : Objects.requireNonNull(file.listFiles())){
            try {
                Class c = Class.forName(f.getName().substring(0,f.getName().length()-6));
                addCard((AbstractCard)c.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }*/

    }
}

/*BaseMod.addCard(new UnKnowCard());*/