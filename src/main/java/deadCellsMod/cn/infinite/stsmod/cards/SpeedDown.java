package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

//减速
public class SpeedDown extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:SpeedDown";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/SpeedDown.png";

    public SpeedDown(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.STATUS, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.SPECIAL,CardTarget.NONE);
        this.setBackgroundTexture(DeadCellsModInitializer.GRAY_SKILL_CARD, DeadCellsModInitializer.GRAY_SKILL_CARD_PORTRAIT);

        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(this.magicNumber));
    }
}
