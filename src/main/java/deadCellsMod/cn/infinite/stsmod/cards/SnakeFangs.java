package deadCellsMod.cn.infinite.stsmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class SnakeFangs extends DeadCellsCard{
    public static final String BASE_ID = "deadCells:SnakeFangs";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public SnakeFangs(){
        super(BASE_ID,STRINGS.NAME,"img/card/SnakeFangs.png",1,STRINGS.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);

        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 4;
        this.heavyDamage = this.baseHeavyDamage = 12;
        this.changeNum = this.baseChangeNum = 5;
        this.shuffleBackIntoDrawPile = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(3);
            this.upgradeHeavyDamage(3);
            this.upgradeName();
        }
    }

    @Override
    public void applyPowers() {
        super.heavyDamageApplyPower();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardHeavyDamage(mo);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster != null) {
            addToBot(new VFXAction( new ClawEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, Color.MAGENTA, Color.PINK), 0.2F));
            if (abstractMonster.hasPower("Poison")) {
                if (abstractMonster.getPower("Poison").amount >= this.changeNum) {
                    addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.heavyDamage), AbstractGameAction.AttackEffect.NONE));
                }
            } else {
                addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage), AbstractGameAction.AttackEffect.NONE));
            }
            addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new PoisonPower(abstractMonster, abstractPlayer, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        }
    }
}
