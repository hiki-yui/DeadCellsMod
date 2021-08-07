package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class BurnsPower extends AbstractPower {

    public static final String BASE_ID = "deadCells:BurnsPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private static final float DMA_MULTI = 0.25F;

    public BurnsPower(AbstractCreature owner,int amount){
        this.name = STRINGS.NAME;
        this.ID = BASE_ID;
        this.amount = amount;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        ImgUtils.setPowerImg(this);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        AbstractCreature powerTarget = this.owner;
        int powerAmount = this.amount;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0;i<powerAmount;i++) {
                    addToBot(new DamageAction(powerTarget,
                            new DamageInfo(powerTarget,1),
                            AbstractGameAction.AttackEffect.FIRE,true));
                }
                addToBot(new ReducePowerAction(powerTarget,powerTarget,BASE_ID,powerAmount));
                this.isDone = true;
            }
        });
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + ((int)(DMA_MULTI*100))  +
                STRINGS.DESCRIPTIONS[1] + (this.amount/3) +STRINGS.DESCRIPTIONS[2] +
                amount + STRINGS.DESCRIPTIONS[3];
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            damage = damage+(damage * DMA_MULTI + (float) (this.amount / 3));
        }
        return super.atDamageFinalReceive(damage, type);
    }
}
