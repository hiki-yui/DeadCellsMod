package deadCellsMod.cn.infinite.stsmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GainAllEnemyBleedingPowerAction extends AbstractGameAction {
    private AbstractCreature source;

    public GainAllEnemyBleedingPowerAction(AbstractCreature source, int baseAmount){
        this.amount = baseAmount;
        this.source = source;
    }

    @Override
    public void update() {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster!=null && !monster.isDying){
                if (!source.hasPower("deadCells:BleedingSpreadPower")) {
                    addToBot(new GainBleedingPowerAction(source, monster,amount));
                }
            }
        }
        if (source.hasPower("deadCells:BleedingSpreadPower")) {
            new GainBleedingPowerAction(source,AbstractDungeon.getRandomMonster(),amount).update();
        }
        isDone = true;
    }
}
