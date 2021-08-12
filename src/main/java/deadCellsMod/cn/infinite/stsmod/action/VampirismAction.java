package deadCellsMod.cn.infinite.stsmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;

public class VampirismAction extends AbstractGameAction {

    private AbstractCreature owner;
    private int startNum;

    public VampirismAction(AbstractCreature owner,int startNum) {
        this.owner = owner;
        this.startNum = startNum;
    }

    @Override
    public void update() {
        addToBot(new WaitAction(0.5F));
        int healNum = 0;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters){
            if (m.hasPower("deadCells:BleedingPower")) {
                for (int i = 0; i < m.getPower("deadCells:BleedingPower").amount && i<20;i++){
                    addToBot(new VFXAction(new FlyingOrbEffect(m.hb.cX, m.hb.cY)));
                }
                healNum += m.getPower("deadCells:BleedingPower").amount;
            }
            for (int i = 0;i<startNum;i++){
                addToBot(new VFXAction(new FlyingOrbEffect(m.hb.cX, m.hb.cY)));
            }
            healNum += startNum;
        }
        addToBot(new HealAction(owner,owner,healNum));
        isDone = true;
    }
}
