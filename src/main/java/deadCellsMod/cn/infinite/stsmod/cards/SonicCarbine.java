package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.SCbPower;

public class SonicCarbine extends DeadCellsCard{
    public static final String BASE_ID = "deadCells:SonicCarbine";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public SonicCarbine(){
        super(BASE_ID,STRINGS.NAME,"img/card/SonicCarbine.png",
                1,STRINGS.DESCRIPTION, CardType.SKILL,
                AbstractDeadCellsEnum.DEAD_CELLS, CardRarity.RARE,
                CardTarget.NONE);
        this.setBackgroundTexture(DeadCellsModInitializer.PURPLE_SKILL_CARD, DeadCellsModInitializer.PURPLE_SKILL_CARD_PORTRAIT);

        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(1); ;
            this.upgraded = true;
            this.upgradeName();

        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new SCbPower(abstractPlayer,this.magicNumber),this.magicNumber,true));
    }
}
