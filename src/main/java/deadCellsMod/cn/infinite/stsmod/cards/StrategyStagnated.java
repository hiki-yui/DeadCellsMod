package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

//战略停滞
public class StrategyStagnated extends DeadCellsCard{
    public static final String BASE_ID = "deadCells:StrategyStagnated";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/StrategyStagnated.png";

    public StrategyStagnated(){
        super(BASE_ID,STRINGS.NAME,IMG,0,STRINGS.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 2;
        this.cardsToPreview = new SpeedDown();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.upgradeMagicNumber(1);
            this.initializeDescription();
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainEnergyAction(this.magicNumber));
        addToBot(new MakeTempCardInDrawPileAction(new SpeedDown(),1,true,true));
    }
}
