package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.powers.InTimePower;

public class InTime extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:InTime";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public InTime(){
        super(BASE_ID,STRINGS.NAME,"img/card/InTime.png",2,STRINGS.DESCRIPTION,CardType.POWER, AbstractCardEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.isInnate = true;
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new InTimePower(abstractPlayer,this.magicNumber),this.magicNumber));
    }
}
