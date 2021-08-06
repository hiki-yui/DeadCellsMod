package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.GainBurnsPowerAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

public class FireGrenade extends GrenadeCard {

    public static final String BASE_ID = "deadCells:FireGrenade";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/FireGrenade.png";

    public FireGrenade(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ALL_ENEMY);

        this.magicNumber = this.baseMagicNumber = 6;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(2);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            addToBot(new GainBurnsPowerAction(monster,abstractPlayer,this.magicNumber));
        }
    }
}
