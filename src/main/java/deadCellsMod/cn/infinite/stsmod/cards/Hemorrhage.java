package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.GainBleedingPowerAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

//放血
public class Hemorrhage extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:Hemorrhage";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public Hemorrhage(){
        super(BASE_ID,STRINGS.NAME,"img/card/Hemorrhage.png",2,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ENEMY);

        this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 12;
        this.heavyDamage = this.baseHeavyDamage = 12;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(5);
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
        addToBot(new GainBleedingPowerAction(abstractPlayer, abstractMonster, this.magicNumber));
        if (!abstractMonster.hasPower("deadCells:BleedingPower")) {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }else{
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.heavyDamage), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }
}
