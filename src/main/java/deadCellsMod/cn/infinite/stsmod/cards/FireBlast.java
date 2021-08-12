package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.FireBlastAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

public class FireBlast extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:FireBlast";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/FireBlast.png";

    public FireBlast(){
        super(BASE_ID,STRINGS.NAME,IMG,-1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ALL_ENEMY);

        this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(3);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new FireBlastAction(abstractPlayer,this.baseDamage,this.energyOnUse,this.freeToPlayOnce,this.magicNumber));
    }
}
