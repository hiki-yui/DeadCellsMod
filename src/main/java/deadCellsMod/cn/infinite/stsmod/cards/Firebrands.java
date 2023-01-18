package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.action.GainBurnsPowerAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

//投掷火把
public class Firebrands extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Firebrands";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/Firebrands.png";

    public Firebrands(){
        super(BASE_ID,STRINGS.NAME,IMG,2,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ALL_ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.RED2_PURPLE2_SKILL_CARD, DeadCellsModInitializer.RED2_PURPLE2_SKILL_CARD_PORTRAIT);

        this.burnNumber = this.baseBurnNumber = 6;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0;i<this.magicNumber;i++) {
            abstractMonster = AbstractDungeon.getRandomMonster();
            addToBot(new GainBurnsPowerAction(abstractMonster,abstractPlayer,this.burnNumber));
        }
    }
}
