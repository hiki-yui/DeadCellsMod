package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import deadCellsMod.cn.infinite.stsmod.action.GainBleedingPowerAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.powers.BleedingPower;

//圆舞飞刀
public class RoundingKnife extends CustomCard {
    public static final String ID = "deadCells:RoundingKnife";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = "img/card/RoundingKnife.png";


    public RoundingKnife(){
        super(ID,strings.NAME,IMG,0,strings.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.BASIC,CardTarget.ALL_ENEMY);


        this.baseMagicNumber = 1;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("deadCells:ROUNDING_KNIFE"));
        addToBot(new WaitAction(0.5F));
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if (monster!=null){
                addToBot(new ApplyPowerAction(monster,abstractPlayer,new WeakPower(monster,
                        this.magicNumber,false),this.magicNumber,true));
                addToBot(new GainBleedingPowerAction(abstractPlayer,monster,3));
            }
        }
    }

    /*@Override
    public AbstractCard makeCopy() {
        RoundingKnife card = new RoundingKnife();
        if (this.upgraded){
            card.upgrade();
        }
        return card;
    }*/
}
