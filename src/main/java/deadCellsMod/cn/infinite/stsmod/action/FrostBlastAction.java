package deadCellsMod.cn.infinite.stsmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import deadCellsMod.cn.infinite.stsmod.powers.FrostbitePower;

import static deadCellsMod.cn.infinite.stsmod.utils.CardUtils.checkX;

public class FrostBlastAction extends AbstractGameAction {
    private AbstractPlayer source;
    private boolean freeToPlayOnce;
    private int energyOnUse;
    private int block;
    private int frostNum;

    public FrostBlastAction(AbstractPlayer source,int block,int energyOnUse,boolean freeToPlayOnce,int frostNum){
        this.source = source;
        this.energyOnUse = energyOnUse;
        this.freeToPlayOnce = freeToPlayOnce;
        this.block = block;
        this.frostNum = frostNum;
    }

    @Override
    public void update() {
        int effect = checkX(this.energyOnUse, this.source);
        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
               addToBot(new GainBlockAction(source,source,this.block,true));
               for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
                   addToBot(new ApplyPowerAction(monster,source,new FrostbitePower(monster,source,frostNum),frostNum,true));
               }
            }
            if (!this.freeToPlayOnce) {
                this.source.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }


}
