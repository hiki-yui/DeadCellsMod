package deadCellsMod.cn.infinite.stsmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import deadCellsMod.cn.infinite.stsmod.cards.FireBlast;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;

import java.util.Arrays;

import static deadCellsMod.cn.infinite.stsmod.utils.CardUtils.checkX;

public class FireBlastAction extends AbstractGameAction {


    private AbstractCard father;
    private AbstractPlayer source;
    private boolean freeToPlayOnce;
    private int energyOnUse;
    private int[] baseDamage = new int[AbstractDungeon.getMonsters().monsters.size()];
    private int burnNum;

    public FireBlastAction(AbstractPlayer source, int baseDamage, int energyOnUse, boolean freeToPlayOnce, int burnNum, AbstractCard father){
        this.source = source;
        this.energyOnUse = energyOnUse;
        this.freeToPlayOnce = freeToPlayOnce;
        Arrays.fill(this.baseDamage, baseDamage);
        this.burnNum = burnNum;
        this.father = father;
    }

    @Override
    public void update() {
        int effect = checkX(this.energyOnUse, this.source);
        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        int[] baseDma = CardUtils.forDamageAllEnemies(father.damage);
                        addToTop(new DamageAllEnemiesAction(source, baseDma, DamageInfo.DamageType.NORMAL, AttackEffect.FIRE, true));
                        isDone = true;
                    }
                });
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
