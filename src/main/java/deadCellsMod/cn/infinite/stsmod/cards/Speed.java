package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.SpeedPower;


public class Speed extends CustomCard {

    public static final String ID = "deadCells:Speed";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = "img/card/Speed.png";

    public Speed(){
        super(ID,strings.NAME,IMG,1,strings.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);

        this.baseMagicNumber = 4;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
        addToBot(new SFXAction("deadCells:SPEED"));
        addToBot(new ApplyPowerAction(paramAbstractPlayer, paramAbstractPlayer, new SpeedPower(paramAbstractPlayer, this.baseMagicNumber), this.baseMagicNumber));
    }

    /*@Override
    public AbstractCard makeCopy() {
        AbstractCard card = new Speed();
        if (this.upgraded){
            card.upgrade();
        }
        return card;
    }*/
}
