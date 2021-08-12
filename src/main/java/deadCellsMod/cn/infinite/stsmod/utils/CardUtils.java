package deadCellsMod.cn.infinite.stsmod.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class CardUtils {

    public static int checkX(int energyOnUse, AbstractPlayer source) {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }
        if (source.hasRelic("Chemical X")) {
            effect += 2;
            source.getRelic("Chemical X").flash();
        }
        return effect;
    }

    public static int getRandomItemFromList(ArrayList<AbstractCard> cardList){
        //放1个null防止random出现异常(至少要在两个数里随机)//应该不会有人把没有元素的数组放进来随机
        cardList.add(null);
        int rnd = AbstractDungeon.cardRng.random(cardList.size() - 1);
        long lastTime = System.currentTimeMillis();
        while(cardList.get(rnd) == null){
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime >= 60000){
                throw new RuntimeException("请检查数组是否存在合适的元素");
            }
            rnd = AbstractDungeon.cardRng.random(cardList.size() - 1);
        }
        return rnd;
    }

    public static int[] forDamageAllEnemies(int damage){
        int mo = 0;
        for (AbstractMonster m:AbstractDungeon.getMonsters().monsters){
            mo++;
        }
        int[] damageAll = new int[mo];
        for (int i = 0;i<damageAll.length;i++){
            damageAll[i] = damage;
        }
        return  damageAll;
    }
}
