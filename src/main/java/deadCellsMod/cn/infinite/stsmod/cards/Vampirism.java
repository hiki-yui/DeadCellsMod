package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.GainAllEnemyBleedingPowerAction;
import deadCellsMod.cn.infinite.stsmod.action.GainBleedingPowerAction;
import deadCellsMod.cn.infinite.stsmod.action.VampirismAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

public class Vampirism extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Vampirism";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public Vampirism(){
        super(BASE_ID,STRINGS.NAME,"img/card/Vampirism.png",2,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractCardEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.ALL_ENEMY);

        this.magicNumber = this.baseMagicNumber = 4;
        this.exhaust = true;
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
        addToBot(new VampirismAction(abstractPlayer,magicNumber));
        addToTop(new GainAllEnemyBleedingPowerAction(abstractPlayer, magicNumber));
    }
}