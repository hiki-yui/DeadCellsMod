package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;

public class KnockBackShield extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:KnockBackShield";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/KnockBackShield.png";

    public KnockBackShield(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ALL_ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.PURPLE2_GREEN2_SKILL_CARD, DeadCellsModInitializer.PURPLE2_GREEN2_SKILL_CARD_PORTRAIT);

        this.baseBlock = 5;
        this.magicNumber = this.baseMagicNumber = 3;
        this.tags.add(DeadCellsTags.SHIELD);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBlock(3);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DrawCardNextTurnPower(abstractPlayer,this.magicNumber),this.magicNumber));
    }
}
