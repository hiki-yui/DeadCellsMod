package deadCellsMod.cn.infinite.stsmod;


import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.jar.JarFile;

import static basemod.BaseMod.addCard;

public class Test {

    public static void main(String[] args) {
       /* ArrayList<String> attacks = new ArrayList<>();
        attacks.add("123");
        attacks.add("124");
        for (String s:attacks){
            System.out.println(s);
        }
        attacks.remove(1);
        for (String s:attacks){
            System.out.println(s);
        }*/

        /*File file = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                .getResource("deadCellsMod/cn/infinite/stsmod/cards/")).getFile());
        for (File f : Objects.requireNonNull(file.listFiles())){
            try {
                Class c = Class.forName("deadCellsMod.cn.infinite.stsmod.cards."+f.getName().substring(0,f.getName().length()-6));
                System.out.println(c.newInstance().toString());
                addCard((AbstractCard)c.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            System.out.println();
        }*/
        /*String jarPath = Test.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        System.out.println(jarPath);
        File file = new File(jarPath+"deadCellsMod/cn/infinite/stsmod/cards/");
        System.out.println(file);
        for (File f : file.listFiles()){
            System.out.println(f.getName());
        }
        int firstIndex = jarPath.lastIndexOf(System.getProperty("path.separator")) + 1;
        int lastIndex = jarPath.lastIndexOf(File.separator) + 1;
        *//*System.out.println(firstIndex);
        System.out.println(lastIndex);*//*
        jarPath = jarPath.substring(firstIndex, lastIndex);
*/
        System.out.println("123".length());
    }
}

/*BaseMod.addCard(new UnKnowCard());*/