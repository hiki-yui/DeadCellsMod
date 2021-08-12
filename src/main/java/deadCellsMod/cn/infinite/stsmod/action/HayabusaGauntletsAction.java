package deadCellsMod.cn.infinite.stsmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class HayabusaGauntletsAction extends AbstractGameAction {

    private int damage;
    private int heavyDamage;
    private boolean canHeavyD;
    private int multiNum;

    public HayabusaGauntletsAction(AbstractCreature source,AbstractCreature target, int damage, int heavyDamage,boolean canHeavyD,int multiNum){
        this.source = source;
        this.target = target;
        this.damage = damage;
        this.heavyDamage = heavyDamage;
        this.canHeavyD = canHeavyD;
        this.multiNum = multiNum;
    }

    @Override
    public void update() {
        AbstractCreature thisTarget = this.target;
        AbstractCreature thisSource = this.source;

        addToTop(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (thisTarget.currentHealth < (thisTarget.maxHealth * 0.4) && canHeavyD) {
                    for (int i = 0; i < multiNum; i++) {
                        addToBot(new DamageAction(thisTarget, new DamageInfo(thisSource,heavyDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    }
                }
                this.isDone = true;
            }
        });
        this.isDone = true;
    }
}
