package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.powers.CocoonPower;

public class Cocoon extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Cocoon";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public Cocoon(){
        super(BASE_ID,STRINGS.NAME,"img/card/Cocoon.png",1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 2;
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
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new CocoonPower(abstractPlayer,this.magicNumber),this.magicNumber));
    }
}
