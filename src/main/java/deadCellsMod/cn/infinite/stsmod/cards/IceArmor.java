package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.powers.FrostbitePower;


public class IceArmor extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:IceArmor";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/IceArmor.png";

    public IceArmor(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.SELF);

        this.block = this.baseBlock = 6;
        this.changeNum = this.baseChangeNum = 9;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBlock(3);
            this.upgradeChangeNum(3);
            this.upgradeName();
        }
    }


    @Override
    public void applyPowers() {
        int realBase = this.baseBlock;
        this.baseBlock = this.baseChangeNum;
        super.applyPowers();
        this.changeNum = this.block;
        this.baseBlock = realBase;
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBase = this.baseBlock;
        this.baseBlock = this.baseChangeNum;
        super.calculateCardDamage(mo);
        this.changeNum = this.block;
        this.baseBlock = realBase;
        super.calculateCardDamage(mo);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.currentBlock<=0) {
            addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,changeNum));
        }else{
            addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,block));
        }
        for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters) {
            addToBot(new ApplyPowerAction(monster,abstractPlayer,new FrostbitePower(monster,abstractPlayer,this.magicNumber),this.magicNumber));
        }
    }
}
