package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.IronStaffPower;

public class IronStaff extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:IronStaff";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public IronStaff(){
        super(BASE_ID,STRINGS.NAME,"img/card/IronStaff.png",3,STRINGS.DESCRIPTION, CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS, AbstractCard.CardRarity.UNCOMMON, CardTarget.SELF);
        this.setBackgroundTexture(DeadCellsModInitializer.RED2_GREEN2_SKILL_CARD, DeadCellsModInitializer.RED2_GREEN2_SKILL_CARD_PORTRAIT);

        this.baseBlock = 24;
        this.magicNumber = this.baseMagicNumber = 1 ;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBlock(6);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new IronStaffPower(abstractPlayer,this.magicNumber),this.magicNumber));
        addToBot(new PressEndTurnButtonAction());
    }
}
