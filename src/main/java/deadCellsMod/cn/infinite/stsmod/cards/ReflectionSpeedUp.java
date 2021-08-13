package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.ReflectionSpeedUpPower;

public class ReflectionSpeedUp extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:ReflectionSpeedUp";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/ReflectionSpeedUp.png";

    public ReflectionSpeedUp() {
        super(BASE_ID, STRINGS.NAME, IMG, /*3*/2, STRINGS.DESCRIPTION, CardType.POWER, AbstractDeadCellsEnum.DEAD_CELLS, CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.isInnate = true;
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            /*this.upgradeBaseCost(this.cost - 1);*/
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new ReflectionSpeedUpPower(abstractPlayer,baseMagicNumber),1));
    }


}
