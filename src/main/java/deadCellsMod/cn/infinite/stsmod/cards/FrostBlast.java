package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.FrostBlastAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

//冰霜爆破
public class FrostBlast extends DeadCellsCard{
    public static final String BASE_ID = "deadCells:FrostBlast";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/FrostBlast.png";

    public FrostBlast(){
        super(BASE_ID,STRINGS.NAME,IMG,-1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ALL_ENEMY);

        this.baseBlock = 4;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            /*this.upgradeBlock(2);*/
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new FrostBlastAction(abstractPlayer,this.block,this.upgraded?this.energyOnUse+1:this.energyOnUse
                ,this.freeToPlayOnce,this.magicNumber));
    }
}
