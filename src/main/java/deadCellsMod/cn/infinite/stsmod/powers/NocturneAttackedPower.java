package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.character.DepravedCase;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class NocturneAttackedPower extends AbstractPower {

    public static final String BASE_ID = "deadCells:NocturneAttackedPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private static final float ATTACK_MULTIPLE = 2F;
    private static boolean ownerTurn = false;
    /*private boolean justApply;*/
    private int usedCardNum = 0;
    private int needUsedCard = 1;

    @Override
    public void atStartOfTurn() {
        ownerTurn = true;
        super.atStartOfTurn();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        ownerTurn = false;
        super.atEndOfTurn(isPlayer);
    }

    public NocturneAttackedPower(AbstractCreature owner, int amount){
        this.ID = BASE_ID;
        this.name = STRINGS.NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;
        /*this.justApply = true;*/
        ImgUtils.setPowerImg(this);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0]+amount+STRINGS.DESCRIPTIONS[1]+ATTACK_MULTIPLE+STRINGS.DESCRIPTIONS[2];
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (!(type == DamageInfo.DamageType.HP_LOSS)){
            return damage*ATTACK_MULTIPLE;
        }
        return damage;
    }


    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            usedCardNum++;
        }
        super.onUseCard(card, action);
    }

    /* @Override
            public void wasHPLost(DamageInfo info, int damageAmount) {
                if (info.type == DamageInfo.DamageType.NORMAL &&!(info.owner instanceof DepravedCase)){
                    if (!this.justApply){
                        this.flash();
                        addToBot(new ReducePowerAction(this.owner, this.owner, BASE_ID, 1));
                        return;
                    }
                    if (info.owner instanceof AbstractPlayer){
                        this.justApply = false;
                    }
                }
            }*/
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        /*System.out.println(ownerTurn);
        System.out.print(usedCardNum  +'\n');
        System.out.println(needUsedCard);*/
        if (info.type == DamageInfo.DamageType.NORMAL
                && info.owner!=null
                && !(info.owner instanceof DepravedCase)
                && !ownerTurn
                && usedCardNum >= needUsedCard) {
            this.flash();
            needUsedCard++;
            addToBot(new ReducePowerAction(this.owner, this.owner, BASE_ID, 1));
        }
        return super.onAttacked(info, damageAmount);
    }


/*@Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            addToBot(new ReducePowerAction(this.owner, this.owner, BASE_ID, 1));
        }
        return super.atDamageReceive(damage, type);
    }*/

}
