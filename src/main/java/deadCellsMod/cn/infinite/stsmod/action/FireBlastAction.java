package deadCellsMod.cn.infinite.stsmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static deadCellsMod.cn.infinite.stsmod.utils.CardUtils.checkX;

public class FireBlastAction extends AbstractGameAction {

    private AbstractPlayer source;
    private boolean freeToPlayOnce;
    private int energyOnUse;
    private int[] baseDamage = new int[AbstractDungeon.getMonsters().monsters.size()];
    private int burnNum;

    public FireBlastAction(AbstractPlayer source,int baseDamage,int energyOnUse,boolean freeToPlayOnce,int burnNum){
        this.source = source;
        this.energyOnUse = energyOnUse;
        this.freeToPlayOnce = freeToPlayOnce;
        for (int i=0;i<this.baseDamage.length;i++){
            this.baseDamage[i] = baseDamage;
        }
        this.burnNum = burnNum;
    }

    @Override
    public void update() {
        int effect = checkX(this.energyOnUse, this.source);
        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                addToBot(new DamageAllEnemiesAction(source, baseDamage, DamageInfo.DamageType.NORMAL, AttackEffect.FIRE, true));
                for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    addToBot(new GainBurnsPowerAction(monster, source, burnNum));
                }
            }
            if (!this.freeToPlayOnce) {
                this.source.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
