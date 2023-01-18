package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.action.GainBleedingPowerAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

//圆舞飞刀
public class RoundingKnife extends CustomCard {
    public static final String ID = "deadCells:RoundingKnife";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String IMG = "img/card/RoundingKnife.png";


    public RoundingKnife(){
        super(ID,strings.NAME,IMG,0,strings.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.BASIC,CardTarget.ALL_ENEMY);
        this.setBackgroundTexture(DeadCellsModInitializer.RED2_PURPLE2_SKILL_CARD, DeadCellsModInitializer.RED2_PURPLE2_SKILL_CARD_PORTRAIT);


        this.magicNumber = this.baseMagicNumber = 1;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.rawDescription = strings.UPGRADE_DESCRIPTION;
            this.upgradeName();
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("deadCells:ROUNDING_KNIFE"));
        addToBot(new WaitAction(0.5F));
        //以下给予流血的操作以有GainAllEnemyBleedingPowerAction可以替代
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if (monster!=null && !monster.isDying){
                addToBot(new ApplyPowerAction(monster,abstractPlayer,new WeakPower(monster,
                        this.magicNumber,false),this.magicNumber,true));
                if (!abstractPlayer.hasPower("deadCells:BleedingSpreadPower")) {
                    addToBot(new GainBleedingPowerAction(abstractPlayer, monster, this.upgraded?6:3));
                }
            }
        }
        if (abstractPlayer.hasPower("deadCells:BleedingSpreadPower")) {
            new GainBleedingPowerAction(abstractPlayer, abstractMonster,  this.upgraded?6:3).update();
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
