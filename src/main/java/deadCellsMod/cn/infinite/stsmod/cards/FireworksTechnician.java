package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.FireworksTechnicianPower;

public class FireworksTechnician extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:FireworksTechnician";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public FireworksTechnician(){
        super(BASE_ID,STRINGS.NAME,"img/card/FireworksTechnician.png",1,STRINGS.DESCRIPTION, CardType.POWER, AbstractDeadCellsEnum.DEAD_CELLS, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setBackgroundTexture(DeadCellsModInitializer.GOLD_POWER_CARD, DeadCellsModInitializer.GOLD_POWER_CARD_PORTRAIT);

        this.magicNumber = this.baseMagicNumber = 4;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            this.upgradeMagicNumber(2);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new FireworksTechnicianPower(abstractPlayer,this.magicNumber),this.magicNumber));
    }
}
