package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;
import deadCellsMod.cn.infinite.stsmod.powers.IceShieldPower;

//冰盾
public class IceShield extends DeadCellsCard{
    public static final String BASE_ID = "deadCells:IceShield";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/IceShield.png";

    public IceShield(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);

        this.setBackgroundTexture(DeadCellsModInitializer.GREEN_SKILL_CARD, DeadCellsModInitializer.GREEN_SKILL_CARD_PORTRAIT);

        this.baseBlock = 6;
        this.magicNumber = this.baseMagicNumber = 2;
        this.tags.add(DeadCellsTags.SHIELD);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBlock(3);
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new IceShieldPower(abstractPlayer,magicNumber),this.magicNumber));
    }
}
